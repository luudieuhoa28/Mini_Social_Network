/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.article;

import hoald.user.Users;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author dell
 */
public class DeleteArticleController extends HttpServlet {
    public static final String DELETE_ARTICLE_SUCCESS = "NewsFeedController";
    public static final String DELETE_ARTICLE_FAIL = "error.jsp";
    public static final String LOGIN = "login.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = DELETE_ARTICLE_FAIL;
        try {
            String userId = request.getParameter("userId");
            HttpSession session = request.getSession();
            Users user = (Users) session.getAttribute("USER_DTO");
            if (userId.equals(user.getUserId()) || user.getRoleId().getRoleName().equalsIgnoreCase("admin")) {
                int articleId = Integer.parseInt(request.getParameter("articleId"));
                ArticleBLO articleBLO = new ArticleBLO();
                articleBLO.updateStatusArticle(articleId);
                url = DELETE_ARTICLE_SUCCESS;
            } else {
                url = LOGIN;
            }

        } catch (Exception e) {
            Logger.getLogger("Error at DeleteArticleController: " + e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
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
