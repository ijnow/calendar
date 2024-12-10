package com.example.calendar.controller;

import com.example.calendar.dto.CalendarRequestDto;
import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.service.CalendarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendars")
public class CalendarController {
    private final CalendarService calendarService;

    private CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }


    @PostMapping
    public ResponseEntity<CalendarResponseDto> createCalendar(@RequestBody CalendarRequestDto requestDto) {

        return new ResponseEntity<>(calendarService.saveCalendar(requestDto), HttpStatus.CREATED);
    }


    @GetMapping
    public List<CalendarResponseDto> findAllCalendars() {

        return calendarService.findAllCalendars();
    }


    @GetMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> findCalendarById(@PathVariable Long id) {

        return new ResponseEntity<>(calendarService.findCalendarById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<CalendarResponseDto> updateCalendar(
            @PathVariable Long id,
            @RequestBody CalendarRequestDto requestDto
    ) {

        return new ResponseEntity<>(calendarService.updateCalendar(id, requestDto.getTitle(), requestDto.getContents()), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCalendar(@PathVariable Long id) {

        calendarService.deleteCalendar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
