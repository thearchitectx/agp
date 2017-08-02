package org.thearchitect.agp;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.thearchitect.agp.ui.MainScene;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/fxml/MainScene.fxml"));
//        Parent root = loader.load();
        
        
        MainScene main = new MainScene();
        Scene scene = new Scene(main);
                
        main.play(UIStateSequence.builder().state(null, "Sentence 1")
                                            .state(null, "Sentence 2")
                                            .state(null, "Sentence 3").build());
        
        stage.setScene(scene);
        stage.setTitle("JavaFX and Maven");
        stage.setMaximized(true);
        stage.show();
        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
