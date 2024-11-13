package hbs.hotel_booking_servise.statistics;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaMListener implements KafkaMessageListener {
    @Value("${app.kafka.kafkaUserMessageTopic}")
    private String userTopicName;
    @Value("${app.kafka.kafkaBookingMessageTopic}")
    private String bookingTopicName;

    private final StatisticServise statisticServise;

    @Override
    public void action(KafkaMessage message, UUID key , String topic, Integer partition, Long timestamp) {
        log.info("Received message: {}", message);
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);

        if (userTopicName.equals(topic)){
            System.out.println("ZAPIS USERA V MONGO");
            statisticServise.addUser((EventDataNewUser) message);

        }

        if (bookingTopicName.equals(topic)){
            System.out.println("ZAPIS BOOKING V MONGO");
            statisticServise.addBooking((EventDataNewBooking) message);

        }
    }
}