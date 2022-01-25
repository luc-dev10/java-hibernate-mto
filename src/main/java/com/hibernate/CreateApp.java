package com.hibernate;

import com.hibernate.entity.Course;
import com.hibernate.entity.Instructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateApp {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // Session
        Session session = factory.openSession();

        try {

            // create the instructor
            Instructor instructor = new Instructor();
            instructor.setEmail("lucio.zhao@gmail.com");
            instructor.setFirstName("lucio");
            instructor.setLastName("zhao");

            // create the course
            Course course1 = new Course();
            course1.setTitle("Cloud Watching");
            Course course2 = new Course();
            course2.setTitle("Stair mopping");

            // set the relationship
            instructor.addCourse(course1);
            instructor.addCourse(course2);

            // save record
            session.save(instructor);
            session.save(course1);
            session.save(course2);

            // begin transaction
            session.beginTransaction();

            // commit
            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            factory.close();
            session.close();
        }

    }
}
