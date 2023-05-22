package ru.asayke.houses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asayke.houses.model.Role;
import ru.asayke.houses.model.User;

@Repository
public interface RoleRepository extends JpaRepository<User, Long> {
    Role findByName(String roleName);
}