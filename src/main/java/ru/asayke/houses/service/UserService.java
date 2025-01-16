package ru.asayke.houses.service;

import ru.asayke.houses.model.User;

public interface UserService {
    void register(User user);
    User findByUsername(String username);
    //TODO лишнее удали
    void save(User user);
    void deleteById(Long id);
    void updateUserName(String username, String newName);
    void updateUserAge(String username, Integer newAge);
    void deleteUser(String username);
}