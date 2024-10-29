package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.time.LocalDate;

public enum BookingDto {;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long roomId;

        private String checkIn;

        private String checkOut;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long roomId;

        private Long userId;

        private LocalDate checkIn;

        private LocalDate checkOut;
    }
}
