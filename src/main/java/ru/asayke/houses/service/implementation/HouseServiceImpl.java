package ru.asayke.houses.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asayke.houses.model.House;
import ru.asayke.houses.model.User;
import ru.asayke.houses.repository.HouseRepository;
import ru.asayke.houses.repository.UserRepository;
import ru.asayke.houses.service.HouseService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void saveAsOwner(String username, String address) {
        User user = userRepository.findByUsername(username);

        House house = new House();
        house.setAddress(address);
        house.setOwnerId(user.getId());
        houseRepository.save(house);

        List<House> houses = user.getHouses();
        houses.add(house);
        user.setHouses(houses);

        userRepository.save(user);
    }

    @Override
    public List<House> findAllByOwner(String username) {
        User user = userRepository.findByUsername(username);

        return houseRepository.findAllByOwnerId(user.getId());
    }
}