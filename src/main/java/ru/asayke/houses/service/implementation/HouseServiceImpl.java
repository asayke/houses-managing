package ru.asayke.houses.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asayke.houses.dto.MemberDTO;
import ru.asayke.houses.model.House;
import ru.asayke.houses.model.User;
import ru.asayke.houses.repository.HouseRepository;
import ru.asayke.houses.repository.UserRepository;
import ru.asayke.houses.service.HouseService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Override
    @Transactional
    public void addNewMember(String username, MemberDTO addMemberDTO) {
        User user = userRepository.findByUsername(username);

        if(Objects.equals(addMemberDTO.getNewMemberId(), user.getId())) return;

        House house = houseRepository.getById(addMemberDTO.getHouseId());

        if(!Objects.equals(user.getId(), house.getOwnerId())) return;

        User newMember = userRepository.getById(addMemberDTO.getNewMemberId());

        List<House> houses = newMember.getHouses();
        houses.add(house);
        newMember.setHouses(houses);

        userRepository.save(newMember);
    }

    @Override
    public List<House> findAllByUser(String username) {
        User user = userRepository.findByUsername(username);

        return user.getHouses();
    }

    @Override
    @Transactional
    public void deleteMember(String username, MemberDTO memberDTO) {
        User user = userRepository.findByUsername(username);

        if(Objects.equals(memberDTO.getNewMemberId(), user.getId())) return;

        House house = houseRepository.getById(memberDTO.getHouseId());

        if(!Objects.equals(user.getId(), house.getOwnerId())) return;

        User member = userRepository.getById(memberDTO.getNewMemberId());

        List<House> newHouses = member.getHouses().stream().filter(h -> !Objects.equals(h.getId(), house.getId())).collect(Collectors.toList());
        member.setHouses(newHouses);

        userRepository.save(member);
    }
}