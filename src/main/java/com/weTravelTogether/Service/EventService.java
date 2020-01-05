package com.weTravelTogether.Service;

import com.weTravelTogether.Service.utils.JwtTokenUtil;
import com.weTravelTogether.Service.utils.UserUtil;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.models.User;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.EventGeoRequest;
import com.weTravelTogether.repos.EventRepository;
import com.weTravelTogether.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;

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

    public MessageRequest createEvent(HttpServletRequest httpServletRequest, EventGeoRequest eventGeoRequest){
        User user = userUtil.getUserObject(httpServletRequest);
        Event event = new Event();
        event.setTitle(eventGeoRequest.getTitle());
        event.setCity(eventGeoRequest.getCity());
        event.setRegion(eventGeoRequest.getRegion());
        event.setLatitude(eventGeoRequest.getLatitude());
        event.setLongitude(eventGeoRequest.getLongitude());
        eventRepository.save(event);

        user.setEvent(event);
        userRepository.save(user);

        return new MessageRequest("create", HttpStatus.OK.value());

    }
}
