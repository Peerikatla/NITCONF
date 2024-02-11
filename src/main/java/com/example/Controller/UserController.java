package com.example.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Service.UserService;
import com.example.model.User;

/**
* The UserController class handles requests related to user profile updates.
*/
public class UserController {

   /**
    * Handles the request to update the user profile.
    *
    * @param user The User object containing the updated user profile information.
    * @return Redirects to the "profile" page after updating the user profile.
    */
   @PostMapping("/updateProfile")
   public String updateProfile(@ModelAttribute User user) {
       // Call the UserService to save the updated user profile information
//       UserService.updateProfile(user);

       // Redirect to the "profile" page after updating the user profile
       return "redirect:/profile";
   }
}
