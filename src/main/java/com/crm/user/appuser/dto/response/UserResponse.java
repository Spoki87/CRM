package com.crm.user.appuser.dto.response;

import com.crm.user.appuser.model.Role;
import lombok.Value;

import java.util.UUID;

@Value
public class UserResponse {
    UUID id;
    String email;
    Role role;
}
