package com.example.booking.kafka;

import com.base.base.dto.BookingEventDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookingProducer {
    private final NewTopic bookingTopic;
    private final KafkaTemplate<String, BookingEventDTO> kafkaTemplate;

    public void sendMessage(BookingEventDTO bookingEventDTO) {
        log.info(String.format("Sending Booking event to topic -> %s", bookingEventDTO));

        Message<BookingEventDTO> message = MessageBuilder
                .withPayload(bookingEventDTO)
                .setHeader("kafkaTopic", bookingTopic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
