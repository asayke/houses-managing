package ru.asayke.houses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.asayke.houses.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    //TODO Лучше возвращать Optional<Role>
    Role findByName(String roleName);
}