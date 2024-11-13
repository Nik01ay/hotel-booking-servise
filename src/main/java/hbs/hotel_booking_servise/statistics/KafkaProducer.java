package hbs.hotel_booking_servise.statistics;

import hbs.hotel_booking_servise.dto.BookingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaMessageManager kafkaMessageManager;
    @Value("${app.kafka.kafkaUserMessageTopic}")
    private String userTopicName;
    @Value("${app.kafka.kafkaBookingMessageTopic}")
    private String bookingTopicName;

    public void sendToKafkaUserEvent(Long id) {
        EventDataNewUser user = new EventDataNewUser();
        user.setId(id);
        user.setUuid(UUID.randomUUID());

        kafkaMessageManager.sendUser(userTopicName, user);
    }

    public void sendToKafkaBookingEvent(BookingDto.Response booking){
        EventDataNewBooking eventBooking = new EventDataNewBooking();

        eventBooking.setUserId(booking.getUserId());
        eventBooking.setCheckIn(booking.getCheckIn());
        eventBooking.setCheckOut(booking.getCheckOut());

        kafkaMessageManager.sendBooking(bookingTopicName, eventBooking);
    }
}
