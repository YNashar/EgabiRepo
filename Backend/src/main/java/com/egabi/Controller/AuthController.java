package com.egabi.Controller;

import com.egabi.Service.AuthService;
import com.egabi.Main.User;
import com.egabi.security.JwtUtil;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        User user = authService.authenticate(request.getUsername(), request.getPassword());
        if (user == null) {
            throw new RuntimeException("Invalid credentials");
        }
        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        return new AuthResponse(user.getUsername(), user.getRole(), token);
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
        public AuthResponse(String username, String role, String token) {
            this.username = username;
            this.role = role;
            this.token = token;
        }
        public String getUsername() { return username; }
        public String getRole() { return role; }
        public String getToken() { return token; }
    }
}