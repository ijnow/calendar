package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;

import java.util.List;
import java.util.Optional;

public class CalendarRepository {
    CalendarResponseDto saveCalendar(Calendar calendar);

    List<CalendarResponseDto> findAllCalendars();

    Optional<Calendar> findCalendarById(Long id);

    Calendar findCalendarByIdOrElseThrow(Long id);

    int updateCalendar(Long id, String title, String contents);

    int updateTitle(Long id, String title);

    int deleteCalendar(Long id);
}
