package hbs.hotel_booking_servise.statistics;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDataNewBooking implements KafkaMessage{
    private UUID uuid;
    private Long id;
    private Long userId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate checkIn;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate checkOut;
}
