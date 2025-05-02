package com.crm.user.appuser.controller;

import com.crm.api.Response;
import com.crm.user.appuser.dto.request.CreateUserRequest;
import com.crm.user.appuser.dto.response.UserResponse;
import com.crm.user.appuser.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Tag(name = "Users")
@PreAuthorize("hasRole('SUPER_ADMIN')")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Response<UserResponse>> addUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse userResponse = userService.addUser(request);
        return ResponseEntity.ok(Response.success("User added successfully",userResponse, HttpStatus.CREATED));
    }

    @PostMapping("/deactivate/{id}")
    public ResponseEntity<Response<Void>> deactivateUser(@PathVariable UUID id) {
        userService.deactivateUser(id);
        return ResponseEntity.ok(Response.success("User deactivated successfully",null, HttpStatus.OK));
    }
}
