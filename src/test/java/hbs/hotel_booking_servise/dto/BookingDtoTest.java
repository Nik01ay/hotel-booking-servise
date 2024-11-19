package hbs.hotel_booking_servise.dto;

import hbs.hotel_booking_servise.domain.entity.Booking;
import hbs.hotel_booking_servise.mapper.BookingMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookingDtoTest {

   @Autowired
    BookingMapper mapper;
    @Test
    void requestToEntityTest() throws Exception {

        LocalDate date1 = LocalDate.of(2023, 1, 1);
        LocalDate date5 = LocalDate.of(2023, 1, 5);
        LocalDate date10 = LocalDate.of(2023, 1, 10);
        LocalDate date15 = LocalDate.of(2023, 1, 15);

        BookingDtoRequest request1 = BookingDtoRequest.builder()
                .roomId(1L)
                .checkIn(date5)
                .checkOut(date15)
                .build();


        BookingDtoRequest request2 = (BookingDtoRequest.builder()
                .roomId(2L)
                .checkIn(date1)
                .checkOut(date10)
                .build());

        ObjectMapper objectMapper = new ObjectMapper();
        String content =   objectMapper.writeValueAsString(request1);
        System.out.println("testStart");
        System.out.println("request is - ");
        System.out.println(request1);

       Booking entity1= mapper.requestToEntity(request1);
        Booking entity2= mapper.requestToEntity(request2);
        System.out.println("entity is - ");
        System.out.println(entity1);

        BookingDtoResponse response1 = mapper.entityToResponse(entity1);
        System.out.println("response is - ");
        System.out.println(response1);
    }

}