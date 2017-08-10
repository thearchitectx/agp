package org.thearchitect.agp.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import org.thearchitect.agp.UIState;
import org.thearchitect.agp.cell.Cell;
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
    
    public void play(UIState uIState) {
        playPane.cellPlayerVisibleProperty().set(false);
        actionPane.actionsProperty().get().clear();
        actionPane.actionsProperty().get().addAll( uIState.getActionSupplier().get() );
        playPane.display().plain(uIState.getText());
    }
    
    public void play(Cell cell) {
        playPane.cellPlayerVisibleProperty().set(true);
        actionPane.actionsProperty().get().clear();
        playPane.loadCell(cell);
    }
    
    protected void handleActionFired(ActionPane.ActionFiredEvent actionEvent) {
        Object outcome = actionEvent.action.getSupplier().get();
        
        if (outcome instanceof UIState) {
            play((UIState) outcome);
        } else if (outcome instanceof Cell) {
            play((Cell) outcome);
        }
    }
}
