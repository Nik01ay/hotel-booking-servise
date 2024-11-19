package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelDtoResponse {


    private Long id;

    private String name;

    private String announce;

    private String city;

    private String address;

    private Float distance;

    private Float rating;

    private Integer votes;

}
