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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author zubay
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private Button ManageStdBtn;
    @FXML
    private Button ManageCourseBtn;
    @FXML
    private Button ApproveRegBtn;
    @FXML
    private Button ReportBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private VBox MyProfileVbox;
    @FXML
    private Label studentLabel31;
    @FXML
    private TextField tfID;
    @FXML
    private Label studentLabel311;
    @FXML
    private TextField tfName;
    @FXML
    private Label studentLabel312;
    @FXML
    private TextField tfContact;
    @FXML
    private Button updateBtn;
    @FXML
    private Label studentLabel313;
    @FXML
    private VBox ViewCourseVbox;
    @FXML
    private VBox MyEnrollmentsVbox;
    @FXML
    private Label studentLabel3121;
    @FXML
    private Label studentLabel3122;
    @FXML
    private Label studentLabel3123;
    @FXML
    private TextField tfDept;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPass;
    @FXML
    private VBox EnrollVbox;
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
    private Button enrollBtn;
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
    
    private int student_id;
    private String studentUsername;
    
    private ObservableList<ManageCourse> courseList = FXCollections.observableArrayList();
    private ObservableList<Enrollment> enrollmentList = FXCollections.observableArrayList();

    public void setStudentId(int id) {
        this.student_id = id;
        loadProfileData(); // Load profile data once ID is set
        loadEnrollmentData();
    }

    public void setStudentUsername(String username) {
        this.studentUsername = username;
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "SELECT id FROM students WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    setStudentId(rs.getInt("id"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        colCID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colCname.setCellValueFactory(new PropertyValueFactory<>("course_name"));
        colCinstructor.setCellValueFactory(new PropertyValueFactory<>("instructor"));
        colCdept.setCellValueFactory(new PropertyValueFactory<>("course_dept"));
        colCcredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        
        colenID.setCellValueFactory(new PropertyValueFactory<>("enrollment_id"));
        colstdID.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colcrsID.setCellValueFactory(new PropertyValueFactory<>("course_id"));
        colenDate.setCellValueFactory(new PropertyValueFactory<>("enroll_date"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        
        loadCourseData();
        comboStatus.getItems().addAll("Enrolled", "Dropped");
        
    }    

    private void showOnly(VBox toShow) {
        // Hide all
        MyProfileVbox.setVisible(false);
        MyProfileVbox.setManaged(false);

        ViewCourseVbox.setVisible(false);
        ViewCourseVbox.setManaged(false);

        EnrollVbox.setVisible(false);
        EnrollVbox.setManaged(false);
        
        MyEnrollmentsVbox.setVisible(false);
        MyEnrollmentsVbox.setManaged(false);

        // Show selected
        if (toShow != null) {
            toShow.setVisible(true);
            toShow.setManaged(true);
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
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM enrollments WHERE student_id = ?")) {
            stmt.setInt(1, student_id);
            ResultSet rs = stmt.executeQuery();

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
            System.out.println("Load Enrollments Error: " + e.getMessage());
        }
    }

    private void loadProfileData() {
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "SELECT * FROM students WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, student_id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                tfID.setText(String.valueOf(rs.getInt("id")));
                tfName.setText(rs.getString("name"));
                tfDept.setText(rs.getString("dept"));
                tfContact.setText(rs.getString("phone"));
                tfEmail.setText(rs.getString("email"));
                tfPass.setText(rs.getString("password"));
            }
        } catch (Exception e) {
            System.out.println("Profile Load Error: " + e.getMessage());
        }
    }
    
    @FXML
    private void Update(ActionEvent event) {
        try {
            String id = tfID.getText().trim();
            String name = tfName.getText().trim();
            String dept = tfDept.getText().trim();
            String phone = tfContact.getText().trim();
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
//                loadStudentData();
                clearStudentFields();
            }

        } catch (Exception e) {
            System.out.println("Update Error: " + e.getMessage());
        }
    }

    @FXML
    private void Enroll(ActionEvent event) {
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
    private void myProfile(ActionEvent event) {
        showOnly(MyProfileVbox);
    }

    @FXML
    private void viewCourses(ActionEvent event) {
        showOnly(ViewCourseVbox);
    }

    @FXML
    private void enrollCourse(ActionEvent event) {
        showOnly(EnrollVbox);
    }

    @FXML
    private void myEnrollments(ActionEvent event) {
        showOnly(MyEnrollmentsVbox);
    }
    
    private void clearStudentFields() {
        tfID.clear();
        tfName.clear();
        tfDept.clear();
        tfContact.clear();
        tfEmail.clear();
    }
    
    private void clearEnrollmentFields() {
        tfenID.clear();
        tfstdID.clear();
        tfcrsID.clear();
        dpenDate.setValue(null);
        comboStatus.setValue(null);
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Signin Form");
        stage.show();
        ((Stage) LogoutBtn.getScene().getWindow()).close();
    }
    
}
