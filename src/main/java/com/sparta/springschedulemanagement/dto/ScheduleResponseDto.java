package com.sparta.springschedulemanagement.dto;

import com.sparta.springschedulemanagement.entity.Schedule;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ScheduleResponseDto {
    private Long id; // 고유 식별자
    private String title; // 할일
    private String task; // 내용
    private String author; // 작성자명
    private String createdAt; // 작성일
    private String updatedAt; // 수정일

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.task = schedule.getTask();
        this.author = schedule.getAuthor();
        this.createdAt = schedule.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.updatedAt = schedule.getUpdatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public ScheduleResponseDto (Long id, String title, String task, String author, String createdAt, String updatedAt) {
        this.id = id;
        this.title = title;
        this.task = task;
        this.author = author;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
