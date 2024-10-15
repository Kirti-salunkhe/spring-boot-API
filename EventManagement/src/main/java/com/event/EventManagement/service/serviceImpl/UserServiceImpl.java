package com.event.EventManagement.service.serviceImpl;

import com.event.EventManagement.Repository.UserRepository;
import com.event.EventManagement.entity.Event;
import com.event.EventManagement.entity.User;
import com.event.EventManagement.service.EventService;
import com.event.EventManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EventService eventService;

//    public User getLoggedInUser(){
//        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
//        String email=authentication.getName();
//        return userRepository.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User not found"));
//    }

    public User getLoggedInUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("not found"));
    }

    @Override
    public User createUser(User u) {
        if (userRepository.existsByEmail(u.getEmail())) {
            throw new RuntimeException("Email already exist");
        }
        u.setRoles(Arrays.asList("USER"));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public Set<Event> updateEventByUserId(Long eventId) {
        Set<Event> allEvents;
        User u = getLoggedInUser();
        Event e = eventService.getEventById(eventId);
        allEvents = u.getMyevents();
        allEvents.add(e);
        u.setMyevents(allEvents);
        userRepository.save(u);
        return u.getMyevents();
    }

    @Override
    public Set<Event> getAllUserEvents() {
        User u = getLoggedInUser();
        return u.getMyevents();
    }

    public void deleteEventByUserId(Long eventId) {
        Set<Event> allEvents;
        User user = getLoggedInUser();
        allEvents = user.getMyevents();
        Set<Event> events = allEvents.stream().filter(e -> eventId != e.getId()).collect(Collectors.toSet());
        user.setMyevents(events);
        userRepository.save(user);
    }

}
