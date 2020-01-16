package com.weTravelTogether.service;

import com.weTravelTogether.models.entities.UserProfile;
import com.weTravelTogether.models.exception.DuplicateEmailException;
import com.weTravelTogether.models.request.UserProfileRequest;
import com.weTravelTogether.models.request.UserRegistration;
import com.weTravelTogether.repos.UserProfileRepository;
import com.weTravelTogether.security.SecurityService;
import com.weTravelTogether.models.entities.User;
import com.weTravelTogether.models.entities.UserGeo;
import com.weTravelTogether.models.request.MessageRequest;
import com.weTravelTogether.models.request.UserGeoRequest;
import com.weTravelTogether.repos.UserGeoRepository;
import com.weTravelTogether.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserGeoRepository userGeoRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    SecurityService securityService;

    public User registerUser(UserRegistration userRegistration) throws Exception{
        Optional<User> existingUser = userRepository.findByEmail(userRegistration.getEmail());
        if (existingUser.isPresent()) {
            throw new DuplicateEmailException();
        }

        User user = new User();
        user.setEmail(userRegistration.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        user.setUpdatedAt(new Date());
        user.setCreatedAt(new Date());

        UserProfile userProfile = new UserProfile();
        userProfile.setVisibleGeo(true);
        userProfile.setUpdatedAt(new Date());
        userProfile.setCreatedAt(new Date());
        userProfile.setUser(user);


        UserGeo userGeo = new UserGeo();
        userGeo.setUser(user);
        userGeo.setUpdatedAt(new Date());
        userGeo.setCreatedAt(new Date());

        user.setUserProfile(userProfile);
        user.setUserGeo(userGeo);
        userRepository.save(user);

        return user;

    }


    public MessageRequest updateUserGeo(UserGeoRequest userGeoRequest, HttpServletRequest httpServletRequest) {
        long userID = securityService.getLoggedUserId();
        Optional<User> userOptional = userRepository.findById(userID);
        User user = userOptional.get();
        UserGeo userGeo = new UserGeo();
        userGeo.setUser(user);
        userGeo.setCity(userGeoRequest.getCity());
        userGeo.setRegion(userGeoRequest.getRegion());
        userGeo.setLatitude(userGeoRequest.getLatitude());
        userGeo.setLongitude(userGeoRequest.getLongitude());
        userGeo.setUpdatedAt(new Date());
        userGeoRepository.save(userGeo);
        return new MessageRequest("update", HttpStatus.OK.value());
    }

    public MessageRequest updateUserProfile(UserProfileRequest profile, long id) {
        UserProfile user = userProfileRepository.findUserProfileByUserId(id);
        user.setName(profile.getName());
        user.setSurname(profile.getSurname());
        user.setCity(profile.getCity());
        user.setPatronymic(profile.getPatronymic());
        user.setAge(profile.getAge());
        user.setVisibleGeo(profile.isVisibleGeo());
        user.setUpdatedAt(new Date());
        userProfileRepository.save(user);
        return new MessageRequest("update", HttpStatus.OK.value());
    }



}
