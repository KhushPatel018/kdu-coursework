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


    public GlobalResponseDTO registerDevice(RegisterDeviceRequestDTO registerDeviceRequestDTO, String token) {
        try {
            String username = tokenService.extractUsername(token.substring(7)); // bearer removal
            User user = userRepository.findById(username).orElseThrow(() -> new UnauthorizedUserAccessException("Invalid username please check token"));
            Inventory inventory = inventoryRepository.findById(registerDeviceRequestDTO.getKickstonId()).orElseThrow(()->new EntityNotFoundException("Device with given id does not exist in inventory"));
            log.info(user.toString());
            if (!(inventory.getDevicePassword().equals(registerDeviceRequestDTO.getDevicePassword()) && inventory.getDeviceUsername().equals(registerDeviceRequestDTO.getDeviceUsername()))) {
                throw new UnauthorizedUserAccessException("Invalid credentials please check again");
            }

            return mapperService.toDTOGlobal(deviceRepository.save(mapperService.toEntity(registerDeviceRequestDTO)), "Device registered successfully");

        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("Device cannot be registered. " + e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Device cannot be registered. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Device cannot be registered please check again");
        }
    }

    public GlobalResponseDTO addDeviceToHouse(AddDeviceRequestDTO addDeviceRequestDTO, String token) {
        try {
            String username = tokenService.extractUsername(token.substring(7));
            User user = userRepository.findById(username).orElseThrow(()->new UnauthorizedUserAccessException("Invalid username please check token"));
            log.info(user.toString());
            House house = houseRepository.findById(addDeviceRequestDTO.getHouseId()).orElseThrow(null);
            if (!(houseOwnerRepository.findByHouse_IdAndUser_Username(house.getId(), username).getRole().equals(RoleUser.ADMIN))) {
                throw new UnauthorizedUserAccessException("User is not the admin of the house");
            }
            Device device = deviceRepository.findById(addDeviceRequestDTO.getKickstonId()).orElseThrow(()->new EntityNotFoundException("Device with given id does not exist"));

            Room room = roomRepository.findById(addDeviceRequestDTO.getRoomId()).orElseThrow(()->new EntityNotFoundException("Room with given id does not exist"));
            device.setHouse(house);
            device.setRoom(room);
            return mapperService.toDTOGlobal(deviceRepository.save(device), "Device added to the given room of the house");
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("Device cannot be added. " + e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Device cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Device cannot be added to house please check again");
        }
    }
}
