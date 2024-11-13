package hbs.hotel_booking_servise.controller;

import hbs.hotel_booking_servise.AbstractTest;
import hbs.hotel_booking_servise.dto.BookingDto;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.dto.RoomDto;

import hbs.hotel_booking_servise.specification.RoomFilter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

        HotelDto.Request request = HotelDto.Request.builder()
                .name("Hotel1")
                .city("Gopensk")
                .address("adres12345")
                .distance(23F)
                .announce("announce1")
                .build();
        hotelService.create(request);


        hotelService.create(HotelDto.Request.builder()
                .name("Hotel2")
                .city("Zagopensk")
                .address("adres123456")
                .distance(234F)
                .announce("announce2")
                .build());

        roomService.create(RoomDto.Request.builder()
                .name("BigRoom")
                .capacity(5)
                .description("description")
                .price(500)
                .hotelId(1L)
                .number("5-15")
                .build()
        );


        roomService.create(RoomDto.Request.builder()
                .name("SmallRoom")
                .capacity(2)
                .description("description")
                .price(200)
                .hotelId(1L)
                .number("1-10")
                .build()
        );

        roomService.create(RoomDto.Request.builder()
                .name("SmallRoom")
                .capacity(2)
                .description("description")
                .price(100)
                .hotelId(2L)
                .number("2-99")
                .build()
        );
        roomService.create(RoomDto.Request.builder()
                .name("BigRoom")
                .capacity(8)
                .description("description")
                .price(300)
                .hotelId(2L)
                .number("1-99")
                .build()
        );
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                .withZone(ZoneId.systemDefault());

        bookingService.create(BookingDto.Request.builder()
                .roomId(1L)
                .checkIn(date5)
                .checkOut(date15)
                .build());


        bookingService.create(BookingDto.Request.builder()
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
     @WithMockUser(username = "user", authorities =  {"USER"})
    public void getAllTest() throws Exception {
        System.out.println("Start Get All test");
        mockMvc.perform(get("/api/v1/room")).andExpect(
                        status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.count", is(4))) // Проверяем, что размер массива равен4
                .andExpect(jsonPath("$.listResponse[0].id", is(1)))
                .andExpect(jsonPath("$.listResponse[0].hotelId", is(1)))// Проверяем параметры первого объекта

        ;

    }

    @Test
     @WithMockUser(username = "user", authorities =  {"USER"})
    public void filterByTest() throws Exception {
        // Создание фильтра
        String filterParams = "?hotelId=1&name=BigRoom";
        //String filterParams = "?capacity=3";

        mockMvc.perform(get("/api/v1/room/filter" + filterParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.listResponse[0].id", is(1)))
                .andExpect(jsonPath("$.listResponse[0].hotelId", is(1)))
                .andExpect(jsonPath("$.listResponse", hasSize(1)))
                .andExpect(jsonPath("$.count", is(4)))
        ;

    }

    @Test
    @WithMockUser(username = "user", authorities =  {"USER"})
    public void filterByDateTest() throws Exception {



        RoomFilter filter = new RoomFilter();
        filter.setDateStartRange(date1);
        filter.setDateEndRange(date5);

        String filterParams = "?dateStartRange=" +filter.getDateStartRange() + "&dateEndRange=" + filter.getDateEndRange();


        System.out.println("filterParams-" + filterParams);

        bookingService.findAll().getListResponse().forEach(System.out::println);
        mockMvc.perform(get("/api/v1/room/filter" + filterParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.listResponse[0].id", is(1)))
                .andExpect(jsonPath("$.listResponse[0].hotelId", is(1)))
                .andExpect(jsonPath("$.listResponse", hasSize(3)))
                .andExpect(jsonPath("$.count", is(4)))
        ;





    }


}
