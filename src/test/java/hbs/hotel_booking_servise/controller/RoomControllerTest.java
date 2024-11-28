package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.AbstractTest;


import hbs.hotel_booking_servise.domain.repository.HotelRepo;
import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.dto.BookingDtoRequest;
import hbs.hotel_booking_servise.dto.HotelDtoRequest;
import hbs.hotel_booking_servise.dto.RoomDtoRequest;
import hbs.hotel_booking_servise.mapper.HotelMapper;
import hbs.hotel_booking_servise.specification.RoomFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class RoomControllerTest extends AbstractTest {



    @Test
     @WithMockUser(username = "user", authorities =  {"USER"})
    public void getAllTest() throws Exception {
        System.out.println("Start Get All test");
        mockMvc.perform(get("/api/v1/room")).andExpect(
                        status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.count", is(6))) // Проверяем, что размер массива равен4
                .andExpect(jsonPath("$.listResponse[0].id", is(1)))
                .andExpect(jsonPath("$.listResponse[0].hotelId", is(1)))// Проверяем параметры первого объекта

        ;

    }

    @Test
     @WithMockUser(username = "user", authorities =  {"USER"})
    public void filterByTest() throws Exception {
        // Создание фильтра
        String filterParams = "?capacity=5&name=Room 302";

        mockMvc.perform(get("/api/v1/room/filter" + filterParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.listResponse[0].id", is(6)))
                .andExpect(jsonPath("$.listResponse[0].hotelId", is(3)))
                .andExpect(jsonPath("$.listResponse", hasSize(1)))
                .andExpect(jsonPath("$.count", is(6)))
        ;

    }

    @Test
    @WithMockUser(username = "user", authorities =  {"USER"})
    public void filterByDateTest() throws Exception {

        RoomFilter filter = new RoomFilter();
        LocalDate  date1 = LocalDate.of(2023, 1, 1);
        LocalDate date8 = LocalDate.of(2023, 1, 8);
        filter.setDateStartRange(date1);
        filter.setDateEndRange(date8);

        String filterParams = "?dateStartRange=" +filter.getDateStartRange() + "&dateEndRange=" + filter.getDateEndRange();


        System.out.println("filterParams-" + filterParams);


        mockMvc.perform(get("/api/v1/room/filter" + filterParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.listResponse", hasSize(4)))
                .andExpect(jsonPath("$.count", is(6)))
        ;

    }


}
