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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<Response<UserResponse>> register(@Valid @RequestBody CreateUserRequest request) {
        UserResponse userResponse = userService.addUser(request);
        return ResponseEntity.ok(Response.success("User added successfully",userResponse, HttpStatus.CREATED));
    }
}
