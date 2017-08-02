package org.thearchitect.agp.ui;

import org.thearchitect.agp.ui.util.FXController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class PlayPane extends FXController<StackPane> {
    @FXML
    private TextFlow textFlow;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeHeightAsParentProportion(1);
        
        display().plain("This is ").bold("MY").plain(" text !");
    }
    
    /**
     *
     * @return
     */
    public TextDisplayFlow display() {
        textFlow.getChildren().clear();
        return new TextDisplayFlow();
    }
    
    public class TextDisplayFlow {
        public TextDisplayFlow plain(String text) {
            textFlow.getChildren().add(new Text(text));
            return TextDisplayFlow.this;
        }
        
        public TextDisplayFlow bold(String text) {
            Text t = new Text(text);
            t.setFont(Font.font(t.getFont().getFamily(), FontWeight.BOLD, t.getFont().getSize()));
            textFlow.getChildren().add(t);
            return TextDisplayFlow.this;
        }
    }
    
    
}
