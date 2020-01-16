package com.weTravelTogether.repos;

import com.weTravelTogether.models.entities.UserRefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRefreshTokenRepository extends CrudRepository<UserRefreshToken, Long> {

    Optional<UserRefreshToken> findByToken(String token);

}
