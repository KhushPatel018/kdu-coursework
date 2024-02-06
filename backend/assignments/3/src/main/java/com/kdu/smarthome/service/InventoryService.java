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

/**
 * Service class for handling inventory-related operations.
 */
@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final MapperService mapperService;

    /**
     * Constructor for InventoryService.
     *
     * @param inventoryRepository The repository for inventory data.
     * @param mapperService       The service for mapping DTOs to entities.
     */
    public InventoryService(InventoryRepository inventoryRepository, MapperService mapperService) {
        this.inventoryRepository = inventoryRepository;
        this.mapperService = mapperService;
    }

    /**
     * Retrieves all inventory items.
     *
     * @return The response DTO containing the list of inventory items.
     * @throws DataRetrievalFailedException If the data retrieval process fails.
     */
    public InventoryResponseDTO getInventoryItems() {
        try {
            List<Inventory> inventoryList = inventoryRepository.findAll();
            return mapperService.toDTO(inventoryList);
        } catch (Exception e) {
            throw new DataRetrievalFailedException("Unable to retrieve data");
        }
    }

    /**
     * Adds an inventory item.
     *
     * @param inventoryRequestDTO The DTO containing information about the inventory item to be added.
     * @return                     The response DTO indicating the result of the addition process.
     * @throws UnprocessableEntityException If an error occurs during the addition process.
     */
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
