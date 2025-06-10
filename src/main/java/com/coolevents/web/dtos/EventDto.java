package com.coolevents.web.dtos;

import com.coolevents.web.models.UserEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class EventDto {

    private UUID id;
    @NotBlank(message = "Title is required")
    @Size(max = 100, message = "Title must not exceed 100 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 1000, message = "Description must not exceed 1000 characters")
    private String description;

    @NotBlank(message = "Location is required")
    @Size(max = 200, message = "Location must not exceed 200 characters")
    private String location;

    @NotBlank(message = "Photo URL is required")
    @URL(message = "Photo URL must be a valid URL")
    private String photoUrl;

    @NotBlank(message = "Organizer website is required")
    @URL(message = "Organizer website must be a valid URL")
    private String orgSite;

    private UserEntity createdBy;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<ActivityDto> activities;
}
