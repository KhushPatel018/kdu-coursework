package com.kdu.smarthome.service;

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

/**
 * Service class for handling house-related operations.
 */
@Service
public class HouseService {
    private final HouseRepository houseRepository;
    private final HouseOwnerRepository houseOwnerRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final DeviceRepository deviceRepository;
    private final TokenService tokenService;
    private final MapperService mapperService;

    // to avoid sonar lint
    String houseNotFound = "House with given id does not found";
    String userNotFound = "User with given username does not found";

    /**
     * Constructor for HouseService.
     *
     * @param houseRepository       The repository for house data.
     * @param houseOwnerRepository  The repository for house owner data.
     * @param userRepository        The repository for user data.
     * @param roomRepository        The repository for room data.
     * @param deviceRepository      The repository for device data.
     * @param tokenService          The service for handling authentication tokens.
     * @param mapperService         The service for mapping DTOs to entities.
     */
    public HouseService(HouseRepository houseRepository, HouseOwnerRepository houseOwnerRepository, UserRepository userRepository, RoomRepository roomRepository, DeviceRepository deviceRepository, TokenService tokenService, MapperService mapperService) {
        this.houseRepository = houseRepository;
        this.houseOwnerRepository = houseOwnerRepository;
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.tokenService = tokenService;
        this.mapperService = mapperService;
    }

    /**
     * Registers a new house.
     *
     * @param registerHouseRequestDTO The DTO containing house registration information.
     * @param token                    The authentication token.
     * @return                         The response DTO indicating the result of the registration process.
     * @throws EntityNotFoundException      If the requested entity is not found.
     * @throws UnprocessableEntityException If an error occurs during the registration process.
     */
    public HouseResponseDTO addHouse(RegisterHouseRequestDTO registerHouseRequestDTO, String token) {
        try {
            User user = userRepository.findById(tokenService.extractUsername(token.substring(7))).orElseThrow(() -> new EntityNotFoundException(userNotFound));
            House house = houseRepository.save(mapperService.toEntity(registerHouseRequestDTO));
            houseOwnerRepository.save(mapperService.toEntity(house, user, RoleUser.ADMIN));
            return mapperService.toDTO(house, "House added ");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("House can't be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("House can't be added ");
        }
    }

    /**
     * Adds a user to a house.
     *
     * @param houseId              The ID of the house.
     * @param addUserRequestDTO    The DTO containing information about the user to be added.
     * @param token                The authentication token.
     * @return                     The response DTO indicating the result of the operation.
     * @throws EntityNotFoundException      If the requested entity is not found.
     * @throws UnauthorizedUserAccessException If the user is not authorized to perform the operation.
     * @throws UnprocessableEntityException If an error occurs during the process.
     */
    public GlobalResponseDTO addUserToHouse(String houseId, AddUserRequestDTO addUserRequestDTO, String token) {
        try {
            User admin = userRepository.findById(tokenService.extractUsername(token.substring(7))).orElseThrow(() -> new EntityNotFoundException(userNotFound));
            User user = userRepository.findById(addUserRequestDTO.getUsername()).orElseThrow(() -> new EntityNotFoundException(userNotFound));

            House house = houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException(houseNotFound));

            HouseOwner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, admin.getUsername());

            if (!(Objects.isNull(houseOwner)) && houseOwner.getRole().equals(RoleUser.ADMIN)) {
                houseOwnerRepository.save(mapperService.toEntity(house, user, RoleUser.USER));
            } else {
                throw new UnauthorizedUserAccessException("User should be admin of the house for this functionality");
            }
            return mapperService.toDTOGlobal(house, "User added into the house ");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User can't be added. " + e.getMessage());
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("User can't be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("User can't be added ");
        }
    }

    /**
     * Retrieves a list of all houses.
     *
     * @return The response DTO containing the list of houses.
     * @throws DataRetrievalFailedException If the data retrieval process fails.
     */
    public HouseListResponseDTO getAllHouses() {
        try {
            List<House> houseList = houseRepository.findAll();
            return mapperService.toDTO("House List retrieveed ", houseList);
        } catch (Exception e) {
            throw new DataRetrievalFailedException("Failed to retrieve house list");
        }
    }

    /**
     * Updates the address of a house.
     *
     * @param houseId       The ID of the house.
     * @param houseDTO      The DTO containing the updated address information.
     * @param token         The authentication token.
     * @return              The response DTO indicating the result of the operation.
     * @throws EntityNotFoundException      If the requested entity is not found.
     * @throws UnauthorizedUserAccessException If the user is not authorized to perform the operation.
     * @throws UnprocessableEntityException If an error occurs during the process.
     */
    public GlobalResponseDTO updateHouseAddress(String houseId, UpdateAddressRequestDTO houseDTO, String token) {
        try {
            User user = userRepository.findById(tokenService.extractUsername(token.substring(7))).orElseThrow(()->new EntityNotFoundException(userNotFound));
            House house = houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException(houseNotFound));
            HouseOwner houseOwner = houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, user.getUsername());
            if (Objects.isNull(houseOwner)) {
                throw new UnauthorizedUserAccessException("User should be owner of the house");
            }
            house.setAddress(houseDTO.getAddress());
            house = houseRepository.save(house);
            return mapperService.toDTOGlobal(house, "House address updated ");
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("House can't be updated. " + e.getMessage());
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("House can't be updated. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("House can't be updated ");
        }
    }

    /**
     * Retrieves details of a house.
     *
     * @param houseId       The ID of the house.
     * @return              The response DTO containing the details of the house.
     * @throws EntityNotFoundException      If the requested entity is not found.
     * @throws UnprocessableEntityException If an error occurs during the process.
     */
    public RoomListResponseDTO getHouseDetails(String houseId) {
        try {
            House house = houseRepository.findById(houseId).orElse(null);
            if (Objects.isNull(house)) {
                throw new EntityNotFoundException(houseNotFound);
            }
            List<Room> roomList = roomRepository.findByHouse_Id(houseId);
            List<Device> deviceList = deviceRepository.findByHouse_Id(houseId);
            List<Object> houseList = new ArrayList<>();
            houseList.addAll(roomList);
            houseList.addAll(deviceList);
            return mapperService.toDTO(houseList, "House details retrieved ");

        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("House details can't be retrieved" + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("House details can't be retrieved");
        }
    }
}
