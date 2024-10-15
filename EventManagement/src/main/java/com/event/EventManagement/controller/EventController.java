package com.event.EventManagement.controller;

import com.event.EventManagement.entity.Event;
import com.event.EventManagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents(Pageable page) {
        return new ResponseEntity<>(eventService.getAllEvent(page).stream().toList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getAllEventById(@PathVariable Long id) {
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event e) {
        return new ResponseEntity<>(eventService.createEvent(e), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody Event e, @PathVariable Long id) {
        return new ResponseEntity<>(eventService.updateEvent(e, id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteEvent(@RequestParam Long id) {
        eventService.deleteEvent(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
