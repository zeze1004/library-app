package com.group.libraryapp.dto.response;

import com.group.libraryapp.domain.user.User;

public class UserResponse {
    private long id;
    private String name;
    private Integer age;

    public UserResponse(long id, User user) {    // User user를 인자로 받으면 생성자가 자동으로 String name, Integer age 맵핑
        this.id = id;
        this.name = getName();
        this.age = getAge();
    }

    public UserResponse(long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
