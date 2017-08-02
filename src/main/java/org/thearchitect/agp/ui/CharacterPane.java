package org.thearchitect.agp.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class CharacterPane extends BorderPane {
//    @FXML
//    private CharacterViewer characterViewer;
    
    public CharacterPane() {
        loadFXML((Node)this, "/fxml/CharacterPane.fxml");
    }
    
}
