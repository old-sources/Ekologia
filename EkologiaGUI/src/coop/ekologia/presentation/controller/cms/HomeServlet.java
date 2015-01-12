//ATTENTION cette servlet fait bugué le type de retour des fichier css et js en plain/text
//
//package coop.ekologia.presentation.controller.cms;
//
//import java.io.IOException;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import javax.ejb.EJB;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import coop.ekologia.DTO.cms.PageDTO;
//import coop.ekologia.presentation.EkologiaServlet;
//import coop.ekologia.service.cms.PageServiceInterface;
//
//@WebServlet("/")
//public class HomeServlet extends EkologiaServlet {
//private static final long serialVersionUID = 1L;
//    public static final String routing(HttpServletRequest request) {
//        return getUrl(request, "/");
//    }
//
//    @EJB
//    private PageServiceInterface pageService;
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String page = "homepage";
//        PageDTO pageDTO = pageService.getPageFromUrl(page);
//        if (pageDTO != null) {
//            request.getSession().setAttribute("pageDTO", pageDTO);
//            forwardToJsp("/cms/polymorph.jsp", request, response);
//        }
//
//    }
//}
