/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseenrollment;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author zubay
 */
public class ManageCourse {
    
    private SimpleIntegerProperty course_id;
    private SimpleStringProperty course_name;
    private SimpleStringProperty instructor;
    public SimpleStringProperty course_dept;
    private SimpleFloatProperty credit;
    
    public ManageCourse(int course_id, String course_name, String instructor, String course_dept, Float credit) {
        this.course_id = new SimpleIntegerProperty(course_id);
        this.course_name = new SimpleStringProperty(course_name);
        this.instructor = new SimpleStringProperty(instructor);
        this.course_dept = new SimpleStringProperty(course_dept);
        this.credit = new SimpleFloatProperty(credit);
    }

    public void setCourse_id(int course_id) {
        this.course_id.set(course_id);
    }
    
    public void setCourse_name(String course_name) {
        this.course_name.set(course_name);
    }

    public void setInstructor(String instructor) {
        this.instructor.set(instructor);
    }

    public void setCourse_dept(String course_dept) {
        this.course_dept.set(course_dept);
    }

    public void setCredit(Float credit) {
        this.credit.set(credit);
    }
    
    public int getCourse_id() {
        return course_id.get();
    }
    
    public String getCourse_name() {
        return course_name.get();
    }

    public String getInstructor() {
        return instructor.get();
    }

    public String getCourse_dept() {
        return course_dept.get();
    }

    public Float getCredit() {
        return credit.get();
    }

}
