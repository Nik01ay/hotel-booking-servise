package hbs.hotel_booking_servise.statistics;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
@RequiredArgsConstructor
public class KafkaMessageManager {


    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;


    public void sendUser(String topicName, EventDataNewUser message) {
            kafkaTemplate.send(topicName, message);
    }

    public void sendBooking(String topicName, EventDataNewBooking message) {
        kafkaTemplate.send(topicName, message);
    }

}
