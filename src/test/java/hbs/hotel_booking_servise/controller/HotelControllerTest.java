package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.AbstractTest;
import hbs.hotel_booking_servise.domain.entity.Hotel;
import hbs.hotel_booking_servise.domain.repository.HotelRepo;
import hbs.hotel_booking_servise.domain.service.BookingService;
import hbs.hotel_booking_servise.domain.service.HotelService;

import hbs.hotel_booking_servise.mapper.HotelMapper;

import hbs.hotel_booking_servise.statistics.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.test.context.support.WithMockUser;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.utility.MountableFile;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


class HotelControllerTest extends AbstractTest {




    @Test
    @WithMockUser(username = "user", authorities =  {"USER"})
    public void getAllTest() throws Exception{
        System.out.println("Start Get All test");


        mockMvc.perform(get("/api/v1/hotel")).andExpect(
                        status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.count", is(3))) // Проверяем, что размер массива равен 2
                .andExpect(jsonPath("$.listResponse[0].id", is(1))) // Проверяем параметры первого объекта
                .andExpect(jsonPath("$.listResponse[0].city", is("New York")))
                .andExpect(jsonPath("$.listResponse[1].id", is(2))) // Проверяем параметры второго объекта
                .andExpect(jsonPath("$.listResponse[1].name", is("Parkside Inn")))
                .andExpect(jsonPath("$.listResponse[1].city", is("Los Angeles")))
        ;

    }

    @Test
    @WithMockUser(username = "user", authorities =  {"USER"})
    public void filterByTest() throws Exception{
        // Создание фильтра
        String filterParams = "?city=Los Angeles";

        mockMvc.perform(get("/api/v1/hotel/filter" + filterParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.listResponse[0].id", is(2)))
                .andExpect(jsonPath("$.listResponse[0].city", is("Los Angeles")))
                .andExpect(jsonPath("$.listResponse[0].address", is("456 Elm St, Near the Park")))
                .andExpect(jsonPath("$.count", is(3)))
                ;

    }

}