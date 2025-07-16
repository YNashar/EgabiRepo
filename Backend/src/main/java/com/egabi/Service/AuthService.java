package com.egabi.Service;

import com.egabi.Main.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final User admin = new User("admin", "12345", "ADMIN");
    private final User student = new User("student", "1234", "STUDENT");

    public User authenticate(String username, String password) {
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            return admin;
        }
        if (student.getUsername().equals(username) && student.getPassword().equals(password)) {
            return student;
        }
        return null;
    }
}