package hbs.hotel_booking_servise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDtoListResponseCount {

    private List<BookingDtoResponse> listResponse;
    private long count;
}

