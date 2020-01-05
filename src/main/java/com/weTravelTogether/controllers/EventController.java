package com.weTravelTogether.controllers;

import com.weTravelTogether.Service.EventService;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.models.User;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.EventRequest;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.repos.EventRepository;
import com.weTravelTogether.Service.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@RestController
public class EventController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Autowired
    EventService eventService;

    @GetMapping(path="/event/{id}")
    public Event getEvent(@PathVariable String id) throws Exception {
        long eventId = Long.parseLong(id);
        return eventRepository.findById(eventId);
    }

    @PostMapping(path="/event/{id}/update")
    public MessageRequest postEventUpdate(@PathVariable String id,
                          @ModelAttribute("userGeoRequest") EventRequest eventRequest) throws Exception {
        long eventId = Long.parseLong(id);
        Event event = eventRepository.findById(eventId);
        return eventService.updateEvent(event, eventRequest);
    }

    @PostMapping(path="/event/create")
    public MessageRequest postCreteEvent (HttpServletRequest httpServletRequest,
                          @ModelAttribute("userGeoRequest") EventRequest eventRequest) throws Exception {
        return eventService.createEvent(httpServletRequest, eventRequest);
    }

    @GetMapping(path="/event/delete/{id}")
    public MessageRequest postCreteEvent(@PathVariable String id) throws Exception {
        long eventId = Long.parseLong(id);
        try {
        eventRepository.removeEventById(eventId);
        } catch (Exception e) {
            return new MessageRequest("don't delete", HttpStatus.NOT_FOUND.value());
        }
        return new MessageRequest("delete", HttpStatus.OK.value());
    }
}
