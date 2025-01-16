package ru.asayke.houses.dto.fieldsDTO.house;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
//TODO замеить @Data аналогично с сущностями
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDTO {
    private String address;
}