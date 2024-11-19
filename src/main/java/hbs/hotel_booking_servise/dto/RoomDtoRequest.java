package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDtoRequest {


    private String name;

    private String description;

    private String number;

    private Integer price;

    private Integer capacity;

    private Long hotelId;
}

