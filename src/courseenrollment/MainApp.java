package courseenrollment;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dashboard/Dashboard.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/dashboard/style.css").toExternalForm());
        stage.setTitle("Student Course Enrollment System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
