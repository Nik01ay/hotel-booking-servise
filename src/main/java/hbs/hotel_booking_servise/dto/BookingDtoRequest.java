package hbs.hotel_booking_servise.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookingDtoRequest {

    private Long roomId;
    private Long userId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate checkIn;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private LocalDate checkOut;
}