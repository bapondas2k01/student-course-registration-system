/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseenrollment;

import java.time.LocalDate;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author zubay
 */
public class Enrollment {

    
    private SimpleIntegerProperty enrollment_id;
    private SimpleIntegerProperty student_id;
    private SimpleIntegerProperty course_id;
    private ObjectProperty<LocalDate> enroll_date;
    private SimpleStringProperty status;
    
    public Enrollment(int enrollment_id, int student_id, int course_id, LocalDate enroll_date, String status) {
        this.enrollment_id = new SimpleIntegerProperty(enrollment_id);
        this.student_id = new SimpleIntegerProperty(student_id);
        this.course_id = new SimpleIntegerProperty(course_id);
        this.enroll_date = new SimpleObjectProperty<>(enroll_date);
        this.status = new SimpleStringProperty(status);
    }

    public int getEnrollment_id() {
        return enrollment_id.get();
    }

    public int getStudent_id() {
        return student_id.get();
    }

    public int getCourse_id() {
        return course_id.get();
    }

    public LocalDate getEnroll_date() {
        return enroll_date.get();
    }

    public String getStatus() {
        return status.get();
    }

    public void setEnrollment_id(int enrollment_id) {
        this.enrollment_id.set(enrollment_id);
    }

    public void setStudent_id(int student_id) {
        this.student_id.set(student_id);
    }

    public void setCourse_id(int course_id) {
        this.course_id.set(course_id);
    }

    public void setEnroll_date(LocalDate enroll_date) {
        this.enroll_date.set(enroll_date);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }
}
