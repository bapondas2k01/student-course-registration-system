
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SigninController implements Initializable {

    @FXML
    private TextField tfUsername;
    @FXML
    private PasswordField pfPass;
    @FXML
    private Button signinBtn;
    @FXML
    private Hyperlink SignupLink;
    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Signup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Signup.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Signup Form");
        stage.show();
        ((Stage) SignupLink.getScene().getWindow()).close();
    }

    @FXML
    private void Signin(ActionEvent event) throws IOException {
        String input = tfUsername.getText().trim();
        String password = pfPass.getText().trim();

        if (input.isEmpty() || password.isEmpty()) {
            lblError.setText("Username/ID and Password required");
            return;
        }

        try (Connection conn = ConnectionDB.getConnection()) {
            boolean isStudentId = input.matches("\\d+");

            String sql;
            PreparedStatement stmt;

            if (isStudentId) {
                sql = "SELECT * FROM students WHERE id = ? AND password = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, Integer.parseInt(input));
                stmt.setString(2, password);
            } else {
                sql = "SELECT * FROM users WHERE username = ? AND password = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, input);
                stmt.setString(2, password);
            }

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                if (isStudentId) {
                    // Load Student Dashboard (from students table)
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
                    Parent root = loader.load();
                    StudentDashboardController controller = loader.getController();
                    controller.setStudentId(Integer.parseInt(input)); // Implement this method in your controller
                    switchScene(root, "Student Dashboard");

                } else {
                    // Load dashboard based on role
                    String role = rs.getString("role");
                    FXMLLoader loader;

                    switch (role.toLowerCase()) {
                        case "admin":
                            loader = new FXMLLoader(getClass().getResource("AdminDashboard.fxml"));
                            break;
                        case "student":
                            loader = new FXMLLoader(getClass().getResource("StudentDashboard.fxml"));
                            break;
                        default:
                            lblError.setText("Unknown role: " + role);
                            return;
                    }

                    Parent root = loader.load();

                    if (role.equalsIgnoreCase("student")) {
                        StudentDashboardController controller = loader.getController();
                        controller.setStudentUsername(input);
                    }

                    switchScene(root, role.substring(0, 1).toUpperCase() + role.substring(1) + " Dashboard");
                }
            } else {
                lblError.setText("Invalid credentials!");
            }

        } catch (Exception e) {
            lblError.setText("Login error!");
            e.printStackTrace();
        }
    }

    private void switchScene(Parent root, String title) {
        Stage stage = (Stage) lblError.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle(title);
        stage.show();
    }


    
}
