package com.coolevents.web.dtos;

import com.coolevents.web.models.Event;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    private UUID id;

    @NotBlank(message = "Activity name must not be blank")
    @Size(max = 100, message = "Activity name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "Speaker name must not be blank")
    @Size(max = 100, message = "Speaker name must not exceed 100 characters")
    private String speaker;

    @NotNull(message = "Start time is required")
    @FutureOrPresent(message = "Start time cannot be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-d HH:mm")
    private LocalDateTime startTime;

    @NotNull(message = "End time is required")
    @Future(message = "End time must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-d HH:mm")
    private LocalDateTime endTime;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    private Event event;
}
