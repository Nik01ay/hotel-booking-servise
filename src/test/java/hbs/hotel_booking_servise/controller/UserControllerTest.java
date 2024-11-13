package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.AbstractTest;
import hbs.hotel_booking_servise.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

class UserControllerTest extends AbstractTest {
    @BeforeEach
    public void setup (){
        System.out.println("Start Before test");
        UserDto.Request request =UserDto.Request.builder()
                .name("User1")
                .email("email1@sda.ru")
                .password("12345")
                .build();
        userService.create(request);
        System.out.println("User1 CREATED");
        System.out.println(request);

        userService.create(UserDto.Request.builder()
                .name("User2")
                .email("email2@sda.ru")
                .password("12345")
                .build());
        System.out.println("User2 CREATED");

    }
    @AfterEach
    public void afterEach(){
        userService.deleteAll();
    }

    @Test

    public void createUniqueUser() throws Exception{
    UserDto.Request   user = UserDto.Request.builder()
                .password("12345")
                .email("maile@mail.ru")
                .name("Ivan")
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
       String content = objectMapper.writeValueAsString(user);
       System.out.println("CONTENT USER: ");
       System.out.println(content);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is("Ivana")))
                .andExpect(jsonPath("$.password", is("12345")))
                .andExpect(jsonPath("$.email", is("maile@mail.ru")));


    }
    @Test
    public void createNoUniqueUser() throws Exception{
        UserDto.Request   user = UserDto.Request.builder()
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
    @WithMockUser(username = "user", authorities =  {"ADMIN"})
    public void getAllTest() throws Exception{
        System.out.println("Start Get All test");
        mockMvc.perform(get("/api/v1/user")).andExpect(
                status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2))) // Проверяем, что размер массива равен 2
                .andExpect(jsonPath("$[0].id", is(1))) // Проверяем параметры первого объекта
                .andExpect(jsonPath("$[0].name", is("User1")))

                .andExpect(jsonPath("$[0].email", is("email1@sda.ru")))
                .andExpect(jsonPath("$[1].id", is(2))) // Проверяем параметры второго объекта
                .andExpect(jsonPath("$[1].name", is("User2")))

                .andExpect(jsonPath("$[1].email", is("email2@sda.ru")))
                .andExpect(jsonPath("$[0].role", is("USER"))) // Проверяем значение role
                .andExpect(jsonPath("$[1].role", is("USER")));
    }

}