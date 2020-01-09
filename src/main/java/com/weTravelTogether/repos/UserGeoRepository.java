package com.weTravelTogether.repos;

import com.weTravelTogether.models.entities.UserGeo;
import org.springframework.data.repository.CrudRepository;

public interface UserGeoRepository extends CrudRepository<UserGeo, Long> {

    UserGeo findUserGeoByUserId(long id);

}
