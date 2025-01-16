package ru.asayke.houses.model;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

//TODO убрать @Data, использовать только Getter Setter и возможно Equals и Hashcode если где-то используется
//TODO Использовать @FieldsDefault для того чтобы убрать private и писать его под капотом
@Data
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(name = "username")
    private String username;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    //TODO почему здесь @Fetch(value = FetchMode.SUBSELECT), скорее всего был тупой копипаст и можно убрать
    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    @JoinTable(name = "user_houses", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "house_id", referencedColumnName = "id")})
    private List<House> houses;
}