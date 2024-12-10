package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;

import java.util.List;

public class CalendarService {

    CalendarResponseDto saveCalendar(CalendarRequestDto requestDto);

    List<CalendarResponseDto> findAllCalendars();

    CalendarResponseDto findCalendarById(Long id);

    CalendarResponseDto updateCalendar(Long id, CalendarRequestDto requestDto);

    void deleteCalendar(Long id);
}
