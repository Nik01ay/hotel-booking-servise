package hbs.hotel_booking_servise.statistics;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDataNewUser implements KafkaMessage {
    private UUID uuid;
    private Long id;

}
