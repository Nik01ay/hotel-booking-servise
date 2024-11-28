package hbs.hotel_booking_servise.controller;


import hbs.hotel_booking_servise.AbstractTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import hbs.hotel_booking_servise.domain.repository.HotelRepo;
import hbs.hotel_booking_servise.domain.service.HotelService;
import hbs.hotel_booking_servise.dto.BookingDtoRequest;
import hbs.hotel_booking_servise.dto.HotelDtoRequest;
import hbs.hotel_booking_servise.dto.RoomDtoRequest;
import hbs.hotel_booking_servise.mapper.HotelMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


class BookingControllerTest extends AbstractTest {

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void createFreeRoomBookingTest() throws Exception {
        System.out.println(" - createFreeRoomBookingTest - ");

        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date5 = LocalDate.of(2023, 1, 5);

        BookingDtoRequest bookingRequest = BookingDtoRequest.builder()
                .roomId(4L)
                .checkIn(date1)
                .checkOut(date5)
                .build();

        String content = "{\"roomId\":" + bookingRequest.getRoomId()
                + ",\"checkIn\":\"" + bookingRequest.getCheckIn()
                + "\",\"checkOut\":\"" + bookingRequest.getCheckOut() + "\"}";
        System.out.println("request - " + bookingRequest);
        System.out.println("mapping bookingRequest.toString();" + content);

        mockMvc.perform(post("/api/v1/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.roomId", is(4)))
                .andExpect(jsonPath("$.checkIn", is("2023-01-01")))
                .andExpect(jsonPath("$.checkOut", is("2023-01-05")));


    }

    @Test
    @WithMockUser(username = "user", authorities = {"USER"})
    void createUsedRoomBookingTest() throws Exception {
        System.out.println(" - createFreeRoomBookingTest - ");
        LocalDate date2 = LocalDate.of(2023, 1, 2);
        LocalDate date3 = LocalDate.of(2023, 1, 18);


        BookingDtoRequest bookingRequest = BookingDtoRequest.builder()
                .roomId(1L)
                .checkIn(date2)
                .checkOut(date3)
                .build();

        String content = "{\"roomId\":" + bookingRequest.getRoomId()
                + ",\"checkIn\":\"" + bookingRequest.getCheckIn()
                + "\",\"checkOut\":\"" + bookingRequest.getCheckOut() + "\"}";
        System.out.println("bookingRequest.toString();" + content);

        mockMvc.perform(post("/api/v1/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", is("Room is used")));


    }


}