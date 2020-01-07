package com.weTravelTogether.controllers;

import com.weTravelTogether.security.SecurityService;
import com.weTravelTogether.service.UserService;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.UserProfile;
import com.weTravelTogether.repos.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;


    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "Обновление профиля для авториз пользавателя", response = MessageRequest.class)
    @PostMapping(value = "/user/profile/update")
    public MessageRequest postUpdateProfile(@ModelAttribute("formUpdate") UserProfile profile,
                                            HttpServletRequest request) throws Exception {
        return userService.updateUserProfile(profile, request);
    }

    @ApiOperation(value = "Обновление профиля по ID", response = MessageRequest.class)
    @PostMapping(value = "/user/profile/{id}/update")
    public MessageRequest postUpdateProfileById(@ModelAttribute("formUpdate") UserProfile profile,
                                                @PathVariable String id) throws Exception {
        long profileId = Long.parseLong(id);
        return userService.updateUserProfileById(profile, profileId);
    }

    @ApiOperation(value = "Вернет профиль авториз пользавателя", response = UserProfile.class)
    @GetMapping(value = "/user/profile")
    public UserProfile getAccountProfile(HttpServletRequest request) throws Exception {
        Long userId = securityService.getLoggedUserId();
        return userService.getUserProfileById(userId);
    }

    @ApiOperation(value = "Вернет профиль по ID", response = UserProfile.class)
    @GetMapping(value = "/user/profile/{id}")
    public UserProfile getAccountProfile(@PathVariable String id) throws Exception {
        long profileId = Long.parseLong(id);
        return userService.getUserProfileById(profileId);
    }

}
