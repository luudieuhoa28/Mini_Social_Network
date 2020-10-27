/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.emotion;

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
 * @author dell
 */
public class MakeEmotionController extends HttpServlet {

    private static final String MAKE_EMOTION_FAIL = "error.jsp";
    private static final String MAKE_EMOTION_SUCCESS = "MainController?btnAction=ArticleDetail&";

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
        String url = MAKE_EMOTION_FAIL;
        try {
            String action = request.getParameter("btnAction");
            String userId = request.getParameter("userId");
            int articleId = Integer.parseInt(request.getParameter("articleId"));
            EmotionBLO emotionBLO = new EmotionBLO();
            Emotion emotion;
            ArticleBLO articleBLO = new ArticleBLO();
            Date date = new Date();
            Notification notification;
            NotificationBLO notificationBLO;
            HttpSession session = request.getSession();
            Users userMakeAction = (Users) session.getAttribute("USER_DTO");
            switch (action) {
                case "Dislike":
                    emotion = new Emotion(new EmotionPK(userMakeAction.getUserId(), articleId), 2);
                    if (emotionBLO.makeEmotion(emotion) && !userId.equals(userMakeAction.getUserId())) {
                        notification = new Notification(0, false, date, articleBLO.getArticle(articleId), userMakeAction);
                        notificationBLO = new NotificationBLO();
                        notificationBLO.insertNotification(notification);
                    }
                    break;
                case "Like":
                    emotion = new Emotion(new EmotionPK(userMakeAction.getUserId(), articleId), 1);
                    if (emotionBLO.makeEmotion(emotion) && !userId.equals(userMakeAction.getUserId())) {
                        notification = new Notification(1, false, date, articleBLO.getArticle(articleId), userMakeAction);
                        notificationBLO = new NotificationBLO();
                        notificationBLO.insertNotification(notification);
                    }
                    break;
            }
            url = MAKE_EMOTION_SUCCESS + "userId=" + userId + "&articleId=" + articleId;
        } catch (Exception e) {
            Logger.getLogger("Error at MakeEmotionController: " + e);
        } finally {
            response.sendRedirect(url);
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
