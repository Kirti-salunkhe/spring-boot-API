package com.event.EventManagement.controller;


import com.event.EventManagement.Repository.EventRepository;
import com.event.EventManagement.entity.Event;
import com.event.EventManagement.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public Page<Event> getAllEvents(Pageable page) {
        return eventService.getAllEvent(page);
    }
}
