package pl.ksr.pon.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.ksr.pon.dao.DAO;
import pl.ksr.pon.dao.Player;
import pl.ksr.pon.dao.PlayerDAOFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App extends Application {
    private static Scene scene;
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage stage) throws Exception {
        scene = new Scene(loadFXML("primary"), 1000, 630);
        App.stage = stage;
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main( String[] args ) throws IOException {
        launch();
    }


}
