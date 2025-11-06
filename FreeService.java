package com.example.studentmgmt.service;

import com.example.studentmgmt.dao.StudentDAO;
import com.example.studentmgmt.model.Student;
import org.springframework.transaction.annotation.Transactional;

public class FeeService {
    private final StudentDAO studentDAO;

    public FeeService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Transactional
    public void payFee(int studentId, double amount) {
        Student s = studentDAO.get(studentId);
        if (s != null) {
            s.setBalance(s.getBalance() - amount);
            if (s.getBalance() < 0) throw new RuntimeException("Insufficient funds!");
            studentDAO.update(s);
            System.out.println("Payment successful for student " + s.getName());
        }
    }

    @Transactional
    public void refundFee(int studentId, double amount) {
        Student s = studentDAO.get(studentId);
        if (s != null) {
            s.setBalance(s.getBalance() + amount);
            studentDAO.update(s);
            System.out.println("Refund successful for student " + s.getName());
        }
    }
}
