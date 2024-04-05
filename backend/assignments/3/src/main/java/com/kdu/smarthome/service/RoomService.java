package com.kdu.smarthome.service;

import com.kdu.smarthome.constant.RoleUser;
import com.kdu.smarthome.dto.request.AddRoomRequestDTO;
import com.kdu.smarthome.dto.response.RoomResponseDTO;
import com.kdu.smarthome.entity.House;
import com.kdu.smarthome.entity.Room;
import com.kdu.smarthome.exception.EntityNotFoundException;
import com.kdu.smarthome.exception.UnauthorizedUserAccessException;
import com.kdu.smarthome.exception.UnprocessableEntityException;
import com.kdu.smarthome.repository.HouseOwnerRepository;
import com.kdu.smarthome.repository.HouseRepository;
import com.kdu.smarthome.repository.RoomRepository;
import org.springframework.stereotype.Service;

/**
 * Service class for handling room-related operations.
 */
@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final TokenService tokenService;
    private final HouseOwnerRepository houseOwnerRepository;
    private final MapperService mapperService;

    /**
     * Constructor for RoomService.
     *
     * @param roomRepository         The repository for room data.
     * @param houseRepository        The repository for house data.
     * @param tokenService           The service for token-related operations.
     * @param houseOwnerRepository   The repository for house ownership data.
     * @param mapperService          The service for mapping DTOs to entities.
     */
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository, TokenService tokenService,
                       HouseOwnerRepository houseOwnerRepository, MapperService mapperService) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.tokenService = tokenService;
        this.houseOwnerRepository = houseOwnerRepository;
        this.mapperService = mapperService;
    }

    /**
     * Adds a room to a house.
     *
     * @param houseId              The ID of the house to which the room will be added.
     * @param addRoomRequestDTO    The DTO containing information about the room to be added.
     * @param token                The token for authentication.
     * @return                     The response DTO indicating the result of the addition process.
     * @throws UnauthorizedUserAccessException If the user doesn't have admin access to the house.
     * @throws EntityNotFoundException        If the requested house is not found.
     * @throws UnprocessableEntityException   If an error occurs during the addition process.
     */
    public RoomResponseDTO addRoomToHouse(String houseId, AddRoomRequestDTO addRoomRequestDTO, String token) {
        try {
            String username = tokenService.extractUsername(token.substring(7));
            if (!(houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, username)
                    .getRole().equals(RoleUser.ADMIN))) {
                throw new UnauthorizedUserAccessException("User should be admin access for this functionality");
            }
            House house = houseRepository.findById(houseId).orElseThrow(() -> new EntityNotFoundException("House with given id doesn't found"));
            Room room = mapperService.toEntity(house, addRoomRequestDTO);
            return mapperService.toDTO(roomRepository.save(room), "Room added to house");
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("Room can't be added. " + e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Room can't be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Room can't be added please check again");
        }
    }
}
