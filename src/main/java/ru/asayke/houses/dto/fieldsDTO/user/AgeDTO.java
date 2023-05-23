package ru.asayke.houses.dto.fieldsDTO.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgeDTO {
    private Integer age;
}