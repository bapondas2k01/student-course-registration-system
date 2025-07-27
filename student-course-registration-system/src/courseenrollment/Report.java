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
public class Report {
    
    private SimpleIntegerProperty report_id;
    private SimpleStringProperty report_title;
    private ObjectProperty<LocalDate> report_date;
    private SimpleStringProperty report_type;
    private SimpleStringProperty report_data;

    public Report(int report_id, String report_title, LocalDate report_date, String report_type, String report_data) {
        this.report_id = new SimpleIntegerProperty(report_id);
        this.report_title = new SimpleStringProperty(report_title);
        this.report_date = new SimpleObjectProperty<>(report_date);
        this.report_type = new SimpleStringProperty(report_type);
        this.report_data = new SimpleStringProperty(report_data);
    }

    public int getReport_id() {
        return report_id.get();
    }

    public String getReport_title() {
        return report_title.get();
    }

    public LocalDate getReport_date() {
        return report_date.get();
    }

    public String getReport_type() {
        return report_type.get();
    }

    public String getReport_data() {
        return report_data.get();
    }

    public void setReport_id(int report_id) {
        this.report_id.set(report_id);
    }

    public void setReport_title(String report_title) {
        this.report_title.set(report_title);
    }

    public void setReport_date(LocalDate date) {
        this.report_date.set(date);
    }

    public void setReport_type(String report_type) {
        this.report_type.set(report_type);
    }

    public void setReport_data(String report_data) {
        this.report_data.set(report_data);
    }
}
