/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.article;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hoald.user.Users;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * @author dell
 */
public class PostArticleController extends HttpServlet {

    // public static final String POST_ARTICLE_SUCCESS = "MainController?btnAction=NewsFeed";
    public static final String POST_ARTICLE_SUCCESS = "NewsFeedController";
    public static final String POST_ARTICLE_FAIL = "error.jsp";

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
        String url = POST_ARTICLE_FAIL;
        try {
            String title = "";
            String description = "";
            String imagePath = "";
            boolean isValid = true;
            ArticleError articleError = new ArticleError();
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : items) {
                    if (item.isFormField()) {
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                        String fieldName = item.getFieldName();
                        String fieldValue = item.getString("utf-8");
                        switch (fieldName) {
                            case "title":
                                title = fieldValue;
                                break;
                            case "description":
                                description = fieldValue;
                                break;
                            case "imagePath":
                                imagePath = fieldValue;
                                break;
                        }
                    } else {
                        // Process form file field (input type="file").
                        String fieldName = item.getFieldName();
                        if (fieldName.equals("imagePath")) {
                            String fileName = item.getName();
                            if (!fileName.equals("")) {
                                fileName = getFileName(fileName);
                            }

                            if (!fileName.equals("") && (fileName.endsWith("png") || fileName.endsWith("bmp") || fileName.endsWith("jpg")
                                    || fileName.endsWith("PNG") || fileName.endsWith("BMP") || fileName.endsWith("JPG"))) {
                                String realPath = getServletContext().getRealPath("/") + "images\\" + fileName;
                                File saveFile = new File(realPath);
                                item.write(saveFile);
                                imagePath = realPath.substring(realPath.lastIndexOf("\\") + 1);
                            } else if (!fileName.equals("")) {
                                articleError.setImageError("Choose file type png or bmp or jpg");
                                isValid = false;
                            }
                        }

                    }
                }
                if (description.length() > 4000) {
                    isValid = false;
                    articleError.setDescriptionError("This length must be shoter than 4000 characters");
                }
                if (title.length() > 50) {
                    isValid = false;
                    articleError.setTitleError("This length must be shorter than 50 characters");
                }

                if (description.equals("") && imagePath.equals("")) {
                    articleError.setEmptyError("Please enter description or choose image to post!!!");
                    isValid = false;

                }
                HttpSession session = request.getSession();
                Article article = new Article(title, description, imagePath, new Date(), true, (Users) session.getAttribute("USER_DTO"));
                if (isValid) {
                    article = new Article(title, description, imagePath, new Date(), true, (Users) session.getAttribute("USER_DTO"));
                    ArticleBLO articleBLO = new ArticleBLO();
                    articleBLO.insertArticle(article);
                } else {
                    request.setAttribute("ARTICLE", article);
                }
                request.setAttribute("ARTICLE_ERROR", articleError);

            } catch (FileUploadException e) {
                Logger.getLogger("Cannot parse multipart request." + e);
                
            }
            url = POST_ARTICLE_SUCCESS;
        } catch (Exception e) {
            Logger.getLogger("Error at PostArticleController: " + e);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    private String getFileName(String fileName) {
        try {
            fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);
            String tmpFileName = fileName.substring(0, fileName.lastIndexOf(".") - 1);
            String imgType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            fileName = tmpFileName + new Date().getTime() + imgType;
            return fileName;
        } catch (Exception e) {
            return "";
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
