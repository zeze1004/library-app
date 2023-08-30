package com.group.libraryapp.repository.user;

import org.springframework.jdbc.core.JdbcTemplate;

public class UserRepository {

    public boolean isUserNotExist(JdbcTemplate jdbcTemplate, long id) {
        String readSql = "select * from user where id = ?"; // 유저 정보를 수정하기 전에 유저 정보가 있는지 확인함
        // 유저 정보가 있으면 [0](쿼리 리스트)이 생김
        // 없으면 빈 값
        // select * from user where id = request.getId()
        // select sql의 결과가 있으면 0으로 반환
        // 쿼리가 0을 list로 반환 => [0]
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    // 유저 이름 변경
    public void updateUserName(JdbcTemplate jdbcTemplate, String name, long id) {
        String sql = "update user set name = ? where id = ?";   // update [테이블] set [열] = [변경할 값] where [조건]
        jdbcTemplate.update(sql, name, id);
    }
}
