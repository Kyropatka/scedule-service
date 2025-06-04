package com.gmail.deniska1406sme.sceduleservice.service;

import com.gmail.deniska1406sme.sceduleservice.dto.EventMessage;
import com.gmail.deniska1406sme.sceduleservice.model.Event;
import com.gmail.deniska1406sme.sceduleservice.repository.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final String topicName;

    public EventService(EventRepository eventRepository,
                        KafkaTemplate<String, Object> kafkaTemplate,
                        @Value("${app.kafka.topic.events}") String topicName) {
        this.eventRepository = eventRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.topicName = topicName;
    }

    @Transactional(readOnly = true)
    public List<Event> findAll(Long userId) {
        return eventRepository.findByUserId(userId);
    }
    @Transactional(readOnly = true)
    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    @Transactional
    public Event createEvent(Event event) {
        Event saved = eventRepository.save(event);

        EventMessage msg = new EventMessage(
                saved.getId(), saved.getUserId(),
                saved.getTitle(), saved.getDescription(),
                saved.getStartTime(), saved.getEndTime(),
                saved.getNotificationOffsetMinutes()
        );
        kafkaTemplate.send(topicName, String.valueOf(saved.getId()), msg);
        return saved;
    }

    @Transactional
    public Event updateEvent(Long id, Event event) {
        event.setId(id);
        Event saved = eventRepository.save(event);

        EventMessage msg = new EventMessage(
                saved.getId(), saved.getUserId(),
                saved.getTitle(), saved.getDescription(),
                saved.getStartTime(), saved.getEndTime(),
                saved.getNotificationOffsetMinutes()
        );
        kafkaTemplate.send(topicName, String.valueOf(saved.getId()), msg);
        return saved;
    }

    @Transactional
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }
}
