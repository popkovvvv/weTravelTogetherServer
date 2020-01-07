package com.weTravelTogether.repos;

import com.weTravelTogether.models.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findById(long id);

    User findByEmailAndPassword(String email, String password);


}
