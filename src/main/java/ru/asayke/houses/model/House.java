package ru.asayke.houses.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "houses")
public class House extends BaseEntity {
    @Column(name = "address")
    private String address;

    @Column(name = "owner_id")
    private Integer owner_id;

    @ManyToMany(mappedBy = "houses", fetch = FetchType.LAZY)
    private List<User> users;
}