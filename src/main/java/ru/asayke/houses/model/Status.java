package ru.asayke.houses.model;

//TODO убрать @Data, использовать только Getter Setter и возможно Equals и Hashcode если где-то используется
//TODO Использовать @FieldsDefault для того чтобы убрать private и писать его под капотом
public enum Status {
    ACTIVE,
    NOT_ACTIVE,
    DELETED
}