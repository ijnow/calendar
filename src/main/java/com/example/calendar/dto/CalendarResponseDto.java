package com.example.calendar.dto;

import com.example.calendar.entity.Calendar;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponseDto {
    private Long id;
    private String todo;
    private String name;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.todo = calendar.getTodo();
        this.name = calendar.getName();
        this.password = calendar.getPassword();
        this.createdAt = calendar.getCreatedAt();
        this.updatedAt = calendar.getUpdatedAt();
    }
}
