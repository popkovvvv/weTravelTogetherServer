package com.weTravelTogether.controllers;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.models.AccountGeo;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.models.EventGeo;
import com.weTravelTogether.pogos.EventGeoRequest;
import com.weTravelTogether.pogos.UserGeoRequest;
import com.weTravelTogether.repos.AccountGeoRepository;
import com.weTravelTogether.repos.AccountRepository;
import com.weTravelTogether.repos.EventGeoRepository;
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
    EventGeoRepository eventGeoRepository;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping(path="/event/get/{title}") // Map ONLY POST Requests
    public EventGeo getCurrentGeo(HttpServletRequest httpServletRequest, @PathVariable String title) throws Exception {
        return eventGeoRepository.getEventGeoByEvent_TitleOrderByIdDesc(title);
    }

    @PostMapping(path="/event/create") // Map ONLY POST Requests
    public EventGeo postCreteEvent (HttpServletRequest httpServletRequest,
                                    @ModelAttribute("userGeoRequest") EventGeoRequest eventGeoRequest) throws Exception {
        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(httpServletRequest);
        Optional<Account> accountOptional =  accountRepository.findByEmail(userDetails.getUsername());
        Account account = accountOptional.get();

        Event event = new Event();
        event.setTitle(eventGeoRequest.getTitle());
        eventRepository.save(event);

        account.setEvent(event);
        accountRepository.save(account);

        EventGeo eventGeo = new EventGeo();
        eventGeo.setEvent(event);
        eventGeo.setCity(eventGeoRequest.getCity());
        eventGeo.setRegion(eventGeoRequest.getRegion());
        eventGeo.setLatitude(eventGeoRequest.getLatitude());
        eventGeo.setLongitude(eventGeoRequest.getLongitude());

        eventGeoRepository.save(eventGeo);


        return eventGeo;

    }
}
