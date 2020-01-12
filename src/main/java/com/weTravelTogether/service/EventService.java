package com.weTravelTogether.service;

import com.weTravelTogether.security.SecurityService;
import com.weTravelTogether.models.entities.Event;
import com.weTravelTogether.models.entities.User;
import com.weTravelTogether.models.request.MessageRequest;
import com.weTravelTogether.models.request.EventRequest;
import com.weTravelTogether.repos.EventRepository;
import com.weTravelTogether.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SecurityService securityService;

    public MessageRequest createEvent(HttpServletRequest httpServletRequest, EventRequest eventRequest){
        long userID = securityService.getLoggedUserId();
        Optional<User> userOptional = userRepository.findById(userID);
        User user = userOptional.get();

        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setCity(eventRequest.getCity());
        event.setRegion(eventRequest.getRegion());
        event.setLatitude(eventRequest.getLatitude());
        event.setLongitude(eventRequest.getLongitude());
        event.getUserList().add(user);
        event.setUpdatedAt(new Date());
        event.setCreatedAt(new Date());
        eventRepository.save(event);

        user.getEvents().add(event);
        userRepository.save(user);

        return new MessageRequest("create", HttpStatus.OK.value());
    }

    public MessageRequest updateEvent(Event event ,EventRequest eventRequest){
        event.setTitle(eventRequest.getTitle());
        event.setCity(eventRequest.getCity());
        event.setRegion(eventRequest.getRegion());
        event.setLatitude(eventRequest.getLatitude());
        event.setLongitude(eventRequest.getLongitude());
        event.setUpdatedAt(new Date());
        eventRepository.save(event);
        return new MessageRequest("update", HttpStatus.OK.value());
    }
}
