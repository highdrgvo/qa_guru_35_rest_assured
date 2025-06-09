package models.lombok;

import lombok.Data;

@Data
public class LoginBodyLombokModel {

    String email, password;

    public String getApiKey() {
        String apiKey = "reqres-free-v1";
        return apiKey;
    }

}
