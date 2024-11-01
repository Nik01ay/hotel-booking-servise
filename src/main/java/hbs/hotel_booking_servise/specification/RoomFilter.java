package hbs.hotel_booking_servise.specification;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@NoArgsConstructor
public class RoomFilter {

    private Long roomId;

    private String name;

    private Integer minCost;

    private Integer maxCost;

    private Integer capacity;

    private Long hotelId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateStartRange;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateEndRange;

    private Integer pageSize = 20;

    private Integer pageNumber = 0;

}
