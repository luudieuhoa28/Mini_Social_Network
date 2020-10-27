/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.controller;

import hoald.article.Article;
import hoald.article.ArticleBLO;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
public class NewsFeedController extends HttpServlet {

    public static final String NEWS_FEED = "newfeed.jsp";
    public static final String ERROR_PAGE = "error.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR_PAGE;
        try {
            int page = 1;
            try {
                String pageStr = request.getParameter("page");
                page = Integer.parseInt(pageStr);
            } catch (Exception e) {
                Logger.getLogger("Error at NewsFeedController: " + e);
            }
            String btnAction = request.getParameter("btnAction");
            String searchArticle = request.getParameter("searchArticle");
            if (searchArticle == null) {
                ArticleBLO articleBLO = new ArticleBLO();
                List<Article> listArticle = articleBLO.getListArticles("", 1);
                request.setAttribute("LIST_ARTICLE", listArticle);
                request.setAttribute("PAGE", 1);
            } else {
                switch (btnAction) {
                    case "Search":
                        ArticleBLO articleBLO = new ArticleBLO();
                        List<Article> listArticle = articleBLO.getListArticles(searchArticle, 1);
                        request.setAttribute("LIST_ARTICLE", listArticle);
                        request.setAttribute("PAGE", 1);
                        break;
                    case "Next":
                        articleBLO = new ArticleBLO();
                        listArticle = articleBLO.getListArticles(searchArticle, page + 1);
                        if (listArticle.isEmpty()) {
                            listArticle = articleBLO.getListArticles(searchArticle, page);
                            request.setAttribute("LIST_ARTICLE", listArticle);
                            request.setAttribute("PAGE", page);
                        } else {
                            request.setAttribute("LIST_ARTICLE", listArticle);
                            request.setAttribute("PAGE", page + 1);
                        }
                        break;
                    case "Previous":
                        articleBLO = new ArticleBLO();
                        if (page == 1) {
                            //listArticle = ArticleDAO.getListArticle(searchArticle, 1);
                            listArticle = articleBLO.getListArticles(searchArticle, 1);
                            request.setAttribute("LIST_ARTICLE", listArticle);
                            request.setAttribute("PAGE", 1);
                        } else {
                            listArticle = articleBLO.getListArticles(searchArticle, page - 1);
                            //listArticle = ArticleDAO.getListArticle(searchArticle, page - 1);
                            request.setAttribute("LIST_ARTICLE", listArticle);
                            request.setAttribute("PAGE", page - 1);
                        }
                        break;
                }
            }

            url = NEWS_FEED;
        } catch (Exception e) {
            Logger.getLogger("Error at NewsFeedController: " + e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
