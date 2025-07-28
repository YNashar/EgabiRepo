package com.egabi.Controller;

import com.egabi.Service.AuthService;
import com.egabi.security.JwtUtil;
import com.egabi.DTO.AuthDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        System.out.println("Login endpoint called with: " + request.getUsername());
        AuthDTO authDTO = authService.authenticate(request.getUsername(), request.getPassword());
        if (authDTO == null) {
            System.out.println("Invalid credentials for: " + request.getUsername());
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(authDTO.getUsername(), authDTO.getRole());
        return new AuthResponse(authDTO, token);
    }
    public static class AuthRequest {
        private String username;
        private String password;
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class AuthResponse {
        private String username;
        private String role;
        private String token;
        public AuthResponse(AuthDTO authDTO, String token) {
            this.username = authDTO.getUsername();
            this.role = authDTO.getRole();
            this.token = token;
        }
        public String getUsername() { return username; }
        public String getRole() { return role; }
        public String getToken() { return token; }
    }
}