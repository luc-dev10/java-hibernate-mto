package com.hibernate;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GetApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = factory.openSession();

        try {

            // get it by id 6
            session.beginTransaction();
            final int INSTRUCTOR_ID = 6;
            Instructor instructor = session.get(Instructor.class, INSTRUCTOR_ID);

            System.out.println(instructor.getFirstName());

            List<Course> courseList = instructor.getCourse();
            courseList.forEach(course -> System.out.println(course.getTitle()));

            // commit transaction
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            factory.close();
        }

    }
}
