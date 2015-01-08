package coop.ekologia.service.mapper.group.wiki;

import javax.inject.Inject;

import coop.ekologia.DTO.group.wiki.WikicommentDTO;
import coop.ekologia.entity.group.wiki.Wikicomment;
import coop.ekologia.service.mapper.Mapper;
import coop.ekologia.service.mapper.user.UserMapper;

public class WikicommentMapper extends Mapper<WikicommentDTO, Wikicomment>{
	@Inject
	private UserMapper userMapper;
	
	@Override
	public WikicommentDTO mapFromEntity(Wikicomment wikicomment) {
		WikicommentDTO wikicommentDTO = new WikicommentDTO();
		wikicommentDTO.setId(wikicomment.getId());
		wikicommentDTO.setTitle(wikicomment.getTitle());
		wikicommentDTO.setContent(wikicomment.getContent());
		wikicommentDTO.setDate(wikicomment.getDate());
		wikicommentDTO.setChildren(mapFromEntity(wikicomment.getWikicommentsById()));
		wikicommentDTO.setUser(userMapper.mapFromEntity(wikicomment.getUser()));
		for (WikicommentDTO child: wikicommentDTO.getChildren()) {
			child.setParent(wikicommentDTO);
		}
		return wikicommentDTO;
	}

	@Override
	public Wikicomment mapToEntity(WikicommentDTO wikicommentDTO) {
		Wikicomment wikicomment = new Wikicomment();
		wikicomment.setId(wikicommentDTO.getId());
		wikicomment.setTitle(wikicommentDTO.getTitle());
		wikicomment.setContent(wikicommentDTO.getContent());
		wikicomment.setDate(wikicommentDTO.getDate());
		wikicomment.setWikicommentsById(mapToEntity(wikicommentDTO.getChildren()));
		wikicomment.setUser(userMapper.mapToEntity(wikicommentDTO.getUser()));
		for (Wikicomment child: wikicomment.getWikicommentsById()) {
			child.setWikicommentByParentId(wikicomment);
		}
		return wikicomment;
	}

}
