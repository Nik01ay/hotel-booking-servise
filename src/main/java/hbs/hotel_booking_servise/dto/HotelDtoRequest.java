package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class HotelDtoRequest {



        private String name;

        private String announce;

        private String city;

        private String address;

        private Float distance;
    }

