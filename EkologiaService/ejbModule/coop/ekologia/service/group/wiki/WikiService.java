//package coop.ekologia.service.group.wiki;
//
//import java.util.Date;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//
//import coop.ekologia.DTO.group.GroupDTO;
//import coop.ekologia.DTO.group.wiki.WikiDTO;
//import coop.ekologia.DTO.group.wiki.WikiversionDTO;
//import coop.ekologia.DTO.user.UserDTO;
//import coop.ekologia.entity.group.wiki.Wiki;
//import coop.ekologia.entity.group.wiki.Wikicomment;
//import coop.ekologia.entity.group.wiki.Wikiversion;
//import coop.ekologia.service.mapper.group.wiki.WikiMapper;
//import coop.ekologia.service.user.UserServiceInterface;
//import coop.ekologia.service.utils.CanonicalizerServiceInterface;
//
//@Stateless
//public class WikiService implements WikiServiceInterface {
//    private static final Logger logger = Logger.getLogger(WikiService.class.getName());
//    
//    @PersistenceContext
//    private EntityManager em;
//    
////    @Inject
////    private WikiMapper wikiMapper;
////    
//    @EJB
//    private UserServiceInterface userService;
//    
////    @EJB
////    private CanonicalizerServiceInterface canonicalizerService;
////	
//	@Override
//	public WikiDTO findByCanonical(String language, String canonical) {
//	    if (language == null || canonical == null) {
//	        logger.log(Level.INFO, "The language or the canonical is null.");
//	        return null;
//	    }
//		Query query = em.createNamedQuery(Wiki.FIND_BY_CANONICAL);
//        query.setParameter("canonical", canonical);
//        query.setParameter("language", language);
//        if (query.getResultList().isEmpty()) {
//        	return null;
//        } else {
//        	Wiki result = (Wiki) query.getSingleResult();
//            return wikiMapper.mapFromEntity(result);
//        }
//	}
//	
//	@Override
//	public boolean exists(String language, String canonical) {
//        if (language == null || canonical == null) {
//            logger.log(Level.INFO, "The language or the canonical is null.");
//            return false;
//        }
//		Query query = em.createNamedQuery(Wiki.FIND_BY_CANONICAL);
//        query.setParameter("canonical", canonical);
//        query.setParameter("language", language);
//        return !query.getResultList().isEmpty();
//	}
//	
//	@Override
//	public boolean canRead(UserDTO userDTO, WikiDTO wikiDTO) {
//        if (userDTO == null || wikiDTO == null) {
//            logger.log(Level.INFO, "The userDTO or wikiDTO is null.");
//            return false;
//        }
//		if (canRead(userDTO, wikiDTO.getGroup())) {
//			for (WikiversionDTO wikiversionDTO: wikiDTO.getVersions()) {
//				if (wikiversionDTO.isActive()) {
//					return true;
//				}
//			}
//		}
//		return false;
//		
//	}
//	
//	@Override
//	public boolean canRead(UserDTO userDTO, GroupDTO groupDTO) {
//	    if (userDTO == null || groupDTO == null) {
//            logger.log(Level.INFO, "The userDTO or groupDTO is null.");
//            return false;
//        }
//		if (userService.isIntoGroup(userDTO, groupDTO)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public boolean canWrite(UserDTO userDTO, WikiDTO wikiDTO) {
//	    if (userDTO == null || wikiDTO == null) {
//            logger.log(Level.INFO, "The userDTO or wikiDTO is null.");
//            return false;
//        }
//		if (wikiDTO.isEditable()) {
//			if (canWrite(userDTO, wikiDTO.getGroup())) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	@Override
//	public boolean canWrite(UserDTO userDTO, GroupDTO groupDTO) {
//	    if (userDTO == null || groupDTO == null) {
//            logger.log(Level.INFO, "The userDTO or groupDTO is null.");
//            return false;
//        }
//		if (userService.isIntoGroup(userDTO, groupDTO)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public synchronized WikiDTO create(WikiDTO wikiDTO) {
//        // We need to synchronize the method, because we must have a unique canonical for a language.
//	    if (wikiDTO == null) {
//            logger.log(Level.INFO, "The wikiDTO is null.");
//            return null;
//	    }
//	    
//	    // prepare
//		Wiki wiki = wikiMapper.mapToEntity(wikiDTO);
//		String canonicalPrefix = canonicalizerService.strToUrl(wiki.getTitle());
//		if (exists(wiki.getLanguage(), canonicalPrefix)) {
//			int suffix = 2;
//			while (exists(wiki.getLanguage(), canonicalPrefix + "-" + suffix)) {
//				suffix++;
//			}
//			wiki.setCanonical(canonicalPrefix + "-" + suffix);
//		} else {
//			wiki.setCanonical(canonicalPrefix);
//		}
//		
//		// save
//        em.persist(wiki);
//		for (Wikiversion wikiversion: wiki.getWikiversionsById()) {
//			if (wikiversion.getDate() == null) {
//				wikiversion.setDate(new Date());
//			}
//			em.persist(wikiversion);
//		}
//		return wikiMapper.mapFromEntity(wiki);
//	}
//	
//	@Override
//	public WikiDTO update(WikiDTO wikiDTO) {
//		Wiki wiki = wikiMapper.mapToEntity(wikiDTO);
//		for (Wikiversion wikiversion: wiki.getWikiversionsById()) {
//			if (wikiversion.getId() == null) {
//				if (wikiversion.getDate() == null) {
//					wikiversion.setDate(new Date());
//				}
//				em.persist(wikiversion);
//			} else {
//			    em.merge(wikiversion);
//			}
//		}
//        wiki = em.merge(wiki);
//		return wikiMapper.mapFromEntity(wiki);
//	}
//	
//	@Override
//	public void delete(WikiDTO wikiDTO) {
//		Wiki wiki = wikiMapper.mapToEntity(wikiDTO);
//		for (Wikiversion wikiversion: wiki.getWikiversionsById()) {
//			em.remove(wikiversion);
//		}
//		for (Wikicomment wikicomment: wiki.getWikicommentsById()) {
//			removeWikicomment(wikicomment);
//		}
//		em.remove(em.merge(wiki));
//	}
//	
//	/**
//	 * Removes the all hierarchy of {@link Wikicomment}.
//	 * 
//	 * @param wikicomment The {@code wikicomment} to remove and its children
//	 */
//	private void removeWikicomment(Wikicomment wikicomment) {
//		removeWikicomment(wikicomment, 1000);
//	}
//	
//	/**
//	 * Removes the all hierarchy of {@link Wikicomment}.
//	 * Security to avoid infinite recursive calls.
//	 * 
//	 * @param wikicomment The {@code Wikicomment} to remove and its children
//	 * @param maxAge      The security, a maximal age where the method can call itself
//	 */
//	private void removeWikicomment(Wikicomment wikicomment, int maxAge) {
//		if (maxAge == 0) {
//			// Security
//			throw new RuntimeException("The method removeWikicomment has passed its maximal recusive calls allowed.");
//		}
//		for (Wikicomment child: wikicomment.getWikicommentsById()) {
//			removeWikicomment(child, maxAge - 1);
//		}
//		em.remove(wikicomment);
//	}
//
//	@Override
//	public coop.ekologia.DTO.wiki.WikiDTO findByCanonical(String language,
//			String canonical) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public boolean canRead(UserDTO user, coop.ekologia.DTO.wiki.WikiDTO wiki) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public boolean canWrite(UserDTO user, coop.ekologia.DTO.wiki.WikiDTO wiki) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//	@Override
//	public coop.ekologia.DTO.wiki.WikiDTO create(
//			coop.ekologia.DTO.wiki.WikiDTO wikiDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public coop.ekologia.DTO.wiki.WikiDTO update(
//			coop.ekologia.DTO.wiki.WikiDTO wikiDTO) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void delete(coop.ekologia.DTO.wiki.WikiDTO wikiDTO) {
//		// TODO Auto-generated method stub
//		
//	}
//}
