package com.weTravelTogether.controllers;

import com.weTravelTogether.Service.EventService;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.models.User;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.EventRequest;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.repos.EventRepository;
import com.weTravelTogether.Service.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@Api(value="Event Controller")
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

    @ApiOperation(value = "Вернет JSON конкретного События по ID", response = Event.class)
    @RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
    public Event getEvent(@PathVariable String id) throws Exception {
        long eventId = Long.parseLong(id);
        return eventRepository.findById(eventId);
    }

    @ApiOperation(value = "Обновление конкретного События по ID", response = MessageRequest.class)
    @PostMapping(path="/event/{id}/update")
    public MessageRequest postEventUpdate(@PathVariable String id,
                          @ModelAttribute("userGeoRequest") EventRequest eventRequest) throws Exception {
        long eventId = Long.parseLong(id);
        Event event = eventRepository.findById(eventId);
        return eventService.updateEvent(event, eventRequest);
    }

    @ApiOperation(value = "Создание события", response = MessageRequest.class)
    @PostMapping(path="/event/create")
    public MessageRequest postCreteEvent (HttpServletRequest httpServletRequest,
                          @ModelAttribute("userGeoRequest") EventRequest eventRequest) throws Exception {
        return eventService.createEvent(httpServletRequest, eventRequest);
    }

    @ApiOperation(value = "Удаление события по ID", response = MessageRequest.class)
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
