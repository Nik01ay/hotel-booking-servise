package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.AbstractTest;
import hbs.hotel_booking_servise.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

class UserControllerTest extends AbstractTest {
    @BeforeEach
    public void setup (){
        System.out.println("Start Before test");
        UserDto.Request request =UserDto.Request.builder()
                .name("User1")
                .email("email1@sda.ru")
                .password("12345")
                .build();
        userHandler.create(request);
        System.out.println("User1 CREATED");
        System.out.println(request);

        userHandler.create(UserDto.Request.builder()
                .name("User2")
                .email("email2@sda.ru")
                .password("12345")
                .build());
        System.out.println("User2 CREATED");

    }
    @AfterEach
    public void afterEach(){
        userHandler.deleteAll();
    }

    @Test
   // @WithMockUser(username = "user", roles =  {"USER"})
    public void getAllTest() throws Exception{
        System.out.println("Start Get All test");
        mockMvc.perform(get("/api/v1/user")).andExpect(
                status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2))) // Проверяем, что размер массива равен 2
                .andExpect(jsonPath("$[0].id", is(1))) // Проверяем параметры первого объекта
                .andExpect(jsonPath("$[0].name", is("User1")))
                .andExpect(jsonPath("$[0].password", is("12345")))
                .andExpect(jsonPath("$[0].email", is("email1@sda.ru")))
                .andExpect(jsonPath("$[1].id", is(2))) // Проверяем параметры второго объекта
                .andExpect(jsonPath("$[1].name", is("User2")))
                .andExpect(jsonPath("$[1].password", is("12345")))
                .andExpect(jsonPath("$[1].email", is("email2@sda.ru")))
                .andExpect(jsonPath("$[0].role", is(nullValue()))) // Проверяем значение role
                .andExpect(jsonPath("$[1].role", is(nullValue())));
    }

}