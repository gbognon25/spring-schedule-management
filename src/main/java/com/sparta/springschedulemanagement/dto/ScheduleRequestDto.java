package com.sparta.springschedulemanagement.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String title; //할알
    private String task; // 내용
    private String author; // 작성자명
    private String password; // 비밀번호
}
