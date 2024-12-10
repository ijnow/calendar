package com.example.calendar.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Setter

public class Calendar {

    private Long id; //고유식별자
    private String todo; //할 일
    private String name; //작성자명
    private String password; //비밀번호
    private LocalDateTime createdAt; //작성일
    private LocalDateTime updatedAt; //수정일

    public Calendar(String todo, String name, String password, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.todo = todo;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
