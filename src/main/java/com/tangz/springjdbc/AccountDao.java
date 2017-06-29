package com.tangz.springjdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AccountDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void resetMoney() {
        jdbcTemplate.update("update springjdbc_account set balance=1000");
    }

    public List<Account> accountList() {
        return this.jdbcTemplate.query("select * from springjdbc_account", new RowMapper<Account>() {
            public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
                Account account = new Account();
                account.setUser(rs.getString("user"));
                account.setBalance(rs.getDouble("balance"));

                return account;
            }
        });
    }

    public void transferMoney(String source, String target, double count) {
        this.jdbcTemplate.update("update springjdbc_account set balance=balance-? where user=?", count, source);
        throwException();
        this.jdbcTemplate.update("update springjdbc_account set balance=balance+? where user=?", count, target);
    }

    private void throwException() {
        throw new RuntimeException("ERROR");
    }
}
