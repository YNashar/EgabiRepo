package com.egabi.Controller;

import com.egabi.Service.AuthService;
import com.egabi.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

  @Autowired
  private AuthService authService;

  @PostMapping("/login")
  public ResponseEntity<User> login(@RequestBody Map<String, String> loginRequest) {
    String username = loginRequest.get("username");
    String password = loginRequest.get("password");

    User user = authService.authenticate(username, password);

    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      return ResponseEntity.badRequest().build();
    }
  }
}
