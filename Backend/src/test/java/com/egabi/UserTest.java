package com.egabi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

  @Test
  @DisplayName("Should create user with constructor and get correct values")
  void shouldCreateUserWithConstructorAndGetCorrectValues() {
    String username = "testuser";
    String password = "testpass";
    String role = "STUDENT";

    User user = new User(username, password, role);

    assertEquals(username, user.getUsername());
    assertEquals(password, user.getPassword());
    assertEquals(role, user.getRole());
  }

  @Test
  @DisplayName("Should create admin user with correct values")
  void shouldCreateAdminUserWithCorrectValues() {
    String username = "admin";
    String password = "12345";
    String role = "ADMIN";

    User user = new User(username, password, role);

    assertEquals("admin", user.getUsername());
    assertEquals("12345", user.getPassword());
    assertEquals("ADMIN", user.getRole());
  }

  @Test
  @DisplayName("Should create student user with correct values")
  void shouldCreateStudentUserWithCorrectValues() {
    String username = "student";
    String password = "1234";
    String role = "STUDENT";

    User user = new User(username, password, role);

    assertEquals("student", user.getUsername());
    assertEquals("1234", user.getPassword());
    assertEquals("STUDENT", user.getRole());
  }

  @Test
  @DisplayName("Should set and get username correctly")
  void shouldSetAndGetUsernameCorrectly() {
    User user = new User("olduser", "password", "STUDENT");
    String newUsername = "newuser";

    user.setUsername(newUsername);

    assertEquals(newUsername, user.getUsername());
  }

  @Test
  @DisplayName("Should set and get password correctly")
  void shouldSetAndGetPasswordCorrectly() {
    User user = new User("username", "oldpass", "STUDENT");
    String newPassword = "newpass";

    user.setPassword(newPassword);

    assertEquals(newPassword, user.getPassword());
  }

  @Test
  @DisplayName("Should set and get role correctly")
  void shouldSetAndGetRoleCorrectly() {
    User user = new User("username", "password", "STUDENT");
    String newRole = "ADMIN";

    user.setRole(newRole);

    assertEquals(newRole, user.getRole());
  }

  @Test
  @DisplayName("Should handle empty strings in constructor")
  void shouldHandleEmptyStringsInConstructor() {
    String username = "";
    String password = "";
    String role = "";

    User user = new User(username, password, role);

    assertEquals("", user.getUsername());
    assertEquals("", user.getPassword());
    assertEquals("", user.getRole());
  }

  @Test
  @DisplayName("Should handle null values in setters")
  void shouldHandleNullValuesInSetters() {
    User user = new User("username", "password", "STUDENT");

    user.setUsername(null);
    user.setPassword(null);
    user.setRole(null);

    assertNull(user.getUsername());
    assertNull(user.getPassword());
    assertNull(user.getRole());
  }

  @Test
  @DisplayName("Should handle special characters in username")
  void shouldHandleSpecialCharactersInUsername() {
    String username = "user@123";
    String password = "password";
    String role = "STUDENT";

    User user = new User(username, password, role);

    assertEquals("user@123", user.getUsername());
  }

  @Test
  @DisplayName("Should handle long strings")
  void shouldHandleLongStrings() {
    String longUsername = "a".repeat(100);
    String longPassword = "b".repeat(100);
    String longRole = "c".repeat(100);

    User user = new User(longUsername, longPassword, longRole);

    assertEquals(longUsername, user.getUsername());
    assertEquals(longPassword, user.getPassword());
    assertEquals(longRole, user.getRole());
  }

  @Test
  @DisplayName("Should handle whitespace in strings")
  void shouldHandleWhitespaceInStrings() {
    String username = "  user  ";
    String password = "  pass  ";
    String role = "  STUDENT  ";

    User user = new User(username, password, role);

    assertEquals("  user  ", user.getUsername());
    assertEquals("  pass  ", user.getPassword());
    assertEquals("  STUDENT  ", user.getRole());
  }

  @Test
  @DisplayName("Should maintain state after multiple setter calls")
  void shouldMaintainStateAfterMultipleSetterCalls() {
    User user = new User("initial", "initial", "STUDENT");

    user.setUsername("first");
    user.setUsername("second");
    user.setPassword("first");
    user.setPassword("second");
    user.setRole("ADMIN");
    user.setRole("STUDENT");

    assertEquals("second", user.getUsername());
    assertEquals("second", user.getPassword());
    assertEquals("STUDENT", user.getRole());
  }
}
