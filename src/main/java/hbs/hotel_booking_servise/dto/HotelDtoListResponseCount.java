package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotelDtoListResponseCount {


    private List<HotelDtoResponse> listResponse;
    private long count;

}
