/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseenrollment;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zubay
 */
public class AdminDashboardController implements Initializable {
    @FXML
    private ComboBox<String> tableCombo;
    @FXML
    private Button LogoutBtn;
    
    // === Student Fields ===
    @FXML
    private VBox ManageStudentVbox;
    @FXML
    private Label studentLabel;
    @FXML
    private TableView<ManageStudent> StudentTable;
    @FXML
    private TableColumn<ManageStudent, Integer> colID;
    @FXML
    private TableColumn<ManageStudent, String> colName;
    @FXML
    private TableColumn<ManageStudent, String> colDept;
    @FXML
    private TableColumn<ManageStudent, String> colEmail;
    @FXML
    private TableColumn<ManageStudent, String> colPhone;
    @FXML
    private TableColumn<ManageStudent, String> colPass;
    @FXML
    private Button ManageStdBtn;
    @FXML
    private VBox tfStudentVbox;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDept;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPhone;
    @FXML
    private TextField tfPass;
    @FXML
    private Button studentAddBtn;
    @FXML
    private Button studentUpdateBtn;
    @FXML
    private Button studentDeleteBtn;
    
    // === Course Fields ===
    @FXML
    private VBox ManageCourseVbox;
    @FXML
    private Label courseLabel;
    @FXML
    private TableView<ManageCourse> CourseTable;
    @FXML
    private TableColumn<ManageCourse, Integer> colCID;
    @FXML
    private TableColumn<ManageCourse, String> colCname;
    @FXML
    private TableColumn<ManageCourse, String> colCinstructor;
    @FXML
    private TableColumn<ManageCourse, String> colCdept;
    @FXML
    private TableColumn<ManageCourse, Float> colCcredit;
    @FXML
    private Button ManageCourseBtn;
    @FXML
    private VBox tfCourseVbox;
    @FXML
    private TextField tfCID;
    @FXML
    private TextField tfcrsName;
    @FXML
    private TextField tfcrsInstructor;
    @FXML
    private TextField tfcrsDept;
    @FXML
    private TextField tfcrsCredit;
    @FXML
    private Button courseAddBtn;
    @FXML
    private Button courseUpdateBtn;
    @FXML
    private Button courseDeleteBtn;
    
    // === Enrollment Fields ===
    @FXML
    private VBox EnrollVbox;
    @FXML
    private Label ViewLabel;
    @FXML
    private TableView<Enrollment> EnrollmentTable;
    @FXML
    private TableColumn<Enrollment, Integer> colenID;
    @FXML
    private TableColumn<Enrollment, Integer> colstdID;
    @FXML
    private TableColumn<Enrollment, Integer> colcrsID;
    @FXML
    private TableColumn<Enrollment, String> colenDate;
    @FXML
    private TableColumn<Enrollment, String> colStatus;
    @FXML
    private Button ApproveRegBtn;
    @FXML
    private VBox tfEnrollVbox;
    @FXML
    private TextField tfenID;
    @FXML
    private TextField tfstdID;
    @FXML
    private TextField tfcrsID;
    @FXML
    private DatePicker dpenDate;
    @FXML
    private ComboBox<String> comboStatus;
    @FXML
    private Button enrollAddBtn;
    @FXML
    private Button enrollUpdateBtn;
    @FXML
    private Button enrollDeleteBtn;
    
    // === Report Fields ===
    @FXML
    private VBox ReportVbox;
    @FXML
    private Label reportLabel;
    @FXML
    private TableView<Report> ReportTable;
    @FXML
    private TableColumn<Report, Integer> colrepID;
    @FXML
    private TableColumn<Report, String> colrepTitle;
    @FXML
    private TableColumn<Report, LocalDate> colrepDate;
    @FXML
    private TableColumn<Report, String> colrepType;
    @FXML
    private TableColumn<Report, String> colrepData;
    @FXML
    private Button ReportBtn;
    @FXML
    private VBox tfReportVbox;
    @FXML
    private TextField tfrepID;
    @FXML
    private TextField tfrepTitle;
    @FXML
    private DatePicker dprepDate;
    @FXML
    private ComboBox<String> comborepType;
    @FXML
    private TextArea tarepData;
    @FXML
    private Button repAddBtn;
    @FXML
    private Button repUpdateBtn;
    @FXML
    private Button repDeleteBtn;
    


    private ObservableList<ManageStudent> studentList = FXCollections.observableArrayList();
    private ObservableList<ManageCourse> courseList = FXCollections.observableArrayList();
    private ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();
    private ObservableList<Report> reportList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboStatus.getItems().addAll("Enrolled", "Completed", "Dropped");
        comborepType.getItems().addAll("Enrollment Summary", "Course Completion", "Student Activity");
        tableCombo.getItems().addAll("Manage Students", "Manage Courses", "View Enrollments", "Generate Reports");

        tableCombo.setOnAction(event -> {
            String selected = tableCombo.getValue();
            boolean isManageStudents = "Manage Students".equalsIgnoreCase(selected);
            tfStudentVbox.setVisible(isManageStudents);
            tfStudentVbox.setManaged(isManageStudents);
            
            boolean isManageCourses = "Manage Courses".equalsIgnoreCase(selected);
            tfCourseVbox.setVisible(isManageCourses);
            tfCourseVbox.setManaged(isManageCourses);
            
            boolean isViewEnrollments = "View Enrollments".equalsIgnoreCase(selected);
            tfEnrollVbox.setVisible(isViewEnrollments);
            tfEnrollVbox.setManaged(isViewEnrollments);
            
            boolean isGenerateReports = "Generate Reports".equalsIgnoreCase(selected);
            tfReportVbox.setVisible(isGenerateReports);
            tfReportVbox.setManaged(isGenerateReports);
        });
        showOnly(null);
        
        setupStudentColumns();
        setupCourseColumns();
        setupEnrollmentColumns();
        setupReportColumns();
        
        loadStudentData();
        loadCourseData();
        loadEnrollmentData();
        loadReportData();
        
//        StudentTable.setItems(studentList);
//        CourseTable.setItems(courseList);
//        EnrollmentTable.setItems(enrollmentList);
//        ReportTable.setItems(reportList);
        
        StudentTable.setOnMouseClicked(e -> {
            ManageStudent selected = StudentTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                tfID.setText(String.valueOf(selected.getId()));
                tfName.setText(selected.getName());
                tfDept.setText(selected.getDept());
                tfPhone.setText(selected.getPhone());
                tfEmail.setText(selected.getEmail());
                tfPass.setText(selected.getPassword());
            }
        });
        
        CourseTable.setOnMouseClicked(e -> {
            ManageCourse selected = CourseTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                tfcrsName.setText(selected.getCourse_name());
                tfcrsInstructor.setText(selected.getInstructor());
                tfcrsDept.setText(selected.getCourse_dept());
                tfcrsCredit.setText(String.valueOf(selected.getCredit()));
                tfCID.setText(String.valueOf(selected.getCourse_id()));
            }
        });
        
        EnrollmentTable.setOnMouseClicked(e -> {
            Enrollment selected = EnrollmentTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                tfenID.setText(String.valueOf(selected.getEnrollment_id()));
                tfstdID.setText(String.valueOf(selected.getStudent_id()));
                tfcrsID.setText(String.valueOf(selected.getCourse_id()));
                dpenDate.setValue(selected.getEnroll_date());
                comboStatus.setValue(selected.getStatus());
            }
        });
        
        ReportTable.setOnMouseClicked(e -> {
            Report selected = ReportTable.getSelectionModel().getSelectedItem();
            if (selected != null) {
                tfrepID.setText(String.valueOf(selected.getReport_id()));
                tfrepTitle.setText(selected.getReport_title());
                dprepDate.setValue(selected.getReport_date());
                comborepType.setValue(selected.getReport_type());
                tarepData.setText(selected.getReport_data());
            }
        });
    }    
    
    private void showOnly(VBox toShow) {
        // Hide all
        ManageStudentVbox.setVisible(false);
        ManageStudentVbox.setManaged(false);

        ManageCourseVbox.setVisible(false);
        ManageCourseVbox.setManaged(false);

        EnrollVbox.setVisible(false);
        EnrollVbox.setManaged(false);
        
        ReportVbox.setVisible(false);
        ReportVbox.setManaged(false);

        // Show selected
        if (toShow != null) {
            toShow.setVisible(true);
            toShow.setManaged(true);
        }
    }
    
    private void setupStudentColumns() {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        colPass.setCellValueFactory(new PropertyValueFactory<>("password"));
    }

    private void setupCourseColumns() {
        colCID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colCname.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        colCinstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        colCdept.setCellValueFactory(new PropertyValueFactory<>("course_dept"));
        colCcredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
    }

    private void setupEnrollmentColumns() {
        colenID.setCellValueFactory(new PropertyValueFactory<>("enrollment_id"));
        colstdID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colcrsID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colenDate.setCellValueFactory(new PropertyValueFactory<>("enroll_date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    private void setupReportColumns() {
        colrepID.setCellValueFactory(new PropertyValueFactory<>("report_id"));
        colrepTitle.setCellValueFactory(new PropertyValueFactory<>("report_title"));
        colrepDate.setCellValueFactory(new PropertyValueFactory<>("report_date"));
        colrepType.setCellValueFactory(new PropertyValueFactory<>("report_type"));
        colrepData.setCellValueFactory(new PropertyValueFactory<>("report_data"));
    }
    
    private void loadStudentData() {
        studentList.clear();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM students");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                studentList.add(new ManageStudent(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("dept"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
            }
            StudentTable.setItems(studentList);

        } catch (Exception e) {
            System.out.println("Load Error: " + e.getMessage());
        }
    }

    private void loadCourseData() {
        courseList.clear();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM courses");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                courseList.add(new ManageCourse(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getString("instructor"),
                        rs.getString("course_dept"),
                        rs.getFloat("credit")
                ));
            }
            CourseTable.setItems(courseList);

        } catch (Exception e) {
            System.out.println("Load Error: " + e.getMessage());
        }
    }

    private void loadEnrollmentData() {
        enrollmentList.clear();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM enrollments");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                enrollmentList.add(new Enrollment(
                        rs.getInt("enrollment_id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getDate("enroll_date").toLocalDate(),
                        rs.getString("status")
                ));
            }
            EnrollmentTable.setItems(enrollmentList);

        } catch (Exception e) {
            System.out.println("Load Error: " + e.getMessage());
        }
    }

    private void loadReportData() {
        reportList.clear();
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reports");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                reportList.add(new Report(
                        rs.getInt("report_id"),
                        rs.getString("report_title"),
                        rs.getDate("report_date").toLocalDate(),
                        rs.getString("report_type"),
                        rs.getString("report_data")
                ));
            }
            ReportTable.setItems(reportList);

        } catch (Exception e) {
            System.out.println("Load Error: " + e.getMessage());
        }
    }
    
    @FXML
    private void studentAdd(ActionEvent event) {
        try {
            String id = tfID.getText().trim();
            String name = tfName.getText().trim();
            String dept = tfDept.getText().trim();
            String phone = tfPhone.getText().trim();
            String email = tfEmail.getText().trim();
            String password = tfPass.getText().trim();

            if (id == null || name == null || dept == null || phone == null || email == null || password == null) {
                System.out.println("Please fill all fields.");
                return;
            }

            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "INSERT INTO students (id, name, dept, phone, email, password) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, id);
                stmt.setString(2, name);
                stmt.setString(3, dept);
                stmt.setString(4, phone);
                stmt.setString(5, email);
                stmt.setString(6, password);
                stmt.executeUpdate();
                System.out.println("Student added successfully.");
                loadStudentData();
                clearStudentFields();
            }

        } catch (Exception e) {
            System.out.println("Add Error: " + e.getMessage());
        }
    }

    @FXML
    private void studentUpdate(ActionEvent event) {
        ManageStudent selected = StudentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No student selected.");
            return;
        }

        try {
            String id = tfID.getText().trim();
            String name = tfName.getText().trim();
            String dept = tfDept.getText().trim();
            String phone = tfPhone.getText().trim();
            String email = tfEmail.getText().trim();
            String password = tfPass.getText().trim();

            if (id.isEmpty() || name.isEmpty() || dept.isEmpty() || phone.isEmpty() || email.isEmpty() || password.isEmpty()) {
                System.out.println("Please fill all fields.");
                return;
            }

            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "UPDATE students SET name = ?, dept = ?, phone = ?, email = ?, password = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, dept);
                stmt.setString(3, phone);
                stmt.setString(4, email);
                stmt.setString(5, password);
                stmt.setString(6, id);
                stmt.executeUpdate();
                System.out.println("Student updated successfully.");
                loadStudentData();
                clearStudentFields();
            }

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    @FXML
    private void studentDelete(ActionEvent event) {
        ManageStudent selected = StudentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No student selected.");
            return;
        }

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "DELETE FROM students WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selected.getId());
            stmt.executeUpdate();
            System.out.println("Student deleted.");
            loadStudentData();
            clearStudentFields();
        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }

    @FXML
    private void courseAdd(ActionEvent event) {
        try {
            String course_name = tfcrsName.getText().trim();
            String instructor = tfcrsInstructor.getText().trim();
            String course_dept = tfcrsDept.getText().trim();
            String credits = tfcrsCredit.getText();
            String ids = tfCID.getText().trim();

            if (ids.isEmpty() || credits.isEmpty() || course_name.isEmpty() || instructor.isEmpty() || course_dept.isEmpty()) {
                System.out.println("Please fill all fields.");
                return;
            }

            int course_id = Integer.parseInt(ids);
            float credit = Float.parseFloat(credits);
            
            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "INSERT INTO courses (course_id, course_name, instructor, course_dept, credit) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, course_id);
                stmt.setString(2, course_name);
                stmt.setString(3, instructor);
                stmt.setString(4, course_dept);
                stmt.setFloat(5, credit);
                stmt.executeUpdate();
                System.out.println("Course added successfully.");
                loadCourseData();
                clearCourseFields();
            }

        } catch (Exception e) {
            System.out.println("Add Error: " + e.getMessage());
        }
    }

    @FXML
    private void courseUpdate(ActionEvent event) {
        ManageCourse selected = CourseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No course selected.");
            return;
        }

        try {
            String course_name = tfcrsName.getText().trim();
            String instructor = tfcrsInstructor.getText().trim();
            String course_dept = tfcrsDept.getText().trim();
            String credits = tfcrsCredit.getText();
            String ids = tfCID.getText().trim();

            if (ids.isEmpty() || credits.isEmpty() || course_name.isEmpty() || instructor.isEmpty() || course_dept.isEmpty()) {
                System.out.println("Please fill all fields.");
                return;
            }

            int course_id = Integer.parseInt(ids);
            float credit = Float.parseFloat(credits);
            
            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "UPDATE courses SET course_name = ?, instructor = ?, course_dept = ?, credit = ? WHERE course_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, course_name);
                stmt.setString(2, instructor);
                stmt.setString(3, course_dept);
                stmt.setFloat(4, credit);
                stmt.setInt(5, course_id);
                stmt.executeUpdate();
                System.out.println("Course updated successfully.");
                loadCourseData();
                clearCourseFields();
            }

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    @FXML
    private void courseDelete(ActionEvent event) {
        ManageCourse selected = CourseTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No course selected.");
            return;
        }

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "DELETE FROM courses WHERE course_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selected.getCourse_id());
            stmt.executeUpdate();
            System.out.println("Course deleted.");
            loadCourseData();
            clearCourseFields();
        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }

    @FXML
    private void enrollAdd(ActionEvent event) {
        try {
            String enrollmentIdS =tfenID.getText().trim();
            String studentIdS = tfstdID.getText().trim();
            String courseIdS = tfcrsID.getText().trim();
            LocalDate enroll_date = dpenDate.getValue();
            String status = comboStatus.getValue();

            if (enrollmentIdS == null || studentIdS == null || courseIdS == null || enroll_date == null || status == null) {
                System.out.println("Please fill all fields.");
                return;
            }
            
            int enrollment_id = Integer.parseInt(enrollmentIdS);
            int student_id = Integer.parseInt(studentIdS);
            int course_id = Integer.parseInt(courseIdS);

            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "INSERT INTO enrollments (enrollment_id, student_id, course_id, enroll_date, status) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, enrollment_id);
                stmt.setInt(2, student_id);
                stmt.setInt(3, course_id);
                stmt.setDate(4, java.sql.Date.valueOf(enroll_date));
                stmt.setString(5, status);
                stmt.executeUpdate();
                System.out.println("Enrollment added successfully.");
                loadEnrollmentData();
                clearEnrollmentFields();
            }

        } catch (Exception e) {
            System.out.println("Add Error: " + e.getMessage());
        }
    }

    @FXML
    private void enrollUpdate(ActionEvent event) {
        Enrollment selected = EnrollmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No student selected.");
            return;
        }

        try {
            String enrollmentIdS =tfenID.getText().trim();
            String studentIdS = tfstdID.getText().trim();
            String courseIdS = tfcrsID.getText().trim();
            LocalDate enroll_date = dpenDate.getValue();
            String status = comboStatus.getValue();

            if (enrollmentIdS == null || studentIdS == null || courseIdS == null || enroll_date == null || status == null) {
                System.out.println("Please fill all fields.");
                return;
            }
            
            int enrollment_id = Integer.parseInt(enrollmentIdS);
            int student_id = Integer.parseInt(studentIdS);
            int course_id = Integer.parseInt(courseIdS);

            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "UPDATE enrollments SET student_id = ?, course_id = ?, enroll_date = ?, status = ? WHERE enrollment_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, enrollment_id);
                stmt.setInt(2, student_id);
                stmt.setInt(3, course_id);
                stmt.setDate(4, java.sql.Date.valueOf(enroll_date));
                stmt.setString(5, status);
                stmt.executeUpdate();
                System.out.println("Enrollment updated successfully.");
                loadEnrollmentData();
                clearEnrollmentFields();
            }

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    @FXML
    private void enrollDelete(ActionEvent event) {
        Enrollment selected = EnrollmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No student selected.");
            return;
        }

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "DELETE FROM enrollments WHERE enrollment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selected.getEnrollment_id());
            stmt.executeUpdate();
            System.out.println("Enrollment deleted.");
            loadEnrollmentData();
            clearEnrollmentFields();
        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }

    @FXML
    private void repAdd(ActionEvent event) {
        try {
            String reportIdS = tfrepID.getText().trim();
            String report_title = tfrepTitle.getText().trim();
            LocalDate report_date = dprepDate.getValue();
            String report_type = comborepType.getValue();
            String report_data = tarepData.getText().trim();

            if (reportIdS.isEmpty() || report_title.isEmpty() || report_date == null || report_type.isEmpty() || report_data.isEmpty()) {
                System.out.println("Please fill all fields.");
                return;
            }
            
            int report_id = Integer.parseInt(reportIdS);

            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "INSERT INTO reports (report_id, report_title, report_date, report_type, report_data) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, report_id);
                stmt.setString(2, report_title);
                stmt.setDate(3, java.sql.Date.valueOf(report_date));
                stmt.setString(4, report_type);
                stmt.setString(5, report_data);
                stmt.executeUpdate();
                System.out.println("Report added successfully.");
                loadReportData();
                clearReportFields();
            }

        } catch (Exception e) {
            System.out.println("Add Error: " + e.getMessage());
        }
    }

    @FXML
    private void repUpdate(ActionEvent event) {
        Report selected = ReportTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No report selected.");
            return;
        }

        try {
            String reportIdS = tfrepID.getText().trim();
            String report_title = tfrepTitle.getText().trim();
            LocalDate report_date = dprepDate.getValue();
            String report_type = comborepType.getValue();
            String report_data = tarepData.getText().trim();

            if (reportIdS.isEmpty() || report_title.isEmpty() || report_date == null || report_type.isEmpty() || report_data.isEmpty()) {
                System.out.println("Please fill all fields.");
                return;
            }
            
            int report_id = Integer.parseInt(reportIdS);

            try (Connection conn = ConnectionDB.getConnection()) {
                String sql = "UPDATE reports SET report_title = ?, report_date = ?, report_type = ?, report_data = ? WHERE report_id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, report_title);
                stmt.setDate(2, java.sql.Date.valueOf(report_date));
                stmt.setString(3, report_type);
                stmt.setString(4, report_data);
                stmt.setInt(5, report_id);
                stmt.executeUpdate();
                System.out.println("Report updated successfully.");
                loadReportData();
                clearReportFields();
            }

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    @FXML
    private void repDelete(ActionEvent event) {
        Report selected = ReportTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            System.out.println("No report selected.");
            return;
        }

        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "DELETE FROM reports WHERE report_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, selected.getReport_id());
            stmt.executeUpdate();
            System.out.println("Report deleted.");
            loadReportData();
            clearReportFields();
        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
        }
    }

    @FXML
    private void ManageStudent(ActionEvent event) {
        showOnly(ManageStudentVbox);
    }

    @FXML
    private void ManageCourse(ActionEvent event) {
        showOnly(ManageCourseVbox);
    }

    @FXML
    private void ViewEnrollment(ActionEvent event) {
        showOnly(EnrollVbox);
    }

    @FXML
    private void GenerateReport(ActionEvent event) {
        showOnly(ReportVbox);
    }

    private void clearStudentFields() {
        tfID.clear();
        tfName.clear();
        tfDept.clear();
        tfPhone.clear();
        tfEmail.clear();
    }
    
    private void clearCourseFields() {
        tfcrsName.clear();
        tfcrsInstructor.clear();
        tfcrsDept.clear();
        tfcrsCredit.clear();
        tfCID.clear();
    }
    
    private void clearEnrollmentFields() {
        tfenID.clear();
        tfstdID.clear();
        tfcrsID.clear();
        dpenDate.setValue(null);
        comboStatus.setValue(null);
    }
    
    private void clearReportFields() {
        tfrepID.clear();
        tfrepTitle.clear();
        dprepDate.setValue(null);
        comborepType.setValue(null);
        tarepData.clear();
    }
    
    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Signin Form");
        stage.show();
        ((Stage) LogoutBtn.getScene().getWindow()).close();
    }
}
