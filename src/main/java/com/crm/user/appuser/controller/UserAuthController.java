package com.crm.user.appuser.controller;

import com.crm.api.Response;
import com.crm.user.appuser.dto.request.AuthenticateUserRequest;
import com.crm.user.appuser.service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication")
@Validated
@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class UserAuthController {

    private final AuthService authService;
    private final HttpSession httpSession;

    @PostMapping("/login")
    public ResponseEntity<Response<Void>> authenticate(@Valid @RequestBody AuthenticateUserRequest request) {
        authService.authenticate(request);
        return ResponseEntity.ok(Response.success("Authenticated successfully",null, HttpStatus.OK));
    }

    @PostMapping("/logout")
    public ResponseEntity<Response<Void>> logout() {
        httpSession.invalidate();
        return ResponseEntity.ok(Response.success("Logged out successfully", null, HttpStatus.OK));
    }
}
