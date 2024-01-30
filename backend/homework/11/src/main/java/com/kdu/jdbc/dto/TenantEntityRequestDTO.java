package com.kdu.jdbc.dto;

import com.kdu.jdbc.model.Shift;
import com.kdu.jdbc.model.ShiftType;
import com.kdu.jdbc.model.ShiftUser;
import com.kdu.jdbc.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TenantEntityRequestDTO {
    private List<User> users;
    private List<Shift> shifts;
    private List<ShiftType> shiftTypes;
    private List<ShiftUser> shiftUsers;
}
