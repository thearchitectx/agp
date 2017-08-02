package org.thearchitect.agp.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import org.thearchitect.agp.UIState;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class MainScene extends SplitPane {
    @FXML
    private CharacterPane characterPane;
    @FXML
    private PlayPane playPane;
    @FXML
    private ActionPane actionPane;

    public MainScene() {
        loadFXML((Node)this, "/fxml/MainScene.fxml");
        actionPane.addEventHandler(ActionPane.EVENT_TYPE_ACTION_FIRED, this::handleActionFired);
    }
    
    public void play(UIState uiState) {
        actionPane.actionsProperty().get().clear();
        actionPane.actionsProperty().get().addAll( uiState.getActionSupplier().get() );
        playPane.display().plain(uiState.getText());
    }
    
    protected void handleActionFired(ActionPane.ActionFiredEvent actionEvent) {
        System.out.println("#"+ actionEvent.actionOutcome);
        if (actionEvent.actionOutcome instanceof UIState) {
            play((UIState) actionEvent.actionOutcome);
        }
    }
}
