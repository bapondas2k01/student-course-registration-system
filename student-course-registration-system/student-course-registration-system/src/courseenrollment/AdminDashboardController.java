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


public class AdminDashboardController implements Initializable {
    @FXML
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
    private Button AddAdminBtn;
    @FXML
    private Button LogoutBtn;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        loadView("ProductView.fxml"); // Load products by default
//
//        ManageStdBtn.setOnAction(e -> loadView("ProductView.fxml"));
//        ManageCourseBtn.setOnAction(e -> loadView("ManageCourse.fxml"));
//        ApproveRegBtn.setOnAction(e -> loadView("ApproveRegistration.fxml"));
//        ReportBtn.setOnAction(e -> loadView("GenerateReports.fxml"));
//        AddAdminBtn.setOnAction(e -> loadView("AddAdmin.fxml"));
//        LogoutBtn.setOnAction(e -> loadView("Signin.fxml"));

    }    
    
    private void loadView(String fxmlFile) {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource(fxmlFile));
            mainContentPane.getChildren().setAll(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
