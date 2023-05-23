package ru.asayke.houses.dto.fieldsDTO.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class NameDTO {
    private String name;
}