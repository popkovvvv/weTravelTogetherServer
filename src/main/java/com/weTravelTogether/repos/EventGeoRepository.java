package com.weTravelTogether.repos;

import com.weTravelTogether.models.AccountGeo;
import com.weTravelTogether.models.EventGeo;
import org.springframework.data.repository.CrudRepository;

public interface EventGeoRepository extends CrudRepository<EventGeo, Long> {

    EventGeo getEventGeoByEvent_TitleOrderByIdDesc(String title);

}
