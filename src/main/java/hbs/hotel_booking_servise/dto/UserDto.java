package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

public enum UserDto {;
    @Data
    @NoArgsConstructor

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
