package com.coolevents.web.services;

import com.coolevents.web.dtos.EventDto;
import com.coolevents.web.models.Event;

import java.util.List;
import java.util.UUID;

public interface EventService {
    List<EventDto> findAllEvents();
    Event createEvent(EventDto eventDto);
    EventDto findEventById(UUID id);
    List<EventDto> findEventByUsername(String username);
    void updateEvent(EventDto eventDto);
    void deleteEvent(UUID eventId);
    List<EventDto> searchEvents(String search);
}
