package com.example.Controller;

import com.example.Service.UserService;
import com.example.model.User;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @ExtendWith({SpringExtension.class, MockitoExtension.class})
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @SuppressWarnings("null")
    @Test
    public void testGetUserProfile() throws Exception {
        int userId = 1;
        User user = new User();
        user.setUserid(userId);
        when(userService.getUserById(userId)).thenReturn(user);

        mockMvc.perform(get("/api/profiles/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userid").value(userId));
    }

    @SuppressWarnings("null")
    @Test
    public void testUpdateUserProfile() throws Exception {
        int userId = 1;
        User updatedUser = new User();
        updatedUser.setUserid(userId);
        when(userService.updateUserProfile(updatedUser)).thenReturn(updatedUser);

        mockMvc.perform(put("/api/profiles/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userid\":1}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userid").value(userId));
    }

    @SuppressWarnings("null")
    @Test
    public void testUpdateProfileFields() throws Exception {
        int userId = 1;
        String firstName = "Naveen";
        String lastName = "Kavuru";
        String username = "TheNaveen";
        String number = "9182514197";
        String specialization = "DSA";
        @SuppressWarnings("deprecation")
        Date dateOfBirth = new Date(2004, 9, 20);

        mockMvc.perform(patch("/api/profiles/{userId}", userId)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("username", username)
                .param("number", number)
                .param("specialization", specialization)
                .param("dateOfBirth", dateOfBirth.toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
