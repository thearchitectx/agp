package org.thearchitect.agp.ui;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class InfoPane extends Pane {
    
    public InfoPane() {
        loadFXML((Node)this, "/fxml/InfoPane.fxml");
    }
    
}
