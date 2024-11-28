package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.AbstractTest;

import hbs.hotel_booking_servise.domain.service.UserService;
import hbs.hotel_booking_servise.dto.UserDtoRequest;
import hbs.hotel_booking_servise.statistics.KafkaProducer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

class UserControllerTest extends AbstractTest {


    @Test
    public void createUniqueUser() throws Exception {
        UserDtoRequest user = UserDtoRequest.builder()
                .password("12345")
                .email("maile@mail.ru")
                .name("Ivan")
                .password("12345")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);
        System.out.println("CONTENT USER: ");
        System.out.println(content);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Ivan")))

                .andExpect(jsonPath("$.email", is("maile@mail.ru")));


    }

    @Test
    public void createNoUniqueUser() throws Exception {
        UserDtoRequest user = UserDtoRequest.builder()
                .password("12345")
                .email("maile@mail.ru")
                .name("User2")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(user);
        System.out.println("CONTENT USER: ");
        System.out.println(content);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest());


    }

    @Test
    @WithMockUser(username = "user", authorities = {"ADMIN"})
    public void getAllTest() throws Exception {
        System.out.println("Start Get All test");
        mockMvc.perform(get("/api/v1/user")).andExpect(
                        status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(3))) // Проверяем, что размер массива равен 2
               // .andExpect(jsonPath("$[0].id", is(1))) // Проверяем параметры первого объекта
                .andExpect(jsonPath("$[0].name", is("User1")))

                .andExpect(jsonPath("$[0].email", is("user1@example.com")))
               // .andExpect(jsonPath("$[1].id", is(2))) // Проверяем параметры второго объекта
                .andExpect(jsonPath("$[1].name", is("User2")))

                .andExpect(jsonPath("$[1].email", is("user2@example.com")))
                .andExpect(jsonPath("$[0].role", is("USER"))) // Проверяем значение role
                .andExpect(jsonPath("$[1].role", is("USER")));
    }

}