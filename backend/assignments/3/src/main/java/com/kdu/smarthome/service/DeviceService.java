package com.kdu.smarthome.service;

import com.kdu.smarthome.constant.RoleUser;
import com.kdu.smarthome.dto.request.AddDeviceRequestDTO;
import com.kdu.smarthome.dto.request.RegisterDeviceRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.entity.*;
import com.kdu.smarthome.exception.EntityNotFoundException;
import com.kdu.smarthome.exception.UnauthorizedUserAccessException;
import com.kdu.smarthome.exception.UnprocessableEntityException;
import com.kdu.smarthome.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Service class for handling device-related operations.
 */
@Service
@Slf4j
public class DeviceService {
    private final UserRepository userRepository;
    private final DeviceRepository deviceRepository;
    private final TokenService tokenService;
    private final MapperService mapperService;
    private final InventoryRepository inventoryRepository;
    private final HouseRepository houseRepository;
    private final HouseOwnerRepository houseOwnerRepository;
    private final RoomRepository roomRepository;

    /**
     * Constructor for DeviceService.
     *
     * @param userRepository         The repository for user data.
     * @param deviceRepository       The repository for device data.
     * @param tokenService           The service for handling authentication tokens.
     * @param mapperService          The service for mapping DTOs to entities.
     * @param inventoryRepository    The repository for inventory data.
     * @param houseRepository        The repository for house data.
     * @param houseOwnerRepository   The repository for house owner data.
     * @param roomRepository         The repository for room data.
     */
    public DeviceService(UserRepository userRepository, DeviceRepository deviceRepository, TokenService tokenService, MapperService mapperService, InventoryRepository inventoryRepository, HouseRepository houseRepository, HouseOwnerRepository houseOwnerRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.deviceRepository = deviceRepository;
        this.tokenService = tokenService;
        this.mapperService = mapperService;
        this.inventoryRepository = inventoryRepository;
        this.houseRepository = houseRepository;
        this.houseOwnerRepository = houseOwnerRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Registers a new device.
     *
     * @param registerDeviceRequestDTO  The DTO containing device registration information.
     * @param token                      The authentication token.
     * @return                           The response DTO indicating the result of the registration process.
     * @throws UnauthorizedUserAccessException  If the user is not authorized to perform the operation.
     * @throws EntityNotFoundException          If the requested entity is not found.
     * @throws UnprocessableEntityException     If an error occurs during the registration process.
     */
    public GlobalResponseDTO registerDevice(RegisterDeviceRequestDTO registerDeviceRequestDTO, String token) {
        try {
            // Extract username from token
            String username = tokenService.extractUsername(token.substring(7)); // bearer removal
            // Retrieve user
            User user = userRepository.findById(username).orElseThrow(() -> new UnauthorizedUserAccessException("Invalid token"));
            // Retrieve inventory
            Inventory inventory = inventoryRepository.findById(registerDeviceRequestDTO.getKickstonId()).orElseThrow(()->new EntityNotFoundException("Device with given id doesn't found in inventory"));
            log.info(user.toString());
            // Check device credentials
            if (!(inventory.getDevicePassword().equals(registerDeviceRequestDTO.getDevicePassword()) && inventory.getDeviceUsername().equals(registerDeviceRequestDTO.getDeviceUsername()))) {
                throw new UnauthorizedUserAccessException("Invalid credentials");
            }

            return mapperService.toDTOGlobal(deviceRepository.save(mapperService.toEntity(registerDeviceRequestDTO)), "Device registered successfully");

        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("Device can't be registered. " + e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Device can't be registered. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Device can't be registered ");
        }
    }

    /**
     * Adds a device to a house.
     *
     * @param addDeviceRequestDTO   The DTO containing information about the device to be added.
     * @param token                 The authentication token.
     * @return                      The response DTO indicating the result of the operation.
     * @throws UnauthorizedUserAccessException  If the user is not authorized to perform the operation.
     * @throws EntityNotFoundException          If the requested entity is not found.
     * @throws UnprocessableEntityException     If an error occurs during the process.
     */
    public GlobalResponseDTO addDeviceToHouse(AddDeviceRequestDTO addDeviceRequestDTO, String token) {
        try {
            // Extract username from token
            String username = tokenService.extractUsername(token.substring(7));
            // Retrieve user
            User user = userRepository.findById(username).orElseThrow(()->new UnauthorizedUserAccessException("Invalid token"));
            log.info(user.toString());
            // Retrieve house
            House house = houseRepository.findById(addDeviceRequestDTO.getHouseId()).orElseThrow(null);
            // Check user's role in the house
            if (!(houseOwnerRepository.findByHouse_IdAndUser_Username(house.getId(), username).getRole().equals(RoleUser.ADMIN))) {
                throw new UnauthorizedUserAccessException("User should be admin of the house for this functionality");
            }
            // Retrieve device
            Device device = deviceRepository.findById(addDeviceRequestDTO.getKickstonId()).orElseThrow(()->new EntityNotFoundException("Device with given id doesn't found"));
            // Retrieve room
            Room room = roomRepository.findById(addDeviceRequestDTO.getRoomId()).orElseThrow(()->new EntityNotFoundException("Room with given id doesn't found"));
            // Set house and room for device
            device.setHouse(house);
            device.setRoom(room);
            return mapperService.toDTOGlobal(deviceRepository.save(device), "Device added into the room");
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("Device can't be added. " + e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Device can't be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Device can't be added into house ");
        }
    }
}
