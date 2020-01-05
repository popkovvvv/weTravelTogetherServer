package com.weTravelTogether.Service;

import com.weTravelTogether.Service.utils.JwtTokenUtil;
import com.weTravelTogether.Service.utils.UserUtil;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.models.User;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.EventRequest;
import com.weTravelTogether.repos.EventRepository;
import com.weTravelTogether.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;

@Service
public class EventService {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserUtil userUtil;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public MessageRequest createEvent(HttpServletRequest httpServletRequest, EventRequest eventRequest){
        User user = userUtil.getUserObject(httpServletRequest);
        Event event = new Event();
        event.setTitle(eventRequest.getTitle());
        event.setCity(eventRequest.getCity());
        event.setRegion(eventRequest.getRegion());
        event.setLatitude(eventRequest.getLatitude());
        event.setLongitude(eventRequest.getLongitude());
        event.getUserList().add(user);
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
        eventRepository.save(event);

        return new MessageRequest("update", HttpStatus.OK.value());

    }
}
