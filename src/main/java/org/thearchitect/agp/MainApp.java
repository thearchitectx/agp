package org.thearchitect.agp;

import static java.util.Arrays.asList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.thearchitect.agp.cell.Cells;
import org.thearchitect.agp.ui.MainScene;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        MainScene main = new MainScene();
        Scene scene = new Scene(main);
        
        DefaultUIState s = new DefaultUIState(null, "What now", () -> asList(
                Action.builder().label("Option A").outcome(Cells.MY_CITY()).build(),
                Action.builder().label("Option B").outcome(
                    UIStateSequence.builder().state(null, "Sentence 1")
                                                .state(null, "Sentence 2")
                                                .state(null, "Sentence 3").build()
                ).build()
        ));
        
        main.play(s);
        
        
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
