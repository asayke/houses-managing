package ru.asayke.houses.service;

import ru.asayke.houses.model.House;

import java.util.List;

public interface HouseService {
    void saveAsOwner(String username, String address);
    List<House> findAllByOwner(String username);
}