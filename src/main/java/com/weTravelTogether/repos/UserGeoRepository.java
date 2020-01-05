package com.weTravelTogether.repos;

import com.weTravelTogether.models.UserGeo;
import org.springframework.data.repository.CrudRepository;

public interface UserGeoRepository extends CrudRepository<UserGeo, Long> {

    UserGeo getAccountGeoByUser_EmailOrderByIdDesc(String username);

}
