package hbs.hotel_booking_servise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

import java.util.List;

public enum BookingDto {;
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private Long roomId;
        private Long userId;

        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
        private LocalDate checkIn;
                @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
        private LocalDate checkOut;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long roomId;

        private Long userId;
        @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
        private LocalDate checkIn;
       @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
        private LocalDate checkOut;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static   class ListResponseCount{

        private List<BookingDto.Response> listResponse;
        private long count;
    }
}
