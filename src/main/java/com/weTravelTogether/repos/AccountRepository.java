package com.weTravelTogether.repos;

import com.weTravelTogether.models.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
}
