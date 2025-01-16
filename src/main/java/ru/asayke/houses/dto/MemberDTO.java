package ru.asayke.houses.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
//TODO замеить @Data аналогично с сущностями
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MemberDTO {
    private Long houseId;
    private Long newMemberId;
}