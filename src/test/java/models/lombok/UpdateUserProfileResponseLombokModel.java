package models.lombok;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UpdateUserProfileResponseLombokModel {

    String name, job, updatedAt;
}
