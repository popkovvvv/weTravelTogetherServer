package com.weTravelTogether.controllers;

import com.weTravelTogether.models.entities.UserProfile;
import com.weTravelTogether.models.request.UserProfileRequest;
import com.weTravelTogether.repos.UserProfileRepository;
import com.weTravelTogether.security.SecurityService;
import com.weTravelTogether.service.UserService;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.repos.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "Обновление профиля для авториз пользавателя", response = MessageRequest.class)
    @PostMapping(value = "/user/profile/update")
    public MessageRequest postUpdateProfile(@ModelAttribute("formUpdate") UserProfileRequest profile) throws Exception {
        long userId = securityService.getLoggedUserId();
        return userService.updateUserProfile(profile, userId);
    }

    @ApiOperation(value = "Обновление профиля по ID", response = MessageRequest.class)
    @PostMapping(value = "/user/profile/{id}/update")
    public MessageRequest postUpdateProfileById(@ModelAttribute("formUpdate") UserProfileRequest profile,
                                                @PathVariable String id) throws Exception {
        long userId = Long.parseLong(id);
        return userService.updateUserProfile(profile, userId);
    }

    @ApiOperation(value = "Вернет профиль авториз пользавателя", response = UserProfile.class)
    @GetMapping(value = "/user/profile")
    public UserProfile getAccountProfile(HttpServletRequest request) throws Exception {
        Long userId = securityService.getLoggedUserId();
        return userProfileRepository.findUserProfileByUserId(userId);
    }

    @ApiOperation(value = "Вернет профиль по ID", response = UserProfile.class)
    @GetMapping(value = "/user/profile/{id}")
    public UserProfile getAccountProfile(@PathVariable String id) throws Exception {
        long profileId = Long.parseLong(id);
        return userProfileRepository.findUserProfileByUserId(profileId);
    }

}
