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

@Service
public class RoomService {
    private final RoomRepository roomRepository;
    private final HouseRepository houseRepository;
    private final TokenService tokenService;
    private final HouseOwnerRepository houseOwnerRepository;
    private final MapperService mapperService;
    public RoomService(RoomRepository roomRepository, HouseRepository houseRepository, TokenService tokenService,
                       HouseOwnerRepository houseOwnerRepository, MapperService mapperService) {
        this.roomRepository = roomRepository;
        this.houseRepository = houseRepository;
        this.tokenService = tokenService;
        this.houseOwnerRepository = houseOwnerRepository;
        this.mapperService = mapperService;
    }

   
    public RoomResponseDTO addRoomToHouse(String houseId, AddRoomRequestDTO addRoomRequestDTO, String token) {
        try {
            String username = tokenService.extractUsername(token.substring(7));
            if (!(houseOwnerRepository.findByHouse_IdAndUser_Username(houseId, username)
                    .getRole().equals(RoleUser.ADMIN))) {
                throw new UnauthorizedUserAccessException("User does not have admin access");
            }
            House house = houseRepository.findById(houseId).orElseThrow(()-> new EntityNotFoundException("House with given id does not exist"));
            Room room = mapperService.toEntity(house, addRoomRequestDTO);
            return mapperService.toDTO(roomRepository.save(room), "Room added to given house");
        } catch (UnauthorizedUserAccessException e) {
            throw new UnauthorizedUserAccessException("Room cannot be added. " + e.getMessage());
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("Room cannot be added. " + e.getMessage());
        } catch (Exception e) {
            throw new UnprocessableEntityException("Room cannot be added please check again");
        }
    }
}
