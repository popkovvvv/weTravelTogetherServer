package com.weTravelTogether.controllers;

import com.weTravelTogether.models.entities.UserProfile;
import com.weTravelTogether.repos.UserProfileRepository;
import com.weTravelTogether.security.SecurityService;
import com.weTravelTogether.service.UserService;
import com.weTravelTogether.models.entities.UserGeo;
import com.weTravelTogether.models.request.MessageRequest;
import com.weTravelTogether.models.request.UserGeoRequest;
import com.weTravelTogether.repos.UserGeoRepository;
import com.weTravelTogether.repos.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserGeoController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGeoRepository userGeoRepository;

    @Autowired
    UserService userService;

    @Autowired
    SecurityService securityService;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Transactional
    @ApiOperation(value = "Обновление геолокации у авториз пользавателя", response = MessageRequest.class)
    @PostMapping(path="/user/geo/update")
    public MessageRequest postUpdateGeo (HttpServletRequest httpServletRequest,
                                         @ModelAttribute("userGeoRequest") UserGeoRequest userGeoRequest) throws Exception {
        return userService.updateUserGeo(userGeoRequest, httpServletRequest);
    }

    @Transactional
    @ApiOperation(value = "Получение геолокации у авториз пользавателя", response = MessageRequest.class)
    @GetMapping(path="/user/geo")
    public Object getUserGeo (HttpServletRequest httpServletRequest) throws Exception {
        long userID = securityService.getLoggedUserId();

        UserProfile userProfile = userProfileRepository.findUserProfileByUserId(userID);
        if (!userProfile.isVisibleGeo()){
            return new MessageRequest("visible false", HttpStatus.BAD_REQUEST.value());
        }

        UserGeo userGeo = userGeoRepository.findUserGeoByUserId(userID);
        if (userGeo == null){
            return new MessageRequest("dont have geo", HttpStatus.BAD_REQUEST.value());
        }
        return userGeo;
    }
}
