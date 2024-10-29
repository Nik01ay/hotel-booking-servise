package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public enum RoomDto {;
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request {

        private String name;

        private String description;

        private String number;

        private Integer price;

        private Integer capacity;

        private Long hotelId;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;

        private String name;

        private String description;

        private String number;

        private Integer price;

        private Integer capacity;

        private Long hotelId;
    }
}
