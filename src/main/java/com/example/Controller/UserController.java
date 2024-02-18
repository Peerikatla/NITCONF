// package com.example.Controller;

// import com.example.Service.UserService;
// import com.example.model.User;

// import java.sql.Date;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/api/profiles")
// public class UserController {

//     @Autowired
//     private UserService userService;

//     @GetMapping("/{userId}")
//     public ResponseEntity<User> getUserProfile(@PathVariable Long userId) {
//         User user = userService.getUserById(userId);
//         if (user != null) {
//             return new ResponseEntity<>(user, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @PutMapping("/{userId}")
//     public ResponseEntity<User> updateUserProfile(@PathVariable Long userId,
//             @RequestBody User updatedUser) {
//         User existingUser = userService.getUserById(userId);
//         if (existingUser != null) {
//             updatedUser.setUserid(userId);
//             User savedUser = userService.updateUserProfile(updatedUser);
//             return new ResponseEntity<>(savedUser, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }

//     @PatchMapping("/{userId}")
//     public ResponseEntity<User> updateProfileFields(@PathVariable Long userId,
//             @RequestParam String firstName,
//             @RequestParam String lastName,
//             @RequestParam String username,
//             @RequestParam String number,
//             @RequestParam String specialization,
//             @RequestParam Date dateOfBirth) {
//         userService.updateUserProfileFields(userId, firstName, lastName, username, number, specialization, dateOfBirth);
//         User updatedUser = userService.getUserById(userId);
//         if (updatedUser != null) {
//             return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//         } else {
//             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//         }
//     }
// }
