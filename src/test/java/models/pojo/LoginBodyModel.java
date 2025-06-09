package models.pojo;

public class LoginBodyModel {

    String email, password, headerApiKey;

    public String getHeaderApiKey() {
        return headerApiKey;
    }

    public void setHeaderApiKey(String headerApiKey) {
        this.headerApiKey = headerApiKey;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
