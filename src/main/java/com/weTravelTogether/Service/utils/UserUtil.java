package com.weTravelTogether.Service.utils;

import com.weTravelTogether.models.User;
import com.weTravelTogether.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Component
public class UserUtil {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User getUserObject(HttpServletRequest httpServletRequest){

        UserDetails userDetails = (UserDetails) jwtTokenUtil.getUserDetailsByToken(httpServletRequest);
        Optional<User> accountOptional =  userRepository.findByEmail(userDetails.getUsername());

        return accountOptional.get();
    }
}
