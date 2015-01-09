package coop.ekologia.service.mapper.group.wiki;

import javax.inject.Inject;

import coop.ekologia.DTO.group.wiki.WikiversionDTO;
import coop.ekologia.entity.group.wiki.Wikiversion;
import coop.ekologia.service.mapper.Mapper;
import coop.ekologia.service.mapper.user.UserMapper;

public class WikiversionMapper extends Mapper<WikiversionDTO, Wikiversion>{
	@Inject
	private UserMapper userMapper;
	
	@Override
	public WikiversionDTO mapFromEntity(Wikiversion wikiversion) {
		WikiversionDTO wikiversionDTO = new WikiversionDTO();
		wikiversionDTO.setId(wikiversion.getId());
		wikiversionDTO.setDate(wikiversion.getDate());
		wikiversionDTO.setContent(wikiversion.getContent());
		wikiversionDTO.setActive(wikiversion.getActive());
		wikiversionDTO.setImage(wikiversion.getImage());
		wikiversionDTO.setUser(userMapper.mapFromEntity(wikiversion.getUser()));
		return wikiversionDTO;
	}

	@Override
	public Wikiversion mapToEntity(WikiversionDTO wikiversionDTO) {
		Wikiversion wikiversion = new Wikiversion();
		wikiversion.setId(wikiversionDTO.getId());
		wikiversion.setDate(wikiversionDTO.getDate());
		wikiversion.setContent(wikiversionDTO.getContent());
		wikiversion.setActive(wikiversionDTO.getActive());
		wikiversion.setImage(wikiversionDTO.getImage());
		wikiversion.setUser(userMapper.mapToEntity(wikiversionDTO.getUser()));
		return wikiversion;
	}
}
