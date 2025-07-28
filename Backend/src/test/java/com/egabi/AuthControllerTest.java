package com.egabi;

import com.egabi.Controller.AuthController;
import com.egabi.Service.AuthService;
import com.egabi.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

  @Mock
  private AuthService authService;

  @InjectMocks
  private AuthController authController;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  @DisplayName("Should return OK response for valid admin credentials")
  void shouldReturnOkResponseForValidAdminCredentials() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("username", "admin");
    loginRequest.put("password", "12345");

    User expectedUser = new User("admin", "12345", "ADMIN");
    when(authService.authenticate("admin", "12345")).thenReturn(expectedUser);

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("admin", response.getBody().getUsername());
    assertEquals("ADMIN", response.getBody().getRole());
    verify(authService, times(1)).authenticate("admin", "12345");
  }

  @Test
  @DisplayName("Should return OK response for valid student credentials")
  void shouldReturnOkResponseForValidStudentCredentials() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("username", "student");
    loginRequest.put("password", "1234");

    User expectedUser = new User("student", "1234", "STUDENT");
    when(authService.authenticate("student", "1234")).thenReturn(expectedUser);

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertEquals("student", response.getBody().getUsername());
    assertEquals("STUDENT", response.getBody().getRole());
    verify(authService, times(1)).authenticate("student", "1234");
  }

  @Test
  @DisplayName("Should return bad request for invalid credentials")
  void shouldReturnBadRequestForInvalidCredentials() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("username", "admin");
    loginRequest.put("password", "wrongpassword");

    when(authService.authenticate("admin", "wrongpassword")).thenReturn(null);

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(authService, times(1)).authenticate("admin", "wrongpassword");
  }

  @Test
  @DisplayName("Should return bad request for non-existent user")
  void shouldReturnBadRequestForNonExistentUser() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("username", "nonexistent");
    loginRequest.put("password", "anypassword");

    when(authService.authenticate("nonexistent", "anypassword")).thenReturn(null);

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(authService, times(1)).authenticate("nonexistent", "anypassword");
  }

  @Test
  @DisplayName("Should handle null username in request")
  void shouldHandleNullUsernameInRequest() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("username", null);
    loginRequest.put("password", "anypassword");

    when(authService.authenticate(null, "anypassword")).thenReturn(null);

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(authService, times(1)).authenticate(null, "anypassword");
  }

  @Test
  @DisplayName("Should handle null password in request")
  void shouldHandleNullPasswordInRequest() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("username", "admin");
    loginRequest.put("password", null);

    when(authService.authenticate("admin", null)).thenReturn(null);

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(authService, times(1)).authenticate("admin", null);
  }

  @Test
  @DisplayName("Should handle empty request map")
  void shouldHandleEmptyRequestMap() {
    Map<String, String> loginRequest = new HashMap<>();

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(authService, times(1)).authenticate(null, null);
  }

  @Test
  @DisplayName("Should handle missing username in request")
  void shouldHandleMissingUsernameInRequest() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("password", "anypassword");

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(authService, times(1)).authenticate(null, "anypassword");
  }

  @Test
  @DisplayName("Should handle missing password in request")
  void shouldHandleMissingPasswordInRequest() {
    Map<String, String> loginRequest = new HashMap<>();
    loginRequest.put("username", "admin");

    ResponseEntity<User> response = authController.login(loginRequest);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertNull(response.getBody());
    verify(authService, times(1)).authenticate("admin", null);
  }
}
