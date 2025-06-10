package com.coolevents.web.controllers;

import com.coolevents.web.dtos.EventDto;
import com.coolevents.web.models.Event;
import com.coolevents.web.models.UserEntity;
import com.coolevents.web.security.SecurityUtil;
import com.coolevents.web.services.EventService;
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
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @Autowired
    public EventController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    // 🔹 Просмотр событий

    @GetMapping("/event")
    public String getAllEvents(Model model) {
        List<EventDto> eventDtoList = eventService.findAllEvents();
        model.addAttribute("events", eventDtoList);
        return "event-list";
    }

    @GetMapping("/event/user/{username}")
    public String getAllUserEvents(@PathVariable("username") String username, Model model) {
        List<EventDto> eventDtoList = eventService.findEventByUsername(username);
        model.addAttribute("events", eventDtoList);
        return "event-user";
    }

    @GetMapping("/event/{eventId}")
    public String getEventById(@PathVariable("eventId") UUID eventId, Model model) {
        UserEntity user = new UserEntity();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
        }
        model.addAttribute("user", user);

        EventDto eventDto = eventService.findEventById(eventId);
        model.addAttribute("event", eventDto);
        return "event-detail";
    }

    @GetMapping("/event/search")
    public String searchEvent(@RequestParam("query") String query, Model model) {
        List<EventDto> eventDtoList = eventService.searchEvents(query);
        model.addAttribute("events", eventDtoList);
        return "event-list";
    }

    // 🔹 Создание события

    @GetMapping("/event/create")
    public String getCreateEventForm(Model model) {
        Event event = new Event();
        model.addAttribute("event", event);
        return "event-create";
    }

    @PostMapping("/event/create")
    public String createEvent(@Valid @ModelAttribute("event") EventDto eventDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "event-create";
        }
        eventService.createEvent(eventDto);
        return "redirect:/event";
    }

    // 🔹 Редактирование события

    @GetMapping("/event/{eventId}/edit")
    public String getEditEventForm(@PathVariable("eventId") UUID eventId, Model model) {
        EventDto eventDto = eventService.findEventById(eventId);
        if (eventDto == null || !SecurityUtil.isCurrentUserOwner(eventDto.getCreatedBy().getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied");
        }
        model.addAttribute("event", eventDto);
        return "event-edit";
    }

    @PostMapping("/event/{eventId}/edit")
    public String editEvent(@PathVariable("eventId") UUID eventId,
                            @Valid @ModelAttribute("event") EventDto eventDto,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "event-edit";
        }
        eventDto.setId(eventId);
        eventService.updateEvent(eventDto);
        return "redirect:/event";
    }

    // 🔹 Удаление события

    @PostMapping("/event/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") UUID eventId) {
        EventDto eventDto = eventService.findEventById(eventId);
        if (eventDto == null || !SecurityUtil.isCurrentUserOwner(eventDto.getCreatedBy().getUsername())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "access denied");
        }
        eventService.deleteEvent(eventId);
        return "redirect:/event";
    }
}
