package org.thearchitect.agp.ui;

import javafx.fxml.FXML;
import javafx.scene.control.SplitPane;
import org.thearchitect.agp.UIState;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class MainScene extends SplitPane {
    @FXML
    private CharacterViewer characterPaneController;
    @FXML
    private PlayPane playPaneController;
    @FXML
    private ActionPane actionPane;

    public MainScene() {
        loadFXML(this, "/fxml/MainScene.fxml");
        actionPane.addEventHandler(ActionPane.EVENT_TYPE_ACTION_FIRED, this::handleActionFired);
    }
    
    public void play(UIState uiState) {
        actionPane.actionsProperty().get().clear();
        actionPane.actionsProperty().get().addAll( uiState.getActionSupplier().get() );
        playPaneController.display().plain(uiState.getText());
    }
    
    protected void handleActionFired(Object actionOutcome) {
        System.out.println("GOT IT"+ actionOutcome);
    }
}
