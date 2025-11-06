package com.example.studentmgmt.config;

import com.example.studentmgmt.dao.StudentDAO;
import com.example.studentmgmt.service.FeeService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration as SpringConfig;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringConfig
@ComponentScan(basePackages = "com.example.studentmgmt")
@EnableTransactionManagement
public class AppConfig {

    @Bean
    public SessionFactory sessionFactory() {
        return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }

    @Bean
    public StudentDAO studentDAO() {
        return new StudentDAO(sessionFactory());
    }

    @Bean
    public FeeService feeService() {
        return new FeeService(studentDAO());
    }
}
