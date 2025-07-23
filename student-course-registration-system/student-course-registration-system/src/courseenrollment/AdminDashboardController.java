/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseenrollment;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author zubay
 */
public class AdminDashboardController implements Initializable {
    private AnchorPane mainContentPane;
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
    private VBox ManageStudentVbox;
    @FXML
    private Label studentLabel;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colDept;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colPhone;
    @FXML
    private VBox ManageCourseVbox;
    @FXML
    private Label courseLabel;
    @FXML
    private TableColumn<?, ?> colCname;
    @FXML
    private TableColumn<?, ?> colCinstructor;
    @FXML
    private TableColumn<?, ?> colCdept;
    @FXML
    private TableColumn<?, ?> colCcredit;
    @FXML
    private TableColumn<?, ?> colCcapacity;
    @FXML
    private VBox EnrollVbox;
    @FXML
    private Label ViewLabel;
    @FXML
    private TableColumn<?, ?> colenID;
    @FXML
    private TableColumn<?, ?> colstdID;
    @FXML
    private TableColumn<?, ?> colcrsID;
    @FXML
    private TableColumn<?, ?> colenDate;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private VBox ReportVbox;
    @FXML
    private Label reportLabel;
    @FXML
    private TableColumn<?, ?> colrepID;
    @FXML
    private TableColumn<?, ?> colrepTitle;
    @FXML
    private TableColumn<?, ?> colrepDateTime;
    @FXML
    private TableColumn<?, ?> colrepType;
    @FXML
    private TableColumn<?, ?> colrepData;
    @FXML
    private TableColumn<?, ?> colDept31;
    @FXML
    private ComboBox<String> tableCombo;
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
    private Button studentAddBtn;
    @FXML
    private Button studentUpdateBtn;
    @FXML
    private Button studentDeleteBtn;
    @FXML
    private VBox tfCourseVbox;
    @FXML
    private TextField tfcrsName;
    @FXML
    private TextField tfcrsInstructor;
    @FXML
    private TextField tfcrsDept;
    @FXML
    private TextField tfcrsCredit;
    @FXML
    private TextField tfcrsCapacity;
    @FXML
    private Button courseAddBtn;
    @FXML
    private Button courseUpdateBtn;
    @FXML
    private Button courseDeleteBtn;
    @FXML
    private VBox tfEnrollVbox;
    @FXML
    private TextField tfenID;
    @FXML
    private TextField tfstdID;
    @FXML
    private TextField tfcrsID;
    @FXML
    private TextField tfenDate;
    @FXML
    private ComboBox<?> comboStatus;
    @FXML
    private Button enrollAddBtn;
    @FXML
    private Button enrollUpdateBtn;
    @FXML
    private Button enrollDeleteBtn;
    @FXML
    private VBox tfReportVbox;
    @FXML
    private TextField tfrepID;
    @FXML
    private TextField tfrepTitle;
    @FXML
    private TextField tfDateTime;
    @FXML
    private ComboBox<?> comborepType;
    @FXML
    private TextArea tarepData;
    @FXML
    private Button repAddBtn;
    @FXML
    private Button repUpdateBtn;
    @FXML
    private Button repDeleteBtn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    private void loadView(String fxmlFile) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile));
            mainContentPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void studentAdd(ActionEvent event) {
    }

    @FXML
    private void studentUpdate(ActionEvent event) {
    }

    @FXML
    private void studentDelete(ActionEvent event) {
    }

    @FXML
    private void courseAdd(ActionEvent event) {
    }

    @FXML
    private void courseUpdate(ActionEvent event) {
    }

    @FXML
    private void courseDelete(ActionEvent event) {
    }

    @FXML
    private void enrollAdd(ActionEvent event) {
    }

    @FXML
    private void enrollUpdate(ActionEvent event) {
    }

    @FXML
    private void enrollDelete(ActionEvent event) {
    }

    @FXML
    private void repAdd(ActionEvent event) {
    }

    @FXML
    private void repUpdate(ActionEvent event) {
    }

    @FXML
    private void repDelete(ActionEvent event) {
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

    @FXML
    private void LogOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signin.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Signup Form");
        stage.show();
        ((Stage) LogoutBtn.getScene().getWindow()).close();
    }
}
