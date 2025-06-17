package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserProfileResponseLombokModel {

    private Data data;
    private Support support;

    @lombok.Data
    public static class Data {
        private String id;
        private String email;

        @JsonProperty("first_name")  // Если JSON использует snake_case, а Java — camelCase
        private String firstName;
        @JsonProperty("last_name")   // Аннотация Jackson для маппинга полей
        private String lastName;
        private String avatar;
    }

    @lombok.Data
    public static class Support {
        private String url;
        private String text;
    }
}
