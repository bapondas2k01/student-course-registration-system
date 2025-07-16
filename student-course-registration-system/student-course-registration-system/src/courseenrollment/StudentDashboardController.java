/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseenrollment;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;


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
    private Button AddAdminBtn;
    @FXML
    private Button LogoutBtn;
    @FXML
    private AnchorPane mainContentPane;

    private String patientUsername;
    
    private int patient_id;
    public void setPatientId(int id) {
    this.patient_id = id;
    }

    public void setPatientUsername(String username) {
        this.patientUsername = username;
        try (Connection conn = ConnectionDB.getConnection()) {
            String sql = "SELECT id FROM users WHERE username = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    this.patient_id = rs.getInt("id");
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
        // TODO
    }    
    
}
