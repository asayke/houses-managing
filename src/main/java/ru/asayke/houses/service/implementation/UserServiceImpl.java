package ru.asayke.houses.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asayke.houses.model.Role;
import ru.asayke.houses.model.Status;
import ru.asayke.houses.model.User;
import ru.asayke.houses.repository.HouseRepository;
import ru.asayke.houses.repository.RoleRepository;
import ru.asayke.houses.repository.UserRepository;
import ru.asayke.houses.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final HouseRepository houseRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(User user) {
        Role roleUser =  roleRepository.findByName("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        roles.add(roleUser);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        user.setStatus(Status.ACTIVE);

        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void updateUserName(String username, String newName) {
        User user = userRepository.findByUsername(username);
        user.setName(newName);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserAge(String username, Integer newAge) {
        User user = userRepository.findByUsername(username);
        user.setAge(newAge);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        houseRepository.deleteAllByOwnerId(user.getId());
        userRepository.deleteById(user.getId());
    }
}