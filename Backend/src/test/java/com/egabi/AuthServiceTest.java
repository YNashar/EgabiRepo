package com.egabi;

import com.egabi.Service.AuthService;
import com.egabi.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class AuthServiceTest {

  private AuthService authService;

  @BeforeEach
  void setUp() {
    authService = new AuthService();
  }

  @Test
  @DisplayName("Should authenticate admin with correct credentials")
  void shouldAuthenticateAdminWithCorrectCredentials() {
    String username = "admin";
    String password = "12345";

    User result = authService.authenticate(username, password);

    assertNotNull(result);
    assertEquals("admin", result.getUsername());
    assertEquals("12345", result.getPassword());
    assertEquals("ADMIN", result.getRole());
  }

  @Test
  @DisplayName("Should authenticate student with correct credentials")
  void shouldAuthenticateStudentWithCorrectCredentials() {
    String username = "student";
    String password = "1234";

    User result = authService.authenticate(username, password);

    assertNotNull(result);
    assertEquals("student", result.getUsername());
    assertEquals("1234", result.getPassword());
    assertEquals("STUDENT", result.getRole());
  }

  @Test
  @DisplayName("Should return null for admin with wrong password")
  void shouldReturnNullForAdminWithWrongPassword() {
    String username = "admin";
    String password = "wrongpassword";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should return null for student with wrong password")
  void shouldReturnNullForStudentWithWrongPassword() {
    String username = "student";
    String password = "wrongpassword";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should return null for non-existent username")
  void shouldReturnNullForNonExistentUsername() {
    String username = "nonexistent";
    String password = "anypassword";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should return null for empty username")
  void shouldReturnNullForEmptyUsername() {
    String username = "";
    String password = "anypassword";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should return null for null username")
  void shouldReturnNullForNullUsername() {
    String username = null;
    String password = "anypassword";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should return null for empty password")
  void shouldReturnNullForEmptyPassword() {
    String username = "admin";
    String password = "";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should return null for null password")
  void shouldReturnNullForNullPassword() {
    String username = "admin";
    String password = null;

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should handle case sensitivity for admin username")
  void shouldHandleCaseSensitivityForAdminUsername() {
    String username = "ADMIN";
    String password = "12345";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }

  @Test
  @DisplayName("Should handle case sensitivity for student username")
  void shouldHandleCaseSensitivityForStudentUsername() {
    String username = "STUDENT";
    String password = "1234";

    User result = authService.authenticate(username, password);

    assertNull(result);
  }
}
