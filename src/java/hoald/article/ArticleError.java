/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoald.article;

/**
 *
 * @author dell
 */
public class ArticleError {
    private String titleError;
    private String descriptionError;
    private String imageError;
    private String emptyError;

    public ArticleError() {
    }

    public ArticleError(String titleError, String desciptionError, String imageError) {
        this.titleError = titleError;
        this.descriptionError = desciptionError;
        this.imageError = imageError;
    }

    public String getEmptyError() {
        return emptyError;
    }

    public void setEmptyError(String emptyError) {
        this.emptyError = emptyError;
    }
    
    

    public String getTitleError() {
        return titleError;
    }

    public void setTitleError(String titleError) {
        this.titleError = titleError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String desciptionError) {
        this.descriptionError = desciptionError;
    }

    public String getImageError() {
        return imageError;
    }

    public void setImageError(String imageError) {
        this.imageError = imageError;
    }
}
