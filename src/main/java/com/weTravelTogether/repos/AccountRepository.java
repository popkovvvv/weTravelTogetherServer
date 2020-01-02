package com.weTravelTogether.repos;

import com.weTravelTogether.models.Account;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    Account findByEmail(String email);

    Account findById(long id);


}
