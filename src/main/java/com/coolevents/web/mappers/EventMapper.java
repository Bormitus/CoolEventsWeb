package com.coolevents.web.mappers;

import com.coolevents.web.dtos.EventDto;
import com.coolevents.web.models.Event;

import java.util.stream.Collectors;

public class EventMapper {
    public static EventDto mapToEventDto(Event event)
    {
        EventDto eventDto = EventDto.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .location(event.getLocation())
                .photoUrl(event.getPhotoUrl())
                .orgSite(event.getOrgSite())
                .createdBy(event.getCreatedBy())
                .createdOn(event.getCreatedOn())
                .updatedOn(event.getUpdatedOn())
                .activities(event.getActivities().stream().map((activity) -> ActivityMapper.mapToActivityDto(activity)).collect(Collectors.toList()))
                .build();
        return eventDto;
    }

    public static Event mapToEvent(EventDto eventDto) {
        Event event = Event.builder()
                .id(eventDto.getId())
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .location(eventDto.getLocation())
                .photoUrl(eventDto.getPhotoUrl())
                .orgSite(eventDto.getOrgSite())
                .createdBy(eventDto.getCreatedBy())
                .createdOn(eventDto.getCreatedOn())
                .updatedOn(eventDto.getUpdatedOn())
                .build();
        return event;
    }
}
