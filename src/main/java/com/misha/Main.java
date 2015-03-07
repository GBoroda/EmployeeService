package com.misha;

import com.misha.dao.AppDao;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("app-servlet.xml");

        AppDao dao = context.getBean(AppDao.class);

        System.out.println(dao.getAllEmployees());
        
        
    }
}
