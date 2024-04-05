package com.kdu.smarthome.service;

import com.kdu.smarthome.constant.RoleUser;
import com.kdu.smarthome.dto.request.*;
import com.kdu.smarthome.dto.response.*;
import com.kdu.smarthome.entity.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for mapping DTOs to entities and vice versa.
 */
@Service
public class MapperService {
    /**
     * Maps a RegisterDeviceRequestDTO to a Device entity.
     *
     * @param registerDeviceRequestDTO The DTO containing device registration information.
     * @return                          The mapped Device entity.
     */
    public Device toEntity(RegisterDeviceRequestDTO registerDeviceRequestDTO) {
        return Device.builder()
                .deviceUsername(registerDeviceRequestDTO.getDeviceUsername())
                .devicePassword(registerDeviceRequestDTO.getDevicePassword())
                .kickstonId(registerDeviceRequestDTO.getKickstonId())
                .build();
    }

    /**
     * Maps a RegisterHouseRequestDTO to a House entity.
     *
     * @param registerHouseRequestDTO The DTO containing house registration information.
     * @return                         The mapped House entity.
     */
    public House toEntity(RegisterHouseRequestDTO registerHouseRequestDTO) {
        return House.builder()
                .address(registerHouseRequestDTO.getAddress())
                .houseName(registerHouseRequestDTO.getHouseName())
                .build();
    }

    /**
     * Maps a House, User, and RoleUser to a HouseOwner entity.
     *
     * @param house    The house entity.
     * @param user     The user entity.
     * @param roleUser The role of the user in the house.
     * @return         The mapped HouseOwner entity.
     */
    public HouseOwner toEntity(House house, User user, RoleUser roleUser) {
        return HouseOwner.builder()
                .house(house)
                .user(user)
                .role(roleUser)
                .build();
    }

    /**
     * Maps a House and AddRoomRequestDTO to a Room entity.
     *
     * @param house            The house entity.
     * @param addRoomRequestDTO The DTO containing room addition information.
     * @return                 The mapped Room entity.
     */
    public Room toEntity(House house, AddRoomRequestDTO addRoomRequestDTO) {
        return Room.builder()
                .roomName(addRoomRequestDTO.getRoomName())
                .house(house)
                .build();
    }

    /**
     * Maps a RegisterUserRequestDTO to a User entity.
     *
     * @param registerUserRequestDTO The DTO containing user registration information.
     * @return                        The mapped User entity.
     */
    public User toEntity(RegisterUserRequestDTO registerUserRequestDTO) {
        return User.builder()
                .username(registerUserRequestDTO.getUsername())
                .password(registerUserRequestDTO.getPassword())
                .emailId(registerUserRequestDTO.getEmailId())
                .name(registerUserRequestDTO.getName())
                .build();
    }

    /**
     * Maps an AddInventoryRequestDTO to an Inventory entity.
     *
     * @param addInventoryRequestDTO The DTO containing inventory addition information.
     * @return                        The mapped Inventory entity.
     */
    public Inventory toEntity(AddInventoryRequestDTO addInventoryRequestDTO) {
        return Inventory.builder()
                .kickstonId(addInventoryRequestDTO.getKickstonId())
                .deviceUsername(addInventoryRequestDTO.getDeviceUsername())
                .devicePassword(addInventoryRequestDTO.getDevicePassword())
                .manufactureDateTime(addInventoryRequestDTO.getManufactureDateTime())
                .manufactureFactoryPlace(addInventoryRequestDTO.getManufactureFactoryPlace())
                .build();
    }

    // DTO to ResponseDTO mappings

    public HouseResponseDTO toDTO(House house, String message) {
        return HouseResponseDTO.builder()
                .message(message)
                .house(house)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public InventoryResponseDTO toDTO(List<Inventory> inventoryList) {
        return InventoryResponseDTO.builder()
                .inventory(inventoryList.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public HouseListResponseDTO toDTO(String message, List<House> houseList) {
        return HouseListResponseDTO.builder()
                .houses(houseList.toString())
                .response(message)
                .responseStatus(HttpStatus.OK.value())
                .build();
    }

    public RoomListResponseDTO toDTO(List<Object> houseList, String message) {
        return RoomListResponseDTO.builder()
                .message(message)
                .roomsAndDevices(houseList.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public RoomResponseDTO toDTO(Room room, String message) {
        return RoomResponseDTO.builder()
                .message(message)
                .room(room)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public GlobalResponseDTO toDTOGlobal(Object object , String message) {
        return GlobalResponseDTO.builder()
                .message(message)
                .object(object.toString())
                .httpStatus(HttpStatus.OK)
                .build();
    }
}
