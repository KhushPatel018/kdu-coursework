package com.kdu.smarthome.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kdu.smarthome.constant.RoleUser;
import com.kdu.smarthome.dto.request.AddUserRequestDTO;
import com.kdu.smarthome.dto.request.RegisterHouseRequestDTO;
import com.kdu.smarthome.dto.request.UpdateAddressRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.dto.response.HouseListResponseDTO;
import com.kdu.smarthome.dto.response.HouseResponseDTO;
import com.kdu.smarthome.dto.response.RoomListResponseDTO;
import com.kdu.smarthome.entity.*;
import com.kdu.smarthome.exception.DataRetrievalFailedException;
import com.kdu.smarthome.exception.EntityNotFoundException;
import com.kdu.smarthome.exception.UnauthorizedUserAccessException;
import com.kdu.smarthome.exception.UnprocessableEntityException;
import com.kdu.smarthome.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseOwnerRepository houseOwnerRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;
    private final TokenService tokenService;
    private final MapperService mapperService;

    String userNotExist = "User with given username does not exist";
    String houseNotExist = "House with given id does not exist";

    public HouseService(HouseRepository houseRepository, HouseOwnerRepository houseOwnerRepository, UserRepository userRepository, RoomRepository roomRepository, DeviceRepository deviceRepository, TokenService tokenService, MapperService mapperService) {
        this.houseRepository = houseRepository;
        this.houseOwnerRepository = houseOwnerRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.tokenService = tokenService;
        this.mapperService = mapperService;
    }


    public HouseResponseDTO addHouse(RegisterHouseRequestDTO registerHouseRequestDTO, String token) {
        try {
            User user = userRepository.findById(tokenService.extractUsername(token.substring(7))).orElseThrow(() -> new EntityNotFoundException(userNotExist));
            House house = houseRepository.save(mapperService.toEntity(registerHouseRequestDTO));
            houseOwnerRepository.save(mapperService.toEntity(house, user, RoleUser.ADMIN));
            return mapperService.toDTO(house, "House added successfully");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("House cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("House cannot be added please check again");
        }
    }

    public GlobalResponseDTO addUserToHouse(String houseId, AddUserRequestDTO addUserRequestDTO, String token) throws JsonProcessingException {
        try {
            User admin = userRepository.findById(tokenService.extractUsername(token.substring(7))).orElseThrow(() -> new EntityNotFoundException(userNotExist));
            User user = userRepository.findById(addUserRequestDTO.getUsername()).orElseThrow(() -> new EntityNotFoundException(userNotExist));

            House house = houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException(houseNotExist));

            HouseOwner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, admin.getUsername());

            if (!(Objects.isNull(houseOwner)) && houseOwner.getRole().equals(RoleUser.ADMIN)) {
                houseOwnerRepository.save(mapperService.toEntity(house, user, RoleUser.USER));
            } else {
                throw new UnauthorizedUserAccessException("User with given username is not admin of the house");
            }
            return mapperService.toDTOGlobal(house, "User added to house successfully");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User cannot be added. " + e.getMessage());
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("User cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("User cannot be added please check again");
        }
    }

    public HouseListResponseDTO getAllHouses() {
        try {
            List<House> houseList = houseRepository.findAll();
            return mapperService.toDTO("House List fetched successfully", houseList);
        } catch (Exception e) {
            throw new DataRetrievalFailedException("Failed to fetch house list");
        }
    }


    public GlobalResponseDTO updateHouseAddress(String houseId, UpdateAddressRequestDTO houseDTO, String token) {
        try {
            User user = userRepository.findById(tokenService.extractUsername(token.substring(7))).orElse(null);
            if (Objects.isNull(user)) {
                throw new EntityNotFoundException(userNotExist);
            }
            House house = houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException(houseNotExist));
            HouseOwner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, user.getUsername());
            if (Objects.isNull(houseOwner)) {
                throw new UnauthorizedUserAccessException("User with given username is not owner of the house");
            }
            house.setAddress(houseDTO.getAddress());
            house = houseRepository.save(house);
            return mapperService.toDTOGlobal(house, "House address updated successfully");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("House cannot be updated. " + e.getMessage());
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("House cannot be updated. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("House cannot be updated please check again");
        }
    }

    public RoomListResponseDTO getHouseDetails(String houseId) {
        try {
            House house = houseRepository.findById(houseId).orElse(null);
            if (Objects.isNull(house)) {
                throw new EntityNotFoundException(houseNotExist);
            }
            List<Room> roomList = roomRepository.findByHouse_Id(houseId);
            List<Device> deviceList = deviceRepository.findByHouse_Id(houseId);
            List<Object> houseList = new ArrayList<>();
            houseList.addAll(roomList);
            houseList.addAll(deviceList);
            return mapperService.toDTO(houseList, "House details fetched successfully");

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("House details cannot be fetched. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("House details cannot be fetched please check again");
        }
    }
}
