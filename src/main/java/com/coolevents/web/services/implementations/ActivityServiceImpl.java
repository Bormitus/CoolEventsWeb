package com.coolevents.web.services.implementations;

import com.coolevents.web.dtos.ActivityDto;
import com.coolevents.web.dtos.EventDto;
import com.coolevents.web.mappers.ActivityMapper;
import com.coolevents.web.models.Activity;
import com.coolevents.web.models.Event;
import com.coolevents.web.repositories.ActivityRepository;
import com.coolevents.web.repositories.EventRepository;
import com.coolevents.web.services.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ActivityServiceImpl implements ActivityService {
    private EventRepository eventRepository;
    private ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(EventRepository eventRepository, ActivityRepository activityRepository) {
        this.eventRepository = eventRepository;
        this.activityRepository = activityRepository;
    }

    @Override
    public void createActivity(UUID eventId, ActivityDto activityDto) {
        Event event = eventRepository.findById(eventId).get();
        Activity activity = ActivityMapper.mapToActivity(activityDto);
        activity.setEvent(event);
        activityRepository.save(activity);
    }

    @Override
    public List<ActivityDto> findAllActivities() {
        List<Activity> activityList = activityRepository.findAll();
        return activityList.stream()
                .map(ActivityMapper::mapToActivityDto)
                .collect(Collectors.collectingAndThen(Collectors.toList(), list -> {
                    Collections.shuffle(list);
                    return list;
                }));
    }

    @Override
    public ActivityDto findById(UUID activityId) {
        Activity activity = activityRepository.findById(activityId).get();
        return ActivityMapper.mapToActivityDto(activity);

    }

    @Override
    public void updateActivity(ActivityDto activityDto) {
        Activity activity = ActivityMapper.mapToActivity(activityDto);
        activityRepository.save(activity);
    }

    @Override
    public void deleteActivity(UUID activityId) {
        activityRepository.deleteById(activityId);
    }
}
