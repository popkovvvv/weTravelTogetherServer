package com.weTravelTogether.repos;

import com.weTravelTogether.models.AccountGeo;
import org.springframework.data.repository.CrudRepository;

public interface AccountGeoRepository extends CrudRepository<AccountGeo, Long> {

    AccountGeo getAccountGeoByAccount_EmailOrderByIdDesc(String username);
}
