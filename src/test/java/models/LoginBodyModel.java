package models;

public class LoginBodyModel {

//    String authData = "{\n" +
//                "    \"email\": \"eve.holt@reqres.in\",\n" +
//                "    \"password\": \"cityslicka\"\n" +
//                "}";

    String email, password, headerApiKey;

    public String getHeaderApiKey() {
        return headerApiKey;
    }

    public void setHeaderApiKey(String headerApiKey) {
        this.headerApiKey = headerApiKey;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
