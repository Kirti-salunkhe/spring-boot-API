package com.event.EventManagement.service;

import com.event.EventManagement.entity.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EventService {

    Page<Event> getAllEvent(Pageable page);

    Event getEventById(Long id);

    Event createEvent(Event event);

    Event updateEvent(Event event, Long id);

    void deleteEvent(Long id);
}
