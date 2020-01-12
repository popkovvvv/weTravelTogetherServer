package com.weTravelTogether.repos;

import com.weTravelTogether.models.entities.UserProfile;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile,Long> {
    UserProfile findUserProfileByUserId(Long id);
}
