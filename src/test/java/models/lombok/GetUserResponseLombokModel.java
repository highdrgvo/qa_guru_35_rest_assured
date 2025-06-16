package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class GetUserResponseLombokModel {

    private int page, per_page, total, total_pages;
    private List<User> data;
    private Support support;

    @lombok.Data
    public static class User {
        private String id;
        private String email;
        @JsonProperty("first_name")
        private String firstName;
        @JsonProperty("last_name")
        private String lastName;
        private String avatar;
    }

    @lombok.Data
    public static class Support {
        private String url;
        private String text;
    }
}
