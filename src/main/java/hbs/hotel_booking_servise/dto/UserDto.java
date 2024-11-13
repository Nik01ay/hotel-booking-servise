package hbs.hotel_booking_servise.dto;

import hbs.hotel_booking_servise.domain.entity.UserRole;
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
        private String role;

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
