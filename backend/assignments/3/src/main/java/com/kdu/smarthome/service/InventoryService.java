package com.kdu.smarthome.service;

import com.kdu.smarthome.dto.request.AddInventoryRequestDTO;
import com.kdu.smarthome.dto.response.GlobalResponseDTO;
import com.kdu.smarthome.dto.response.InventoryResponseDTO;
import com.kdu.smarthome.entity.Inventory;
import com.kdu.smarthome.exception.DataRetrievalFailedException;
import com.kdu.smarthome.exception.UnprocessableEntityException;
import com.kdu.smarthome.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MapperService mapperService;

    public InventoryService(InventoryRepository inventoryRepository, MapperService mapperService) {
        this.inventoryRepository = inventoryRepository;
        this.mapperService = mapperService;
    }

    public InventoryResponseDTO getInventoryItems() {
        try {
            List<Inventory> inventoryList = inventoryRepository.findAll();
            return mapperService.toDTO(inventoryList);
        } catch (Exception e) {
            throw new DataRetrievalFailedException("Unable to retrieve requested data");
        }
    }

    public GlobalResponseDTO addInventoryItem(AddInventoryRequestDTO inventoryRequestDTO) {
        try {
            return mapperService.toDTOGlobal(
                    inventoryRepository.save(mapperService.toEntity(inventoryRequestDTO)),
                    "Item added inside the Inventory"
            );
        } catch (Exception e) {
            throw new UnprocessableEntityException("UnprocessableEntity : Item can't be added");
        }
    }
}
