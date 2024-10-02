package com.sparta.springschedulemanagement.service;

import com.sparta.springschedulemanagement.dto.ScheduleRequestDto;
import com.sparta.springschedulemanagement.dto.ScheduleResponseDto;
import com.sparta.springschedulemanagement.entity.Schedule;
import com.sparta.springschedulemanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    //일정 생성
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        //RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        //Database 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        //Entity -> ResponseDto
        //CheckLater
        return new ScheduleResponseDto(saveSchedule);
    }

    //전체 일정 조회 (일정의 수정일과 작성자명)
    public List<Schedule> getAllSchedules (String author, String updatedAt) {
        return scheduleRepository.findAll(author, updatedAt);
    }

    //선택 일정 조회(선택한 일정 정보 불러오기)
    public ScheduleResponseDto getScheduleById(Long id) {
        //일정 ID로 조회
        Optional <Schedule> optionalSchedule = scheduleRepository.findById(id);

        // 일정이 존재하지 않으면 예외 처리
        if(optionalSchedule.isEmpty()) {
            throw new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다.");
        }

        // 일정이 존재하면 해당 데이터를 ResponseDto로 변환하여 반환
        Schedule schedule = optionalSchedule.get();
        return new ScheduleResponseDto(schedule);
    }

    // 선택한 일정 수정
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto, String password) {
        // 일정 ID로 조회
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다.");
        }

        // 일정 존재 시 수정
        Schedule schedule = optionalSchedule.get();

        // 비밀번호 확인
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 할일과 작성자명만 수정
        schedule.setTitle(requestDto.getTitle());
        schedule.setAuthor(requestDto.getAuthor());

        // 수정일 갱신 (현재 시간)
        schedule.setUpdatedAt(java.time.LocalDateTime.now());

        // 저장
        Schedule updatedSchedule = scheduleRepository.save(schedule);

        // Entity -> ResponseDto
        return new ScheduleResponseDto(updatedSchedule);
    }

    //선택한 일정 삭제
    public void deleteSchedule(Long id, String password) {
        // 일정 ID로 조회
        Optional<Schedule> optionalSchedule = scheduleRepository.findById(id);

        if (optionalSchedule.isEmpty()) {
            throw new IllegalArgumentException("해당 ID의 일정이 존재하지 않습니다.");
        }

        // 일정 존재 시 삭제
        Schedule schedule = optionalSchedule.get();

        // 비밀번호 확인
        if (!schedule.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // 일정 삭제
        scheduleRepository.delete(schedule);
    }

}
