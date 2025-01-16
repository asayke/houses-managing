package ru.asayke.houses.model;

import lombok.Data;

import javax.persistence.*;

//TODO убрать @Data, использовать только Getter Setter и возможно Equals и Hashcode если где-то используется
//TODO Использовать @FieldsDefault для того чтобы убрать private и писать его под капотом
@Data
@Entity
@Table(name = "houses")
public class House extends BaseEntity {
    @Column(name = "address")
    private String address;

    @Column(name = "owner_id")
    private Long ownerId;
}