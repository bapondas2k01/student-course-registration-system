package courseenrollment;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class DashboardController {

    @FXML
    private AnchorPane contentArea;

    @FXML
    private void goHome() {
        System.out.println("Navigating to Home");
        // loadHomeView();
    }

    @FXML
    private void goEnroll() {
        System.out.println("Navigating to Enroll");
        // loadEnrollView();
    }

    @FXML
    private void goCourses() {
        System.out.println("Navigating to Courses");
        // loadCoursesView();
    }

    @FXML
    private void goResults() {
        System.out.println("Navigating to Results");
        // loadResultsView();
    }

    @FXML
    private void logout() {
        System.out.println("Logging out");
        // performLogout();
    }
}
