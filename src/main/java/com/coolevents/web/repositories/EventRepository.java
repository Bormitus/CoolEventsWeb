package com.coolevents.web.repositories;

import com.coolevents.web.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByCreatedByUsernameContains(String username);
    @Query("SELECT e FROM Event e WHERE e.title LIKE CONCAT('%' , :query, '%')")
    List<Event> searchEventsByQuery(@Param("query") String query);
}
