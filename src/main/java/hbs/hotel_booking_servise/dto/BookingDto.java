package hbs.hotel_booking_servise.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

public enum BookingDto {;
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long roomId;

        private Date checkIn;

        private Date checkOut;
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
