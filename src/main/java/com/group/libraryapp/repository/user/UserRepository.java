package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(long id) {
        String readSql = "select * from user where id = ?"; // 유저 정보를 수정하기 전에 유저 정보가 있는지 확인함
        // 유저 정보가 있으면 [0](쿼리 리스트)이 생김
        // 없으면 빈 값
        // select * from user where id = request.getId()
        // select sql의 결과가 있으면 0으로 반환
        // 쿼리가 0을 list로 반환 => [0]
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public boolean isUserNotExist(String name) {
        String readSql = "select * from user where name = ?"; // 유저 정보를 수정하기 전에 유저 정보가 있는지 확인함
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
    }

    public void saveUser(String name, Integer age) {
        String sql = "insert into user (name, age) values (?, ?)"; // ?: 유동적인 데이터를 맵핑해줌
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, (rs, rowNum) -> { // RowMapper는 쿼리의 결과를 받아 객체를 반환함
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id, name, age);
        });
    }

    public void updateUserName(String name, long id) {
        String sql = "update user set name = ? where id = ?";   // update [테이블] set [열] = [변경할 값] where [조건]
        jdbcTemplate.update(sql, name, id);
    }

    public void deleteUser(String name) {
        String sql = "delete from user where name = ?"; // localhost:8080/user?name=sojung
        jdbcTemplate.update(sql, name);
    }
}
