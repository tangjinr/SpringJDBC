package com.tangz.springjdbc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Author: tangzhen
 * Package: com.tangz.springjdbc
 * Name: TestSpringJDBC
 * Date: 2017/6/5
 * Time: 22:00
 */

public class TestSpringJDBC {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        AccountDao dao = context.getBean("accountDao", AccountDao.class);

        dao.resetMoney();

        try {
            dao.transferMoney("LiLei", "Hanmeimei", 521);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        List<Account> accountList = dao.accountList();
        for (Account account : accountList) {
            System.out.println(account.getUser() + ": " + account.getBalance());
        }

        ((ConfigurableApplicationContext) context).close();
    }
}
