package com.egabi.Service;

import com.egabi.Main.User;
import com.egabi.DTO.AuthDTO;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final User admin = new User("admin", "12345", "ADMIN");
    private final User student = new User("student", "1234", "STUDENT");

    public AuthDTO authenticate(String username, String password) {
        // Debug print to see what credentials are being checked
        System.out.println("AuthService.authenticate called with username: " + username + ", password: " + password);

        User user = null;
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            user = admin;
        } else if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
            user = student;
        }
        System.out.println("AuthService.authenticate resolved user: " + user); // This will print null if not found

        if (user == null) return null;
        return new AuthDTO(user.getUsername(), user.getRole());
    }
}