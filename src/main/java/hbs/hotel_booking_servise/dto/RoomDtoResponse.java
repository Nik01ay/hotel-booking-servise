package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class RoomDtoResponse {


        private Long id;

        private String name;

        private String description;

        private String number;

        private Integer price;

        private Integer capacity;

        private Long hotelId;
    }

