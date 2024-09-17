package com.kdu.jpa.repository;

import com.kdu.jpa.entity.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftTypeRepository extends JpaRepository<ShiftType, UUID> {
    List<ShiftType> findAllByTenantId(UUID tenantId);
}
