package ru.asayke.houses.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asayke.houses.dto.MemberDTO;
import ru.asayke.houses.dto.HouseDTO;
import ru.asayke.houses.dto.fieldsDTO.house.AddressDTO;
import ru.asayke.houses.model.House;
import ru.asayke.houses.service.HouseService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/house/")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;
    private final ModelMapper mapper;

    @PostMapping(value = "/add-as-owner")
    public ResponseEntity<HttpStatus> addNewHouseAsOwner(Principal principal, @RequestBody AddressDTO addressDTO) {
        String username = principal.getName();

        houseService.saveAsOwner(username, addressDTO.getAddress());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/find-all-by-owner")
    public ResponseEntity<List<HouseDTO>> findAllByOwner(Principal principal) {
        String username = principal.getName();

        return ResponseEntity.ok(houseService.findAllByOwner(username).stream().map(this::convertToHouseDTO).collect(Collectors.toList()));
    }

    @PostMapping(value = "/add-new-member")
    public ResponseEntity<HttpStatus> addNewMember(Principal principal, @RequestBody MemberDTO addMemberDTO) {
        String username = principal.getName();

        houseService.addNewMember(username, addMemberDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/find-all-by-user")
    public ResponseEntity<List<HouseDTO>> findAllByUser(Principal principal) {
        String username = principal.getName();

        return ResponseEntity.ok(houseService.findAllByUser(username).stream().map(this::convertToHouseDTO).collect(Collectors.toList()));
    }

    @PostMapping(value = "/delete-member")
    public ResponseEntity<HttpStatus> deleteMember(Principal principal, @RequestBody MemberDTO memberDTO) {
        String username = principal.getName();
        //TODO FIX THIS
        houseService.deleteMember(username, memberDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private HouseDTO convertToHouseDTO(House house) {
        return mapper.map(house, HouseDTO.class);
    }
}