package com.gmail.deniska1406sme.sceduleservice.controller;


import com.gmail.deniska1406sme.sceduleservice.model.Event;
import com.gmail.deniska1406sme.sceduleservice.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/find")
    public List<Event> findAll(@RequestParam Long userId) {
        return eventService.findAll(userId);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public Event create(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id, @RequestBody Event event) {
        return eventService.findById(id)
                .map(existing -> ResponseEntity.ok(eventService.updateEvent(id, event)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (eventService.findById(id).isPresent()) {
            eventService.deleteEvent(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
