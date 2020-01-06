package com.weTravelTogether.controllers;

import com.weTravelTogether.Service.UserService;
import com.weTravelTogether.models.User;
import com.weTravelTogether.models.UserGeo;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.UserGeoRequest;
import com.weTravelTogether.pogos.UserProfile;
import com.weTravelTogether.repos.UserGeoRepository;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.Service.utils.JwtTokenUtil;
import com.weTravelTogether.Service.utils.UserUtil;
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
     JwtTokenUtil jwtTokenUtil;

    @Autowired
    UserUtil userUtil;

    @Autowired
    UserService userService;

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
        User user = userUtil.getUserObject(httpServletRequest);
        if (!user.isVisibleGeo()){
            return new MessageRequest("visible false", HttpStatus.BAD_REQUEST.value());
        }

        UserGeo userGeo = userGeoRepository.getDistinctFirstByUser_EmailOrderByIdDesc(user.getEmail());
        if (userGeo == null){
            return new MessageRequest("dont have geo", HttpStatus.BAD_REQUEST.value());
        }
        return userGeo;
    }
}
