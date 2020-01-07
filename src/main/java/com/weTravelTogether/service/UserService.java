package com.weTravelTogether.service;

import com.weTravelTogether.models.exception.DuplicateEmailException;
import com.weTravelTogether.models.request.UserRegistration;
import com.weTravelTogether.security.SecurityService;
import com.weTravelTogether.models.entities.User;
import com.weTravelTogether.models.entities.UserGeo;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.UserGeoRequest;
import com.weTravelTogether.pogos.UserProfile;
import com.weTravelTogether.repos.UserGeoRepository;
import com.weTravelTogether.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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
        user.setPatronymic(passwordEncoder.encode(userRegistration.getPassword()));
        userRepository.save(user);

        return user;

    }

    public UserProfile getUserProfileById(Long userId) {
        return userRepository.findById(userId)
                .map(user -> new UserProfile(
                        user.getId(),
                        user.getName(),
                        user.getSurname(),
                        user.getPatronymic(),
                        user.getEmail(),
                        user.getUsername(),
                        user.getCity(),
                        user.getAge(),
                        user.isVisibleGeo()))
                .orElseThrow(IllegalStateException::new);
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

        userGeoRepository.save(userGeo);

        return new MessageRequest("update", HttpStatus.OK.value());

    }

    public MessageRequest updateUserProfile(UserProfile profile, HttpServletRequest httpServletRequest) {

        long userID = securityService.getLoggedUserId();
        Optional<User> userOptional = userRepository.findById(userID);
        User user = userOptional.get();

        user.setName(profile.getName());
        user.setSurname(profile.getSurname());
        user.setUsername(profile.getUsername());
        user.setCity(profile.getCity());
        user.setPatronymic(profile.getPatronymic());
        user.setAge(profile.getAge());
        user.setVisibleGeo(profile.isVisibleGeo());
        userRepository.save(user);

        return new MessageRequest("update", HttpStatus.OK.value());
    }

    public MessageRequest updateUserProfileById(UserProfile profile, long id) {
        Optional<User> user = userRepository.findById(id);
        User userProfile = user.get();

        userProfile.setName(profile.getName());
        userProfile.setSurname(profile.getSurname());
        userProfile.setUsername(profile.getUsername());
        userProfile.setCity(profile.getCity());
        userProfile.setPatronymic(profile.getPatronymic());
        userProfile.setAge(profile.getAge());
        userProfile.setVisibleGeo(profile.isVisibleGeo());
        userRepository.save(userProfile);

        return new MessageRequest("update", HttpStatus.OK.value());
    }
}
