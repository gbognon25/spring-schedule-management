package com.sparta.springschedulemanagement.controller;

import com.sparta.springschedulemanagement.dto.ScheduleRequestDto;
import com.sparta.springschedulemanagement.dto.ScheduleResponseDto;
import com.sparta.springschedulemanagement.entity.Schedule;
import com.sparta.springschedulemanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    //CREATE
    // 일정 생성
    @PostMapping("/schedule")
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto responseDto = scheduleService.createSchedule(requestDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    //READ
    // 전체 일정 조회 (일정의 수정일과 작성자명)
    @GetMapping("/schedule")
    public ResponseEntity<List<ScheduleResponseDto>> getAllSchedules(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String updatedAt) {

        List<Schedule> schedules = scheduleService.getAllSchedules(author, updatedAt);

        //'List<Schedule>'는 'List<ScheduleDto>'로 변환
        List<ScheduleResponseDto> scheduleResponseDtos = schedules.stream()
                .map(ScheduleResponseDto::new)
                .toList();

        return new ResponseEntity<>(scheduleResponseDtos, HttpStatus.OK);
    }

    // 선택 일정 조회
    @GetMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleById(@PathVariable Long id) {
        ScheduleResponseDto schedule = scheduleService.getScheduleById(id);
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    //UPDATE
    // 선택 일정 수정
    @PutMapping("/schedule/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto requestDto,
            @RequestParam String password) {
        ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(id, requestDto, password);
        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    //DELETE
    // 선택 일정 삭제
    @DeleteMapping("/schedule/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id, @RequestParam String password) {
        scheduleService.deleteSchedule(id, password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
