package com.coolevents.web.controllers;

import com.coolevents.web.dtos.ActivityDto;
import com.coolevents.web.models.Activity;
import com.coolevents.web.models.UserEntity;
import com.coolevents.web.security.SecurityUtil;
import com.coolevents.web.services.ActivityService;
import com.coolevents.web.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Controller
public class ActivityController {

    private final ActivityService activityService;
    private final UserService userService;

    @Autowired
    public ActivityController(ActivityService activityService, UserService userService) {
        this.activityService = activityService;
        this.userService = userService;
    }

    // 🔹 Просмотр всех активностей

    @GetMapping("/activity")
    public String getAllActivities(Model model) {
        List<ActivityDto> activityDtoList = activityService.findAllActivities();
        model.addAttribute("activities", activityDtoList);
        return "activity-list";
    }

    // 🔹 Просмотр активности по ID

    @GetMapping("/activity/{activityId}")
    public String getActivityById(@PathVariable("activityId") UUID activityId, Model model) {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);

        ActivityDto activityDto = activityService.findById(activityId);
        model.addAttribute("activity", activityDto);
        return "activity-detail";
    }

    // 🔹 Создание активности

    @GetMapping("/activity/{eventId}/create")
    public String getCreateActivityForm(@PathVariable("eventId") UUID eventId, Model model) {
        Activity activity = new Activity();
        model.addAttribute("eventId", eventId);
        model.addAttribute("activity", activity);
        return "activity-create";
    }

    @PostMapping("/activity/{eventId}/create")
    public String createActivity(@PathVariable("eventId") UUID eventId,
                                 @Valid @ModelAttribute("activity") ActivityDto activityDto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("activity", activityDto);
            return "activity-create";
        }
        activityService.createActivity(eventId, activityDto);
        return "redirect:/event/" + eventId;
    }

    // 🔹 Редактирование активности

    @GetMapping("/activity/{activityId}/edit")
    public String getActivityEditForm(@PathVariable("activityId") UUID activityId, Model model) {
        ActivityDto activityDto = activityService.findById(activityId);
        if (activityDto == null ||
                !SecurityUtil.isCurrentUserOwner(activityDto.getEvent().getCreatedBy().getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied");
        }
        model.addAttribute("activity", activityDto);
        return "activity-edit";
    }

    @PostMapping("/activity/{activityId}/edit")
    public String updateActivity(@PathVariable("activityId") UUID activityId,
                                 @Valid @ModelAttribute("activity") ActivityDto activityDto,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("activity", activityDto);
            return "activity-edit";
        }
        ActivityDto existingActivity = activityService.findById(activityId);
        activityDto.setId(activityId);
        activityDto.setEvent(existingActivity.getEvent());
        activityService.updateActivity(activityDto);
        return "redirect:/activity";
    }

    // 🔹 Удаление активности

    @PostMapping("/activity/{activityId}/delete")
    public String deleteActivity(@PathVariable("activityId") UUID activityId) {
        ActivityDto activityDto = activityService.findById(activityId);
        if (activityDto == null ||
                !SecurityUtil.isCurrentUserOwner(activityDto.getEvent().getCreatedBy().getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied");
        }
        activityService.deleteActivity(activityId);
        return "redirect:/activity";
    }
}
