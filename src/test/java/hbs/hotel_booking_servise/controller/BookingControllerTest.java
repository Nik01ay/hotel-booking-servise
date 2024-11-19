package hbs.hotel_booking_servise.controller;


import hbs.hotel_booking_servise.AbstractTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.Module;
//import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import hbs.hotel_booking_servise.dto.BookingDtoRequest;
import hbs.hotel_booking_servise.dto.HotelDtoRequest;
import hbs.hotel_booking_servise.dto.RoomDtoRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



class BookingControllerTest extends AbstractTest {


    private LocalDate date1;
    private LocalDate date5;
    private LocalDate date10;
    private LocalDate date15;

    @BeforeEach
    public void setup() {


        date1 = LocalDate.of(2023, 1, 1);
        date5 = LocalDate.of(2023, 1, 5);
        date10 = LocalDate.of(2023, 1, 10);
        date15 = LocalDate.of(2023, 1, 15);

        HotelDtoRequest request = HotelDtoRequest.builder()
                .name("Hotel1")
                .city("Gopensk")
                .address("adres12345")
                .distance(23F)
                .announce("announce1")
                .build();
        hotelService.create(request);


        hotelService.create(HotelDtoRequest.builder()
                .name("Hotel2")
                .city("Zagopensk")
                .address("adres123456")
                .distance(234F)
                .announce("announce2")
                .build());

        roomService.create(RoomDtoRequest.builder()
                .name("BigRoom")
                .capacity(5)
                .description("description")
                .price(500)
                .hotelId(1L)
                .number("5-15")
                .build()
        );


        roomService.create(RoomDtoRequest.builder()
                .name("SmallRoom")
                .capacity(2)
                .description("description")
                .price(200)
                .hotelId(1L)
                .number("1-10")
                .build()
        );

        roomService.create(RoomDtoRequest.builder()
                .name("SmallRoom")
                .capacity(2)
                .description("description")
                .price(100)
                .hotelId(2L)
                .number("2-99")
                .build()
        );
        roomService.create(RoomDtoRequest.builder()
                .name("BigRoom")
                .capacity(8)
                .description("description")
                .price(300)
                .hotelId(2L)
                .number("1-99")
                .build()
        );


        bookingService.create(BookingDtoRequest.builder()
                .roomId(1L)
                .checkIn(date5)
                .checkOut(date15)
                .build());


        bookingService.create(BookingDtoRequest.builder()
                .roomId(2L)
                .checkIn(date1)
                .checkOut(date10)
                .build());


    }

    @AfterEach
    public void afterEach() {
        hotelService.deleteAll();
        roomService.deleteAll();
        bookingService.deleteAll();
    }

    @Test
    void getAll() {
    }


    @Test
    @WithMockUser(username = "user", authorities =  {"USER"})
    void createFreeRoomBookingTest() throws Exception {
        System.out.println(" - createFreeRoomBookingTest - ");

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
    @WithMockUser(username = "user", authorities =  {"USER"})
    void createUsedRoomBookingTest() throws Exception {
        System.out.println(" - createFreeRoomBookingTest - ");
        BookingDtoRequest bookingRequest = BookingDtoRequest.builder()
                .roomId(1L)
                .checkIn(date1)
                .checkOut(date10)
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