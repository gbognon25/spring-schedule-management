package com.sparta.springschedulemanagement.entity;

import com.sparta.springschedulemanagement.dto.ScheduleRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor // Generates a no-argument constructor
public class Schedule {

    private Long id; // 고유 식별자
    private String title; // 할일
    private String task; // 내용
    private String author; // 작성자명
    private String password; // 비밀번호
    private LocalDateTime createdAt; // 작성일
    private LocalDateTime updatedAt; // 수정일

    public Schedule(ScheduleRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.task = requestDto.getTask();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
