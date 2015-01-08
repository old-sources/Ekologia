package coop.ekologia.service.mapper.group.wiki;

import java.util.ArrayList;
import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import coop.ekologia.DTO.group.wiki.WikiDTO;
import coop.ekologia.DTO.group.wiki.WikicommentDTO;
import coop.ekologia.DTO.group.wiki.WikiversionDTO;
import coop.ekologia.entity.group.wiki.Wiki;
import coop.ekologia.entity.group.wiki.Wikicomment;
import coop.ekologia.entity.group.wiki.Wikiversion;
import coop.ekologia.service.mapper.Mapper;
import coop.ekologia.service.mapper.group.GroupMapper;
import coop.ekologia.service.utils.DateUtilitiesInterface;
import coop.ekologia.service.utils.NumberUtilitiesInterface;

@Stateless
public class WikiMapper extends Mapper<WikiDTO, Wiki> {
	@Inject
	private GroupMapper groupMapper;
	
	@Inject
	private WikicommentMapper wikicommentMapper;
	
	@Inject
	private WikiversionMapper wikiversionMapper;
	
	@EJB
	private DateUtilitiesInterface dateUtilities;
	
	@EJB
	private NumberUtilitiesInterface numberUtilities;
	
	@Override
	public WikiDTO mapFromEntity(Wiki wiki) {
	    if (wiki == null) {
	        return null;
	    }
	    // We implement security to avoid fetching all wiki of a group.
		return mapFromEntity(wiki, 1, 1);
	}

    public Collection<WikiDTO> mapFromEntity(Collection<Wiki> wikiList, int maxHierarchyDown, int maxHierarchyUp) {
        if (wikiList == null) {
            return null;
        }
        Collection<WikiDTO> result = new ArrayList<WikiDTO>();
        for (Wiki wiki : wikiList) {
            result.add(mapFromEntity(wiki, maxHierarchyDown, maxHierarchyUp));
        }
        return result;
    }
	
    public WikiDTO mapFromEntity(Wiki wiki, int maxHierarchyDown, int maxHierarchyUp) {
        if (wiki == null) {
            return null;
        }
        WikiDTO wikiDTO = new WikiDTO();
        wikiDTO.setId(wiki.getId());
        wikiDTO.setTitle(wiki.getTitle());
        wikiDTO.setLanguage(wiki.getLanguage());
        wikiDTO.setCanonical(wiki.getCanonical());
        wikiDTO.setEditable(wiki.getEditable());
        wikiDTO.setVisible(wiki.getVisible());
        wikiDTO.setGroup(groupMapper.mapFromEntity(wiki.getGroup()));
        wikiDTO.setComments(wikicommentMapper.mapFromEntity(wiki.getWikicommentsById()));
        wikiDTO.setVersions(wikiversionMapper.mapFromEntity(wiki.getWikiversionsById()));
        
        for (WikicommentDTO wikicommentDTO: wikiDTO.getComments()) {
            wikicommentDTO.setWiki(wikiDTO);
        }
        
        for (WikiversionDTO wikiversionDTO: wikiDTO.getVersions()) {
            wikiversionDTO.setWiki(wikiDTO);
        }
        
        // Find current version = last active version or last not active version if any active
        for (WikiversionDTO wikiversionDTO: wikiDTO.getVersions()) {
            WikiversionDTO currentVersion = wikiDTO.getCurrentVersion();
            if (currentVersion == null) {
                wikiDTO.setCurrentVersion(wikiversionDTO);
            } else if (wikiversionDTO.isActive()) {
                if (currentVersion.isNotActive()) {
                    wikiDTO.setCurrentVersion(wikiversionDTO);
                } else if (dateUtilities.isAfter(wikiversionDTO.getDate(), currentVersion.getDate())) {
                    wikiDTO.setCurrentVersion(wikiversionDTO);
                }
                // else do not change
            } else if (currentVersion.isNotActive() && dateUtilities.isAfter(wikiversionDTO.getDate(), currentVersion.getDate())) {
                wikiDTO.setCurrentVersion(wikiversionDTO);
            }
            // else do not change
        }
        
        // We set the children and the parent only when necessary
        if (maxHierarchyDown > 0) {
            wikiDTO.setChildren(mapFromEntity(wiki.getChildren(), maxHierarchyDown - 1, 0));
            for (WikiDTO child: wikiDTO.getChildren()) {
                child.setParent(wikiDTO);
            }
        }
        if (maxHierarchyUp > 0) {
            wikiDTO.setParent(mapFromEntity(wiki.getParent(), 0, maxHierarchyUp - 1));
            if (wikiDTO.getParent() != null) {
                wikiDTO.getParent().getChildren().add(wikiDTO); // Not really true, but we try to keep logic.
            }
        }
        
        return wikiDTO;
    }

	@Override
	public Wiki mapToEntity(WikiDTO wikiDTO) {
	    if (wikiDTO == null) {
	        return null;
	    }
		Wiki wiki = new Wiki();
		wiki.setId(wikiDTO.getId());
		wiki.setTitle(wikiDTO.getTitle());
		wiki.setLanguage(wikiDTO.getLanguage());
		wiki.setCanonical(wikiDTO.getCanonical());
		wiki.setEditable(wikiDTO.getEditable());
		wiki.setVisible(wikiDTO.getVisible());
		wiki.setGroup(groupMapper.mapToEntity(wikiDTO.getGroup()));
		
		wiki.setWikicommentsById(wikicommentMapper.mapToEntity(wikiDTO.getComments()));
		if (wiki.getWikicommentsById() != null) {
		    for (Wikicomment wikicomment: wiki.getWikicommentsById()) {
		        wikicomment.setWikiByWikiId(wiki);
		    }
		}
		
		// Link with current wiki reported to the end of the method because of getCurrentVersion
		wiki.setWikiversionsById(wikiversionMapper.mapToEntity(wikiDTO.getVersions()));
		
		// No need for protection in this way.
		wiki.setParent(mapToEntity(wikiDTO.getParent()));
		if (wiki.getParent() != null) {
		    wiki.getParent().getChildren().add(wiki);
		}
		
		wiki.setChildren(mapToEntity(wikiDTO.getChildren()));
		if (wiki.getChildren() != null) {
		    for (Wiki child: wiki.getChildren()) {
		        child.setParent(wiki);
		    }
		}
		
		// Add the current version if it does not exists into list of versions
		if (wikiDTO.getCurrentVersion() != null) {
			if (wikiDTO.getCurrentVersion().getId() == null) {
				wiki.getWikiversionsById().add(wikiversionMapper.mapToEntity(wikiDTO.getCurrentVersion()));
			} else {
			    int currentVersionId = wikiDTO.getCurrentVersion().getId();
				boolean exists = false;
				for (WikiversionDTO wikiversionDTO: wikiDTO.getVersions()) {
					if (numberUtilities.equals(currentVersionId, wikiversionDTO.getId())) {
						exists = true;
						break;
					}
				}
				if (!exists) {
					wiki.getWikiversionsById().add(wikiversionMapper.mapToEntity(wikiDTO.getCurrentVersion()));
				}
			}
		}
		
        if (wiki.getWikiversionsById() != null) {
            for (Wikiversion wikiversion: wiki.getWikiversionsById()) {
                wikiversion.setWikiByWikiId(wiki);
            }
        }
        
		return wiki;
	}
}
