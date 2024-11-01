package hbs.hotel_booking_servise.dto;

import lombok.*;

import java.util.List;

public enum RoomDto {;
    @Data
    @Builder
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
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static   class ListResponseCount{

        private List<RoomDto.Response> listResponse;
        private long count;
    }
}
