package com.event.EventManagement.controller;

import com.event.EventManagement.Repository.UserRepository;
import com.event.EventManagement.entity.Event;
import com.event.EventManagement.entity.User;
import com.event.EventManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User u) {
        return userService.createUser(u);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/event/{eventId}")
    public Set<Event> updateEventByUser(@PathVariable Long eventId) {
        return userService.updateEventByUserId(eventId);
    }

    @GetMapping("/event")
    public Set<Event> getAllEventByUser(@PathVariable Long userId) {
        return userService.getAllUserEvents();
    }

    @DeleteMapping("/event/{eventId}")
    public void deleteEventByUserId(@PathVariable Long eventId) {
        userService.deleteEventByUserId(eventId);
    }
}
