package com.example.Controller;

import com.example.Service.UserService;
import com.example.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
// import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
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

    @InjectMocks
    private UserController userController;

    @SuppressWarnings("null")
    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" }) // Mock authentication for admin user
    public void testGetUserProfile() throws Exception {

        User expectedUser = new User(1, "Kavuru Naveen Kumar", "TheNaveen",
                "9182514197", "2917", 10L,
                Date.valueOf(LocalDate.parse("2004-09-20")), "naveenkumar_b210560cs@nitc.ac.in", "Data Science");

        // Mock UserService response
        Mockito.when(userService.getUserById(1)).thenReturn(expectedUser);

        // Perform GET request using mockMvc
        mockMvc.perform(get("/api/profiles")
                .param("userId", "1")
                .param("admin", "true")) // Add admin parameter
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.userid").value(expectedUser.getUserid()))
                .andExpect(jsonPath("$.fullName").value(expectedUser.getFullName()))
                .andExpect(jsonPath("$.username").value(expectedUser.getUsername()))
                .andExpect(jsonPath("$.number").value(expectedUser.getNumber()))
                .andExpect(jsonPath("$.specialization").value(expectedUser.getSpecialization()))
                .andExpect(jsonPath("$.dateOfBirth").value(expectedUser.getDateOfBirth().toString()))
                .andExpect(jsonPath("$.email").value(expectedUser.getEmail()))
                .andExpect(jsonPath("$.paperlimit").value(expectedUser.getPaperlimit()))
                .andExpect(jsonPath("$.password").value(expectedUser.getPassword()));

        Mockito.verify(userService, Mockito.times(1)).getUserById(1);
    }

    @Test
    public void testUpdateProfileFields() throws Exception {
        // Mock user data
        User expectedUser = new User(1, "Kavuru Naveen Kumar", "TheNaveen", "9182514197", "2917", 10L,
                Date.valueOf(LocalDate.parse("2004-09-20")), "naveenkumar_b210560cs@nitc.ac.in", "Data Science");

        User updatedUser = new User(1, "Kavuru Naveen Kumar", "TheNaveen", "9492522866", "2917", 10L,
                Date.valueOf(LocalDate.parse("2004-09-20")), "naveenkumar_b210560cs@nitc.ac.in", "Data Science");

        // Mock UserService behavior
        when(userService.getUserById(1)).thenReturn(expectedUser);
        // You can mock other UserService behaviors if needed

        // Perform the test
        HttpStatus result = userController.updateProfileFields(1, updatedUser);

        // Verify that the service method is called with the correct parameters
        verify(userService).updateUserProfileFields(expectedUser, updatedUser);

        // Check if HttpStatus.OK is returned
        assertEquals(HttpStatus.OK, result);
    }
}
