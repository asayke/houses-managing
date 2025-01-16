package ru.asayke.houses.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

//TODO убрать @Data, использовать только Getter Setter и возможно Equals и Hashcode если где-то используется
//TODO Использовать @FieldsDefault для того чтобы убрать private и писать его под капотом
@Data
@Entity
@Table(name = "roles")
public class Role extends BaseEntity {
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;
}