package hbs.hotel_booking_servise.dto;

import lombok.*;

import java.util.List;

public enum HotelDto {;
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Request {

        private String name;

        private String announce;

        private String city;

        private String address;

        private Float distance;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;

        private String name;

        private String announce;

        private String city;

        private String address;

        private Float distance;

        private Float rating;

        private Integer votes;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseShort {

        private Long id;

        private String name;

        private String announce;

        private String city;

        private String address;

        private Float distance;
    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static   class ListResponseCount{

        private List<Response> listResponse;
        private long count;
    }
}
