/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseenrollment;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author zubay
 */
public class ManageStudent {
    public SimpleIntegerProperty id;
    public SimpleStringProperty name;
    public SimpleStringProperty dept;
    public SimpleStringProperty phone;
    public SimpleStringProperty email;
    public SimpleStringProperty password;

    public ManageStudent(int id, String name, String dept, String phone, String email, String password) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dept = new SimpleStringProperty(dept);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.password = new SimpleStringProperty(password);
    }
    
    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setDept(String dept) {
        this.dept.set(dept);
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDept() {
        return dept.get();
    }

    public String getPhone() {
        return phone.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getPassword() {
        return password.get();
    }
}
