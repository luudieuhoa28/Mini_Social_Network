/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */package hoald.user;

/**
 *
 * @author dell
 */
public class UserErrorDTO {
    private String userIdError;
    private String fullNameError;
    private String passwordError;
    private String rePassWordError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String userIdError, String fullNameError, String passwordError, String rePassWordError) {
        this.userIdError = userIdError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.rePassWordError = rePassWordError;
    }

    

    public String getRePassWordError() {
        return rePassWordError;
    }

    public void setRePassWordError(String rePassWordError) {
        this.rePassWordError = rePassWordError;
    }
    
    

    public String getUserIdError() {
        return userIdError;
    }

    public void setUserIdError(String userIdError) {
        this.userIdError = userIdError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
    
    
    
}
