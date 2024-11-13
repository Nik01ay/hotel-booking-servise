package hbs.hotel_booking_servise.statistics;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

import java.util.UUID;


public interface KafkaMessageListener {


    @KafkaListener(
            autoStartup = "${app.kafka.kafkaListenerAutoStartup}",
            topics =  {"${app.kafka.kafkaUserMessageTopic}", "${app.kafka.kafkaBookingMessageTopic}"},
            // todo не смог передать значение топика из реализации
            groupId = "${app.kafka.kafkaMessageGroupId}",
            containerFactory = "kafkaMessageConcurrentKafkaListenerContainerFactory"
    )
    public default void listen(@Payload KafkaMessage message,
                               @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) UUID key,
                               @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                               @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                               @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {
        action(message, key, topic, partition,timestamp);
    }

    public void action(KafkaMessage message,UUID key , String topic, Integer partition, Long timestamp);
    //todo плохо что плохо много параметров в методе

}
