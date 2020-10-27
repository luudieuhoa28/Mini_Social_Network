/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.user;

import java.io.IOException;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hoald.utils.Util;

/**
 *
 * @author dell
 */
public class VerifyAccountController extends HttpServlet {

    public static final String VERIFY_ACCOUNT_PAGE = "verify_account.jsp";
    public static final String VERIFY_ERROR = "error.jsp";
    public static final String VERIFY_ACCOUNT_SUCCESS = "login.jsp";

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
        String url = VERIFY_ERROR;
        try {
            Users user = (Users) request.getAttribute("USER_DTO");
            if (user != null) {
                if (Util.sendMail(user.getUserId(), user.getVerifyCode())) {
                    url = VERIFY_ACCOUNT_PAGE;
                }
            } else {
                UsersBLO usersBLO = new UsersBLO();
                String userId = request.getParameter("userId");
                user = usersBLO.getUser(userId);
            }

            String verifyCode = request.getParameter("verifyCode");
            if (verifyCode != null) {
                if (verifyCode.equals(user.getVerifyCode())) {
                    UsersBLO usersBLO = new UsersBLO();
                    usersBLO.activateAccount(user.getUserId());
                    url = VERIFY_ACCOUNT_SUCCESS;
                } else {
                    url = VERIFY_ACCOUNT_PAGE;
                    request.setAttribute("VERIFY_FAIL", "The code is incorrect!!!");
                }
            }

        } catch (Exception e) {
            Logger.getLogger("Error at VerifyAccountController: " + e);
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
