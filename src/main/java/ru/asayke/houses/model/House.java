package ru.asayke.houses.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "houses")
public class House extends BaseEntity {
    @Column(name = "address")
    private String address;

    @Column(name = "owner_id")
    private Long ownerId;
}