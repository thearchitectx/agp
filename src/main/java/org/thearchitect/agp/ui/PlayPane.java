package org.thearchitect.agp.ui;

import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import org.thearchitect.agp.cell.Cell;
import static org.thearchitect.agp.ui.util.NodeUtils.*;

public class PlayPane extends StackPane {
    @FXML
    private TextFlow textFlow;
    @FXML
    private CellPlayer cellPlayer;
    
    public PlayPane() {
        loadFXML((Node)this, "/fxml/PlayPane.fxml");
        bindHeightAsParentProportion((Region)this, 1);
        bindMaxHeightAsParentProportion((Region)textFlow, 0.25f);
        display().plain("This is ").bold("MY").plain(" text !");
    }
    
    public BooleanProperty cellPlayerVisibleProperty() {
        return cellPlayer.visibleProperty();
    }
    
    public void loadCell(Cell cell) {
        cellPlayer.loadCell(cell);
    }
    
    /**
     *
     * @return
     */
    public final TextDisplayFlow display() {
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
