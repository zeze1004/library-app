package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.response.UserResponse;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdataRequest;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(JdbcTemplate jdbcTemplate) {
        userRepository = new UserRepository(jdbcTemplate);
    }

    // controller가 HTTP 요청을 객체로 받기 때문에 @RequestBody 어노테이션이 필요
    public void saveUser(UserCreateRequest request) {
        userRepository.saveUser(request.getName(), request.getAge());
    }

    public List<UserResponse> getUsers() {
        return userRepository.getUsers();
    }

    public void updateUser(UserUpdataRequest request) {
        boolean isUserNotExist = userRepository.isUserNotExist(request.getId()); // request 전체 값(id, name)을 다 받을 수도 있지만 필요한 값만 전달하는게 권장됨
        if (isUserNotExist) {
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        if (userRepository.isUserNotExist(name)) {
            throw new IllegalArgumentException();
        }

        userRepository.deleteUser(name);
    }
}
