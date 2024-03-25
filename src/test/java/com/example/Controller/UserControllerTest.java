/*
 * package com.example.Controller;
 * 
 * import com.example.Service.UserService; import com.example.model.User; import
 * org.junit.jupiter.api.Test; import
 * org.junit.jupiter.api.extension.ExtendWith; import org.mockito.InjectMocks;
 * import org.mockito.Mock; import org.mockito.junit.jupiter.MockitoExtension;
 * import org.springframework.http.HttpStatus; import
 * org.springframework.http.ResponseEntity;
 * 
 * import static org.junit.jupiter.api.Assertions.assertEquals; import static
 * org.mockito.Mockito.*;
 * 
 * import java.util.Map;
 * 
 * @ExtendWith(MockitoExtension.class) class UserControllerTest {
 * 
 * @Mock private UserService userService;
 * 
 * @InjectMocks private UserController userController;
 * 
 * @Test void getUserProfile_UserExists_ReturnsUserProfile() { // Arrange int
 * userId = 1; User mockUser = new User(); mockUser.setUserid(userId);
 * when(userService.getUserById(userId)).thenReturn(mockUser);
 * 
 * // Act ResponseEntity<Map<String, Object>> responseEntity =
 * userController.getUserProfile(userId);
 * 
 * // Assert assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
 * assertEquals(mockUser, responseEntity.getBody()); }
 * 
 * @Test void getUserProfile_UserDoesNotExist_ReturnsNotFound() { // Arrange int
 * userId = 1; when(userService.getUserById(userId)).thenReturn(null);
 * 
 * // Act ResponseEntity<Map<String, Object>> responseEntity =
 * userController.getUserProfile(userId);
 * 
 * // Assert assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
 * assertEquals(null, responseEntity.getBody()); }
 * 
 * @Test void updateProfileFields_UserExists_ReturnsOk() { // Arrange int userId
 * = 1; User existingUser = new User(); existingUser.setUserid(userId); User
 * updatedUser = new User(); updatedUser.setUserid(userId);
 * when(userService.getUserById(userId)).thenReturn(existingUser);
 * 
 * // Act //HttpStatus status = userController.updateProfileFields(userId,
 * updatedUser);
 * 
 * // Assert //assertEquals(HttpStatus.OK, status); }
 * 
 * @Test void updateProfileFields_UserDoesNotExist_ReturnsNotFound() { //
 * Arrange int userId = 1;
 * when(userService.getUserById(userId)).thenReturn(null);
 * 
 * // Act //HttpStatus status = userController.updateProfileFields(userId, new
 * User());
 * 
 * // Assert //assertEquals(HttpStatus.NOT_FOUND, status); } }
 */