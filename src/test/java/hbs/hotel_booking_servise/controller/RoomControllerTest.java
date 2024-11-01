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
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;

import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class RoomControllerTest extends AbstractTest {

    @BeforeEach
    public void setup() {

        HotelDto.Request request = HotelDto.Request.builder()
                .name("Hotel1")
                .city("Gopensk")
                .address("adres12345")
                .distance(23F)
                .announce("announce1")
                .build();
        hotelHandler.create(request);


        hotelHandler.create(HotelDto.Request.builder()
                .name("Hotel2")
                .city("Zagopensk")
                .address("adres123456")
                .distance(234F)
                .announce("announce2")
                .build());

        roomHandler.create(RoomDto.Request.builder()
                .name("BigRoom")
                .capacity(5)
                .description("description")
                .price(500)
                .hotelId(1L)
                .number("5-15")
                .build()
        );


        roomHandler.create(RoomDto.Request.builder()
                .name("SmallRoom")
                .capacity(2)
                .description("description")
                .price(200)
                .hotelId(1L)
                .number("1-10")
                .build()
        );

        roomHandler.create(RoomDto.Request.builder()
                .name("SmallRoom")
                .capacity(2)
                .description("description")
                .price(100)
                .hotelId(2L)
                .number("2-99")
                .build()
        );
        roomHandler.create(RoomDto.Request.builder()
                .name("BigRoom")
                .capacity(8)
                .description("description")
                .price(300)
                .hotelId(2L)
                .number("1-99")
                .build()
        );

        bookingHandler.create(BookingDto.Request.builder()
                .roomId(1L)
                .checkIn(Date.from(Instant.now().plus(5, ChronoUnit.DAYS)))
                .checkOut(Date.from(Instant.now().plus(15, ChronoUnit.DAYS)))

                .build());
        bookingHandler.create(BookingDto.Request.builder()
                .roomId(2L)
                .checkIn(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .checkOut(Date.from(Instant.now().plus(10, ChronoUnit.DAYS)))
                .build());


    }

    @AfterEach
    public void afterEach() {
        hotelHandler.deleteAll();
        roomHandler.deleteAll();
        bookingHandler.deleteAll();
    }

    @Test
    // @WithMockUser(username = "user", roles =  {"USER"})
    public void getAllTest() throws Exception {
        System.out.println("Start Get All test");
        mockMvc.perform(get("/api/v1/room")).andExpect(
                        status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(4))) // Проверяем, что размер массива равен 2
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].hotelId", is(1)))// Проверяем параметры первого объекта

        ;

    }

    @Test
    // @WithMockUser(username = "user", roles =  {"USER"})
    public void filterByTest() throws Exception {
        // Создание фильтра
        String filterParams = "?hotelId=1&name=BigRoom";

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
    // @WithMockUser(username = "user", roles =  {"USER"})
    public void filterByDateTest() throws Exception {
/*
        RoomFilter roomFilter = new RoomFilter();
        roomFilter.setDateStartRange(Date.from(Instant.now()));
        roomFilter.setDateEndRange(Date.from(Instant.now().plus(4, ChronoUnit.DAYS)));

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(roomFilter);

        mockMvc.perform(get("/api/v1/room/filter")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.listResponse[0].id", is(1)))
                .andExpect(jsonPath("$.listResponse[0].hotelId", is(1)))
                .andExpect(jsonPath("$.listResponse", hasSize(3)))
                .andExpect(jsonPath("$.count", is(4)));


 */

        RoomFilter filter = new RoomFilter();
        filter.setDateStartRange(Date.from(Instant.now()));
        filter.setDateEndRange(Date.from(Instant.now().plus(4, ChronoUnit.DAYS)));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


        String encodedDateStartRange = URLEncoder.encode(dateFormat.format(filter.getDateStartRange()), StandardCharsets.UTF_8);
        String encodedDateEndRange = URLEncoder.encode(dateFormat.format(filter.getDateEndRange()), StandardCharsets.UTF_8);

        //String filterParams = "?dateStartRange=" +encodedDateStartRange + "&dateEndRange=" + encodedDateEndRange;
        String filterParams = "?dateStartRange=" +encodedDateStartRange + "&dateEndRange=" + encodedDateEndRange;



        System.out.println("filterParams-" + filterParams);
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