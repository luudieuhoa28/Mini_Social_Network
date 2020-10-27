/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.user;

import hoald.role.Role;

import java.io.IOException;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author dell
 */
public class RegisterController extends HttpServlet {

    public static final String VERIFY_ACCOUNT = "VerifyAccountController";
    public static final String REGISTER_FAIL = "register.jsp";

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
        String url = REGISTER_FAIL;
        try {
            boolean valid = true;
            String userId = request.getParameter("userId");
            String fullName = request.getParameter("fullName");
            String password = request.getParameter("password");
            String rePassword = request.getParameter("rePassword");
            UserErrorDTO userErrorDTO = new UserErrorDTO();
            Users user = new Users(userId, fullName, password, "New", hoald.utils.Util.generateCode(6), new Role(1, "member"));
            UsersBLO usersBLO = new UsersBLO();
            if (usersBLO.getUserActive(userId) != null) {
                userErrorDTO.setUserIdError("This email existed!!!");
                valid = false;
            } else {
                if (!checkEmailFormat(userId)) {
                    userErrorDTO.setUserIdError("This must be an email!!!");
                    valid = false;
                }
            }
            if(userId.length() > 50) {
                userErrorDTO.setUserIdError("this length must be shorter than 50 characters!!!");
                valid = false;
            }
            if(fullName.length() > 50) {
                userErrorDTO.setFullNameError("this length must be shorter than 50 characters!!!");
                valid = false;
            }
            if(password.length() > 100) {
                userErrorDTO.setPasswordError("this length must be shorter than 100 characters!!!");
                valid = false;
            }
            if(userId.equals("")) {
                userErrorDTO.setUserIdError("This must not be empty!!!");
                valid  = false;
            }
            if (password.equals("")) {
                userErrorDTO.setPasswordError("this must not be empty!!!");
                valid = false;
            }
            if (rePassword.equals("")) {
                userErrorDTO.setRePassWordError("this must not be empty!!!");
                valid = false;
            }
            if (!password.equals(rePassword)) {
                userErrorDTO.setRePassWordError("Password and re-password is not match!!!");
                valid = false;
            }
            if (valid) {
                user.setPassword(hoald.utils.Util.getSHA256(password));
                usersBLO.registerAccount(user);
                url = VERIFY_ACCOUNT;
            }
            request.setAttribute("RE_PASSWORD", rePassword);
            request.setAttribute("USER_ERROR_DTO", userErrorDTO);
            request.setAttribute("USER_DTO", user);

        } catch (Exception e) {
            Logger.getLogger("Error at RegisterController: " + e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    private boolean checkEmailFormat(String email) {
        String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
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
