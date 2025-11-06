package com.example.studentmgmt.main;

import com.example.studentmgmt.config.AppConfig;
import com.example.studentmgmt.dao.StudentDAO;
import com.example.studentmgmt.model.Course;
import com.example.studentmgmt.model.Student;
import com.example.studentmgmt.service.FeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        StudentDAO dao = context.getBean(StudentDAO.class);
        FeeService feeService = context.getBean(FeeService.class);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Student Management Menu ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Pay Fee");
            System.out.println("6. Refund Fee");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = sc.nextLine();
                    Course c = new Course("Spring Boot", "3 Months");
                    Student s = new Student(name, c, 5000);
                    dao.save(s);
                    System.out.println("Student added!");
                    break;
                case 2:
                    List<Student> list = dao.list();
                    list.forEach(st -> System.out.println(st.getStudentId() + " | " + st.getName() + " | " + st.getBalance()));
                    break;
                case 3:
                    System.out.print("Enter student ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    Student update = dao.get(id);
                    if (update != null) {
                        System.out.print("Enter new name: ");
                        update.setName(sc.nextLine());
                        dao.update(update);
                        System.out.println("Updated successfully!");
                    }
                    break;
                case 4:
                    System.out.print("Enter ID to delete: ");
                    dao.delete(sc.nextInt());
                    System.out.println("Deleted!");
                    break;
                case 5:
                    System.out.print("Enter student ID: ");
                    id = sc.nextInt();
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    feeService.payFee(id, amt);
                    break;
                case 6:
                    System.out.print("Enter student ID: ");
                    id = sc.nextInt();
                    System.out.print("Enter refund amount: ");
                    amt = sc.nextDouble();
                    feeService.refundFee(id, amt);
                    break;
                case 0:
                    context.close();
                    System.exit(0);
            }
        }
    }
}
