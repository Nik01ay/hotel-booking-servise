package hbs.hotel_booking_servise.dto;

import lombok.*;

public enum UserDto {;
    @Data
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class Request {

        private String name;

        private String password;

        private String email;

    }
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {

        private Long id;

        private String name;

        private String password;

        private String email;

        private String role;
    }

}
