/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.comment;

import hoald.user.Users;
import hoald.notification.NotificationBLO;
import hoald.notification.Notification;
import hoald.article.ArticleBLO;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell
 */
public class PostCommentController extends HttpServlet {

    public static final String POST_COMMENT_SUCCESS = "MainController?btnAction=ArticleDetail&";
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
            int articleId = Integer.parseInt(request.getParameter("articleId"));
            String userId = request.getParameter("userId");
            String commentContent = request.getParameter("commentContent");
            ArticleBLO articleBLO = new ArticleBLO();
            Date date = new Date();
            HttpSession session = request.getSession();
            Users userMakeAction = (Users) session.getAttribute("USER_DTO");
            if (!commentContent.equals("")) {
                Comment comment = new Comment(date, true, articleBLO.getArticle(articleId), userMakeAction, commentContent);
                CommentBLO commentBLO = new CommentBLO();
                commentBLO.postComment(comment);

                if (!userId.equals(userMakeAction.getUserId())) {
                    Notification notification = new Notification(2, false, date, articleBLO.getArticle(articleId), userMakeAction);
                    NotificationBLO notificationBLO = new NotificationBLO();
                    notificationBLO.insertNotification(notification);
                }
            }
            url = POST_COMMENT_SUCCESS + "articleId=" + articleId;
        } catch (Exception e) {
            Logger.getLogger("Error at PostCommentController: " + e);
        } finally {
            response.sendRedirect(url);
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
