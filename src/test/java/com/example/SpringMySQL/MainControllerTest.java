package com.example.SpringMySQL;

import com.example.SpringMySQL.controller.MainController;
import com.example.SpringMySQL.interceptor.RequestInterceptor;
import com.example.SpringMySQL.model.User;
import com.example.SpringMySQL.service.LoginServiceImpl;
import com.example.SpringMySQL.service.MainServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private User user;

    @MockBean
    private MainServiceImpl mainService;

    @MockBean
    private LoginServiceImpl loginService;

    @MockBean
    private RequestInterceptor requestInterceptor;

    @Test
    public void getUserFromId() throws Exception {

        this.mockMvc.perform(get("/demo/user/{id}", 1)
                .param("token", "6519AAB4C14896CBBF7DEF18D6C2D18C")
        ).andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getAllUsers() throws Exception {

        this.mockMvc.perform(get("/demo/user")
                .param("token", "6519AAB4C14896CBBF7DEF18D6C2D18C")
        ).andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}