package com.example.calendar.repository;

import com.example.calendar.dto.CalendarResponseDto;
import com.example.calendar.entity.Calendar;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplateCalendarRepository implements CalendarRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateCalendarRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public CalendarResponseDto saveCalendar(Calendar calendar) {
        // INSERT Query를 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("calendar").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", calendar.getTitle());
        parameters.put("contents", calendar.getContents());

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return new CalendarResponseDto(key.longValue(), calendar.getTitle(), calendar.getContents());
    }

    @Override
    public List<CalendarResponseDto> findAllCalendars() {
        return jdbcTemplate.query("select * from calendar", calendarRowMapper());
    }

    @Override
    public Optional<Calendar> findCalendarById(Long id) {
        List<Calendar> result = jdbcTemplate.query("select * from calendar where id = ?", calendarRowMapperV2(), id);

        return result.stream().findAny();
    }

    @Override
    public Calendar findCalendarByIdOrElseThrow(Long id) {
        List<Calendar> result = jdbcTemplate.query("select * from calendar where id = ?", calendarRowMapperV2(), id);

        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    @Override
    public int updateCalendar(Long id, String title, String contents) {
        // 쿼리의 영향을 받은 row 수를 int로 반환한다.
        return jdbcTemplate.update("update Calendar set title = ?, contents = ? where id = ?", title, contents, id);
    }

    @Override
    public int updateTitle(Long id, String title) {
        return jdbcTemplate.update("update Calendar set title = ? where id = ?", title, id);
    }

    @Override
    public int deleteCalendar(Long id) {
        return jdbcTemplate.update("delete from Calendar where id = ?", id);
    }

    private RowMapper<CalendarResponseDto> calendarRowMapper() {
        return new RowMapper<CalendarResponseDto>() {
            @Override
            public CalendarResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new CalendarResponseDto(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents")
                );
            }

        };
    }

    private RowMapper<Calendar> calendarRowMapperV2() {
        return new RowMapper<Calendar>() {
            @Override
            public Calendar mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Calendar(
                        rs.getLong("id"),
                        rs.getString("title"),
                        rs.getString("contents")
                );
            }

        };
    }
}
