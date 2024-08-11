package com.example.room.kafka;

import com.base.base.dto.BookingEventDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Service
public class BookingConsumer {
    @KafkaListener(
            topics = {"${spring.kafka.template.default-topic}"},
            groupId = "${spring.kafka.consumer.group-id}"
    )

    public void consume(BookingEventDTO message) {
        log.info(String.format("Receiving booking event -> %s", message));
    }

}
