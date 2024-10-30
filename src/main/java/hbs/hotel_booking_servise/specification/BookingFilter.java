package hbs.hotel_booking_servise.specification;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookingFilter  {

    private Integer pageSize = 20;

    private Integer pageNumber = 0;
}
