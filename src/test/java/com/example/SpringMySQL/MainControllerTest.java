package com.example.SpringMySQL;

import ch.qos.logback.core.net.ObjectWriter;
import com.example.SpringMySQL.controller.MainController;
import com.example.SpringMySQL.interceptor.RequestInterceptor;
import com.example.SpringMySQL.model.Login;
import com.example.SpringMySQL.model.User;
import com.example.SpringMySQL.repository.UserRepository;
import com.example.SpringMySQL.service.LoginServiceImpl;
import com.example.SpringMySQL.service.MainServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.hibernate.mapping.Any;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private MainServiceImpl mainService;

    @MockBean
    private LoginServiceImpl loginService;

    @MockBean
    private RequestInterceptor requestInterceptor;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void getAllUsers() throws Exception {
        User john = new User(1, "John", "john@valve.com", 22, "male", "a");
        List<User> allUsers = Arrays.asList(john);

        given(mainService.findAll()).willReturn(allUsers);
        given(requestInterceptor.preHandle(any(), any(), any())).willReturn(true); //to pass the interceptor

        List<User> users = mainService.findAll();

        this.mockMvc.perform(get("/demo/user"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createUser() throws Exception {
        User john = new User("John", "johnvalve.com", 22, "male", "aaaaaaaaaaaa");
        User validated = new User(1,"John", "john@valve.com", 22, "male", "aaaaaaaaaaaa");

        given(requestInterceptor.preHandle(any(), any(), any())).willReturn(true);
        given(mainService.createUser(any())).willReturn(validated);

        this.mockMvc.perform(post("/demo/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(john)))
                .andDo(print())
                .andExpect(status().isOk());
    }
}