package vn.unigap.api.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;
import vn.unigap.config.auth.SecurityConfig;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Import(SecurityConfig.class)
@WebMvcTest(EmployerController.class)
class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void whenGetEmployerId_thenThrow401Unauthorized() throws Exception {
    }

    @Test
    void createEmployer() {
    }

    @Test
    void updateEmployer() {
    }

    @Test
    void getAllEmployers() {
    }

    @Test
    void deleteEmployer() {
    }
}