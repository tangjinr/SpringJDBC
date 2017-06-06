package com.tangz.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Author: tangzhen
 * Package: com.tangz.springjdbc
 * Name: JdbcTemplateDao
 * Date: 2017/6/5
 * Time: 21:57
 */
@Repository
public class JdbcTemplateDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void createTable() {
        jdbcTemplate.execute("create table springjdbc_user (id integer, first_name varchar(100), last_name varchar(100))");
    }

    public void insertData() {
        this.jdbcTemplate.update("insert into springjdbc_user values (1, ?, ?)", "Meimie", "Han");
        this.jdbcTemplate.update("insert into springjdbc_user values (2, ?, ?)", "Lei", "Li");
    }

    public int count() {
        return this.jdbcTemplate.queryForObject("select count(*) from springjdbc_user", Integer.class);
    }

    public List<User> getUserList() {
        return this.jdbcTemplate.query("select * from springjdbc_user", new RowMapper<User>() {
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));

                return user;
            }
        });
    }
}
