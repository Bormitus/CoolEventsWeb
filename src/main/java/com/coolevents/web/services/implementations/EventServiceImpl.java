package com.coolevents.web.services.implementations;

import com.coolevents.web.dtos.EventDto;
import com.coolevents.web.mappers.EventMapper;
import com.coolevents.web.models.Event;
import com.coolevents.web.models.UserEntity;
import com.coolevents.web.repositories.EventRepository;
import com.coolevents.web.repositories.UserRepository;
import com.coolevents.web.security.SecurityUtil;
import com.coolevents.web.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    @Autowired
    public EventServiceImpl(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(event -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public Event createEvent(EventDto eventDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Event event = EventMapper.mapToEvent(eventDto);
        event.setCreatedBy(user);
        eventRepository.save(event);
        return event;
    }

    @Override
    public EventDto findEventById(UUID id) {
        Event resultEvent = eventRepository.findById(id).get();
        return EventMapper.mapToEventDto(resultEvent);
    }

    @Override
    public List<EventDto> findEventByUsername(String username) {
        List<Event> events = eventRepository.findByCreatedByUsernameContains(username);
        return events.stream().map(event -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Event event = EventMapper.mapToEvent(eventDto);
        event.setCreatedBy(user);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(UUID eventId) {
        eventRepository.deleteById(eventId);
    }

    @Override
    public List<EventDto> searchEvents(String search) {
        List<Event> events = eventRepository.searchEventsByQuery(search);
        return events.stream().map(event -> EventMapper.mapToEventDto(event)).collect(Collectors.toList());
    }
}
