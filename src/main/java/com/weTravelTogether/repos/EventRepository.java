package com.weTravelTogether.repos;

import com.weTravelTogether.models.Account;
import com.weTravelTogether.models.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Event getEventByTitleOrderByIdDesc(String title);


}
