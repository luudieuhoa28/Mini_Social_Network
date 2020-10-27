/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.controller;
import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
public class MainController extends HttpServlet {

    public static final String LOGIN = "LoginController";
    public static final String REGISTER = "RegisterController";
    public static final String ERROR_PAGE = "error.jsp";
    public static final String VERIFY_CODE = "VerifyAccountController";
    public static final String SHOW_ARTICLE_DETAIL = "ShowArticleDetailController";
    public static final String DELETE_COMMENT = "DeleteCommentController";
    public static final String MAKE_EMOTION = "MakeEmotionController";
    public static final String POST_COMMENT = "PostCommentController";
    public static final String SHOW_NOTIFICATION = "ShowNotificationController";
    public static final String DELETE_ARTICLE = "DeleteArticleController";
    public static final String LOGOUT = "LogoutController";
    public static final String POST_ARTICLE = "PostArticleController";
    public static final String GET_NEWS_FEED = "NewsFeedController";

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
            String action = request.getParameter("btnAction");
            switch (action) {
                case "Login":
                    url = LOGIN;
                    break;
                case "Register":
                    url = REGISTER;
                    break;
                case "Verify":
                    url = VERIFY_CODE;
                    break;
                case "ShowDetail":
                    url = SHOW_ARTICLE_DETAIL;
                    break;
                case "DeleteComment":
                    url = DELETE_COMMENT;
                    break;
                case "Dislike":
                    url = MAKE_EMOTION;
                    break;
                case "Like":
                    url = MAKE_EMOTION;
                    break;
                case "Post comment":
                    url = POST_COMMENT;
                    break;
                case "ShowNotification":
                    url = SHOW_NOTIFICATION;
                    break;
                case "DeleteArticle":
                    url = DELETE_ARTICLE;
                    break;
                case "Logout":
                    url = LOGOUT;
                    break;
                case "Post article":
                    url = POST_ARTICLE;
                    break;
                case "Previous":
                    url = GET_NEWS_FEED;
                    break;
                case "Next":
                    url = GET_NEWS_FEED;
                    break;
                case "NewsFeed":
                    url = GET_NEWS_FEED;
                    break;
                case "ArticleDetail":
                    url = SHOW_ARTICLE_DETAIL;
                    break;

            }
        } catch (Exception e) {
            Logger.getLogger("Error at MainController " + e);
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
