package com.weTravelTogether.controllers;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.pogos.EventGeoRequest;
import com.weTravelTogether.repos.AccountRepository;
import com.weTravelTogether.repos.EventRepository;
import com.weTravelTogether.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class EventGeoController {

    @Autowired
    EventRepository eventRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(path="/event/get") // Map ONLY POST Requests
    public Event getCurrentGeo(HttpServletRequest httpServletRequest, @RequestParam String title) throws Exception {
        return eventRepository.getEventByTitleOrderByIdDesc(title);
    }

    @PostMapping(path="/event/create") // Map ONLY POST Requests
    public Event postCreteEvent (HttpServletRequest httpServletRequest,
                                    @ModelAttribute("userGeoRequest") EventGeoRequest eventGeoRequest) throws Exception {

        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(httpServletRequest);
        Optional<Account> accountOptional =  accountRepository.findByEmail(userDetails.getUsername());
        Account account = accountOptional.get();

        Event event = new Event();
        event.setTitle(eventGeoRequest.getTitle());
        event.setCity(eventGeoRequest.getCity());
        event.setRegion(eventGeoRequest.getRegion());
        event.setLatitude(eventGeoRequest.getLatitude());
        event.setLongitude(eventGeoRequest.getLongitude());
        eventRepository.save(event);

        account.setEvent(event);
        accountRepository.save(account);

        return event;

    }
}
