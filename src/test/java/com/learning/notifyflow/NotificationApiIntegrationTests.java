
package com.learning.notifyflow;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class NotificationApiIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/api/v1/notifications";

    @Test
    void createNotification_returns201() throws Exception {
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "recipient": "user@example.com",
                      "message": "Hello"
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", not(emptyString())))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }

    @Test
    void invalidEmail_returnsStructured400() throws Exception {
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "recipient": "invalid-email",
                      "message": ""
                    }
                    """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Validation failed"))
                .andExpect(jsonPath("$.errors.recipient").isArray())
                .andExpect(jsonPath("$.errors.recipient", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$.errors.message").isArray())
                .andExpect(jsonPath("$.errors.message", hasSize(greaterThan(0))));
    }

    @Test
    void notificationNotFound_returns404() throws Exception {
        mockMvc.perform(get(URL + "/nonexistent-id"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createThenGet_returnsSameNotification() throws Exception {
        String response = mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "recipient": "user@example.com",
                      "message": "Hello"
                    }
                    """))
                .andExpect(status().isCreated())
                .andReturn()
                .getResponse()
                .getContentAsString();

        // Extract the server-generated ID from the POST response.
        String id = response.split("\"id\":\"")[1].split("\"")[0];

        mockMvc.perform(get(URL + "/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.recipient").value("user@example.com"))
                .andExpect(jsonPath("$.message").value("Hello"))
                .andExpect(jsonPath("$.status").value("PENDING"));
    }
}
