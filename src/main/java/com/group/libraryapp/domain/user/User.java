package com.group.libraryapp.domain.user;

// POST /user로 user 객체가 생성되면 여기에서 저장해줌
public class User {
    private String name;
    private Integer age;

    public User(String name, Integer age) {     // 객체가 생성될 때 포맷이 잘못되면 예외처리를 함
        if (name == null || name.isBlank()) {   // 이름이 null이거나 빈칸일 때
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
