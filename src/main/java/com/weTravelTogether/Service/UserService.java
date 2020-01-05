package com.weTravelTogether.Service;

import com.weTravelTogether.Service.utils.UserUtil;
import com.weTravelTogether.models.User;
import com.weTravelTogether.models.UserGeo;
import com.weTravelTogether.pogos.MessageRequest;
import com.weTravelTogether.pogos.UserGeoRequest;
import com.weTravelTogether.pogos.UserProfile;
import com.weTravelTogether.repos.UserGeoRepository;
import com.weTravelTogether.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {

    @Autowired
    UserUtil userUtil;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserGeoRepository userGeoRepository;

    public MessageRequest updateUserGeo(UserGeoRequest userGeoRequest, HttpServletRequest httpServletRequest) {
        User user = userUtil.getUserObject(httpServletRequest);
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

        User user = userUtil.getUserObject(httpServletRequest);
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


    public UserProfile getUserProfile(HttpServletRequest httpServletRequest){
        UserProfile userProfile = new UserProfile();
        User user = userUtil.getUserObject(httpServletRequest);
        return userProfile.create(user);
    }
}
