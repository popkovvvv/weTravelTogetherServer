package com.weTravelTogether.controllers;

import com.weTravelTogether.Service.EventService;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.EventGeoRequest;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.repos.EventRepository;
import com.weTravelTogether.Service.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

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

    @PostMapping(path="/event/create")
    public MessageRequest postCreteEvent (HttpServletRequest httpServletRequest,
                                          @ModelAttribute("userGeoRequest") EventGeoRequest eventGeoRequest) throws Exception {
        return eventService.createEvent(httpServletRequest, eventGeoRequest);
    }
}
