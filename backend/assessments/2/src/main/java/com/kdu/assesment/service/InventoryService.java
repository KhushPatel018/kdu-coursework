package com.kdu.assesment.service;

import com.kdu.assesment.entity.Inventory;
import com.kdu.assesment.repository.InventoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InventoryService {
    final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    public void addInventory(Inventory inventory){
        inventoryRepository.save(inventory);
    }

    public List<Inventory> getAll(){
        return inventoryRepository.findAll();
    }
}

