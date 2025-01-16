package ru.asayke.houses.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

//TODO убрать @Data, использовать только Getter Setter и возможно Equals и Hashcode если где-то используется
//TODO Использовать @FieldsDefault для того чтобы убрать private и писать его под капотом
@Data
@MappedSuperclass
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    @Column(name = "created")
    private Date created = Date.from(Instant.now());

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status = Status.ACTIVE;
}