package com.sparta.springschedulemanagement.repository;

import com.sparta.springschedulemanagement.entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Schedule 저장 (KeyHolder 사용)
    public Schedule save(Schedule schedule) {
        String sql = "INSERT INTO schedule (title, task, author, password, createdAt, updatedAt) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, schedule.getTitle());
                ps.setString(2, schedule.getTask());
                ps.setString(3, schedule.getAuthor());
                ps.setString(4, schedule.getPassword());
                ps.setObject(5, schedule.getCreatedAt());
                ps.setObject(6, schedule.getUpdatedAt());
                return ps;
            }
        }, keyHolder);

        // 저장된 Schedule ID 세팅
        schedule.setId(keyHolder.getKey().longValue());

        return schedule;
    }

    // 전체 일정 조회 (작성자명과 수정일로 필터링)
    public List<Schedule> findAll(String author, String updatedAt) {
        String sql = "SELECT * FROM schedules WHERE 1=1";

        if (author != null) {
            sql += " AND author = ?";
        }
        if (updatedAt != null) {
            sql += " AND DATE(updated_at) = ?";
        }

        return jdbcTemplate.query(sql, new Object[]{author, updatedAt}, scheduleRowMapper());
    }

    // ID로 일정 조회
    public Optional<Schedule> findById(Long id) {
        String sql = "SELECT * FROM schedule WHERE id = ?";
        List<Schedule> schedules = jdbcTemplate.query(sql, new Object[]{id}, scheduleRowMapper());

        if (schedules.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(schedules.get(0));
    }

    // 일정 삭제
    public void delete(Schedule schedule) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        jdbcTemplate.update(sql, schedule.getId());
    }

    // RowMapper 설정
    private RowMapper<Schedule> scheduleRowMapper() {
        return (rs, rowNum) -> {
            Schedule schedule = new Schedule();
            schedule.setId(rs.getLong("id"));
            schedule.setTitle(rs.getString("title"));
            schedule.setTask(rs.getString("task"));
            schedule.setAuthor(rs.getString("author"));
            schedule.setPassword(rs.getString("password"));
            schedule.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
            schedule.setUpdatedAt(rs.getTimestamp("updated_at").toLocalDateTime());
            return schedule;
        };
    }

}
