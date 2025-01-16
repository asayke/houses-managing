package ru.asayke.houses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asayke.houses.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //TODO Лучше возвращать Optional<User>
    User findByUsername(String username);
}