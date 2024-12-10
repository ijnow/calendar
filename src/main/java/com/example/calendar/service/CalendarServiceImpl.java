package com.example.calendar.service;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import com.example.calendar.repository.CalendarRepository;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class CalendarServiceImpl implements CalendarService {
    private final CalendarRepository calendarRepository;

    public CalendarServiceImpl(CalendarRepository calendarRepository) {
        this.calendarRepository = calendarRepository;
    }

    @Override
    public CalendarResponseDto saveCalendar(CalendarRequestDto requestDto) {

        // 객체 생성
        Calendar calendar = new Calendar(requestDto.getTitle(), requestDto.getContents());
        // 저장
        return calendarRepository.saveCalendar(calendar);
    }

    @Override
    public List<CalendarResponseDto> findAllCalendars() {

        return calendarRepository.findAllCalendars();
    }

    @Override
    public CalendarResponseDto findCalendarById(Long id) {

        Calendar calendar = calendarRepository.findCalendarByIdOrElseThrow(id);

        return new CalendarResponseDto(calendar);
    }


    @Override
    public void deleteCalendar(Long id) {

        int deletedRow = calendarRepository.deleteCalendar(id);


    }
}
