package com.event.EventManagement.service;

import com.event.EventManagement.entity.Event;
import com.event.EventManagement.entity.User;

import java.util.Set;

public interface UserService {

    User createUser(User u);

    User getUserById(Long id);

    Set<Event> updateEventByUserId(Long eventId);

    Set<Event> getAllUserEvents();

    void deleteEventByUserId(Long eventId);

}
