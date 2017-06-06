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

        JdbcTemplateDao dao = context.getBean("jdbcTemplateDao" , JdbcTemplateDao.class);

        List<User> userList = dao.getUserList();
        for (User user: userList) {
            System.out.println(user.getId() + ": " +user.getFirstName() + " " + user.getLastName());
        }

        ((ConfigurableApplicationContext) context).close();
    }
}
