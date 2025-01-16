package ru.asayke.houses.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
//TODO замеить @Data аналогично с сущностями
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegistrationRequest {
    private final String username;
    private String name;
    private Integer age;
    private final String password;
}