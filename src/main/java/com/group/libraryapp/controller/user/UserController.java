package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.response.UserResponse;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdataRequest;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

//    private final List<User> users = new ArrayList<>(); // 메모리에 저장하는걸 DB에 저장하게끔 바꿈
    private final JdbcTemplate jdbcTemplate;
    private final UserService userService = new UserService();

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @PostMapping("/user")   // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
//        users.add(new User(request.getName(), request.getAge()));
        String sql = "insert into user (name, age) values (?, ?)"; // ?: 유동적인 데이터를 맵핑해줌
        jdbcTemplate.update(sql, request.getName(), request.getAge());
    }

    @GetMapping("/user")
    public List<UserResponse> getUser() {
//        List<UserResponse> responses = new ArrayList<>();
//        for (int i = 0; i < users.size(); i++) {
//            responses.add(new UserResponse(i + 1, users.get(i)));
//        }
//        return responses;
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() { // RowMapper는 쿼리의 결과를 받아 객체를 반환함
            @Override                                                   // DB에서 받아온 정보를 UserResponse로 바꿔서 리턴
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdataRequest request) {
        userService.updateUser(jdbcTemplate, request);

    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        String readSql = "select * from user where name = ?"; // 유저 정보를 수정하기 전에 유저 정보가 있는지 확인함
        boolean isUserNotExist = jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();

        String sql = "delete from user where name = ?"; // localhost:8080/user?name=sojung
        jdbcTemplate.update(sql, name);
    }
}
