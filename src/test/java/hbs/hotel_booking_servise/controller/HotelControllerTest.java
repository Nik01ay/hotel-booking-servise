package hbs.hotel_booking_servise.controller;

import static org.junit.jupiter.api.Assertions.*;
import hbs.hotel_booking_servise.AbstractTest;
import hbs.hotel_booking_servise.dto.HotelDto;
import hbs.hotel_booking_servise.dto.UserDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

class HotelControllerTest extends AbstractTest {
    @BeforeEach
    public void setup (){

        HotelDto.Request request =HotelDto.Request.builder()
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

    }
    @AfterEach
    public void afterEach(){
        hotelHandler.deleteAll();
    }

    @Test
    // @WithMockUser(username = "user", roles =  {"USER"})
    public void getAllTest() throws Exception{
        System.out.println("Start Get All test");
        mockMvc.perform(get("/api/v1/hotel")).andExpect(
                        status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$", hasSize(2))) // Проверяем, что размер массива равен 2
                .andExpect(jsonPath("$[0].id", is(1))) // Проверяем параметры первого объекта
                .andExpect(jsonPath("$[0].city", is("Gopensk")))
                .andExpect(jsonPath("$[1].id", is(2))) // Проверяем параметры второго объекта
                .andExpect(jsonPath("$[1].name", is("Hotel2")))
                .andExpect(jsonPath("$[1].city", is("Zagopensk")))
        ;

    }

    @Test
    // @WithMockUser(username = "user", roles =  {"USER"})
    public void filterByTest() throws Exception{
        // Создание фильтра
        String filterParams = "?hotelId=1&city=Gopensk";

        mockMvc.perform(get("/api/v1/hotel/filter" + filterParams)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.listResponse[0].id", is(1)))
                .andExpect(jsonPath("$.listResponse[0].city", is("Gopensk")))
                .andExpect(jsonPath("$.listResponse[0].address", is("adres12345")))
                .andExpect(jsonPath("$.count", is(2)))
                ;

    }



}