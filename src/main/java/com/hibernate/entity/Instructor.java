package com.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {
    // fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "instructor", cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST})
    private List<Course> courseList;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Course> getCourse() {
        return courseList;
    }

    public void setCourse(List<Course> courseList) {
        this.courseList = courseList;

    }

    // mapping method between the two entities
    public void addCourse(Course course) {
        if (this.courseList == null)
            this.courseList = new ArrayList<>();

        if (!this.courseList.contains(course)) {
            course.setInstructor(this);
            this.courseList.add(course);
        }
    }

}
