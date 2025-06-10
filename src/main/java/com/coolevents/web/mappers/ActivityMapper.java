package com.coolevents.web.mappers;

import com.coolevents.web.dtos.ActivityDto;
import com.coolevents.web.models.Activity;

public class ActivityMapper {
    public static ActivityDto mapToActivityDto(Activity activity)
    {
        ActivityDto activityDto = ActivityDto.builder()
                .id(activity.getId())
                .name(activity.getName())
                .speaker(activity.getSpeaker())
                .startTime(activity.getStartTime())
                .endTime(activity.getEndTime())
                .createdOn(activity.getCreatedOn())
                .updatedOn(activity.getUpdatedOn())
                .event(activity.getEvent())
                .build();
        return activityDto;
    }

    public static Activity mapToActivity(ActivityDto activityDto) {
        Activity activity = Activity.builder()
                .id(activityDto.getId())
                .name(activityDto.getName())
                .speaker(activityDto.getSpeaker())
                .startTime(activityDto.getStartTime())
                .endTime(activityDto.getEndTime())
                .createdOn(activityDto.getCreatedOn())
                .updatedOn(activityDto.getUpdatedOn())
                .event(activityDto.getEvent())
                .build();
        return  activity;
    }
}
