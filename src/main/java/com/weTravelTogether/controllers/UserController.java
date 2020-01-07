package com.weTravelTogether.controllers;

import com.weTravelTogether.Service.UserService;
import com.weTravelTogether.models.Event;
import com.weTravelTogether.models.User;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.UserProfile;
import com.weTravelTogether.repos.UserRepository;
import com.weTravelTogether.pogos.JwtRequest;
import com.weTravelTogether.Service.utils.JwtTokenUtil;
import com.weTravelTogether.security.JwtUserDetailsService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    public UserController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "Вернет JSON с токеном", response = JwtTokenUtil.class)
    @PostMapping(path="/registration") // Map ONLY POST Requests
    public Object registration (@RequestParam String email, @RequestParam String password) throws Exception {

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setVisibleGeo(true);
        userRepository.save(user);

        return jwtTokenUtil.authenticateJwt(email, password, "Registration");
    }

    @ApiOperation(value = "Вернет JSON с токеном", response = JwtTokenUtil.class)
    @PostMapping(value = "/login")
    public Object createAuthenticationToken(@ModelAttribute("formLogin") JwtRequest authenticationRequest,
                                                 HttpServletRequest request) throws Exception {
        boolean checkAuth = jwtTokenUtil.checkAuthenticate(request, authenticationRequest.getEmail());
        if (checkAuth) {
            return new MessageRequest("Auth is true", HttpStatus.BAD_REQUEST.value());
        }
        return jwtTokenUtil.authenticateJwt(authenticationRequest.getEmail(), authenticationRequest.getPassword(), "Login");
    }

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
        return userService.getUserProfile(request);
    }

    @ApiOperation(value = "Вернет профиль по ID", response = UserProfile.class)
    @GetMapping(value = "/user/profile/{id}")
    public UserProfile getAccountProfile(@PathVariable String id) throws Exception {
        long profileId = Long.parseLong(id);
        return userService.getUserProfileById(profileId);
    }

    @ApiOperation(value = "НЕ РЕАЛИЗОВАНО", response = UserProfile.class)
    @GetMapping(value = "/logout")
    public MessageRequest logout() throws Exception {
        jwtTokenUtil.deleteToken();
        return new MessageRequest("logout ok", HttpStatus.OK.value());
    }

}
