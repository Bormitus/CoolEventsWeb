package com.coolevents.web.services;

import com.coolevents.web.dtos.ActivityDto;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface ActivityService {
    void createActivity(UUID eventId, ActivityDto activityDto);
    List<ActivityDto> findAllActivities();
    ActivityDto findById(UUID activityId);
    void updateActivity(ActivityDto activityDto);
    void deleteActivity(UUID activityId);
}
