package com.weTravelTogether.repos;

import com.weTravelTogether.models.Event;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Event getEventByTitleOrderByIdDesc(String title);

    Event findById(long id);

    void removeEventById(long id);

}
