package com.kdu.jdbc.service;

import com.kdu.jdbc.constant.SHIFT_TYPE;
import com.kdu.jdbc.dto.*;
import com.kdu.jdbc.model.*;
import org.springframework.stereotype.Service;

/**
 * Service class for mapping DTOs to entities.
 */
@Service
public class MapperService {

    /**
     * Maps UserDTO to User entity.
     *
     * @param userDTO The UserDTO to be mapped.
     * @return The mapped User entity.
     */
    public User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setTenantId(userDTO.getTenantId());
        user.setTimezone(user.getTimezone());
        return user;
    }

    /**
     * Maps ShiftDTO to Shift entity.
     *
     * @param shiftDTO The ShiftDTO to be mapped.
     * @return The mapped Shift entity.
     */
    public Shift toEntity(ShiftDTO shiftDTO) {
        return Shift.builder().shiftTypeId(shiftDTO.getShiftTypeId())
                .name(shiftDTO.getName())
                .dateStart(shiftDTO.getDateStart())
                .dateEnd(shiftDTO.getDateEnd())
                .timeStart(shiftDTO.getTimeStart())
                .timeEnd(shiftDTO.getTimeEnd())
                .timezone(shiftDTO.getTimezone())
                .tenantId(shiftDTO.getTenantId())
                .build();
    }

    /**
     * Maps ShiftTypeDTO to ShiftType entity.
     *
     * @param shiftTypeDTO The ShiftTypeDTO to be mapped.
     * @return The mapped ShiftType entity.
     */
    public ShiftType toEntity(ShiftTypeDTO shiftTypeDTO) {
        return ShiftType.builder()
                .name(getShiftType(shiftTypeDTO.getName()))
                .description(shiftTypeDTO.getDescription())
                .active(shiftTypeDTO.isActive())
                .timezone(shiftTypeDTO.getTimezone())
                .tenantId(shiftTypeDTO.getTenantId())
                .build();
    }

    /**
     * Maps TenantDTO to Tenant entity.
     *
     * @param tenantDTO The TenantDTO to be mapped.
     * @return The mapped Tenant entity.
     */
    public Tenant toEntity(TenantDTO tenantDTO) {
        return Tenant.builder()
                .name(tenantDTO.getName())
                .createdBy(tenantDTO.getCreatedBy()).build();
    }

    /**
     * Maps ShiftUserDTO to ShiftUser entity.
     *
     * @param shiftUserDTO The ShiftUserDTO to be mapped.
     * @return The mapped ShiftUser entity.
     */
    public ShiftUser toEntity(ShiftUserDTO shiftUserDTO) {
        return ShiftUser.builder()
                .shiftId(shiftUserDTO.getShiftId())
                .userId(shiftUserDTO.getUserId())
                .tenantId(shiftUserDTO.getTenantId()).build();
    }

    /**
     * Gets ShiftType enum based on the given type string.
     *
     * @param type The type string.
     * @return The corresponding SHIFT_TYPE enum value.
     */
    private SHIFT_TYPE getShiftType(String type) {
        if (type.equals("Morning")) {
            return SHIFT_TYPE.MORNING;
        } else return (type.equals("Afternoon") ? SHIFT_TYPE.AFTERNOON : SHIFT_TYPE.EVENING);
    }
}
