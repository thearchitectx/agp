package org.thearchitect.agp.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import org.thearchitect.agp.cell.Cell;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class CellPlayer extends StackPane  {
    @FXML
    private CellViewer cellViewer;
    @FXML
    private CellNavigator cellNavigator;
    
    private Cell cell;
    
    public CellPlayer() {
        loadFXML((Node)this, "/fxml/CellPlayer.fxml");
        cellNavigator.setNavigationConsumer( direction -> {
            cellViewer.navigate(direction);
            cellViewer.update();
            cellNavigator.setNavigability(cellViewer.getPositionNavigability());
        });
        cellNavigator.setNavigability(cellViewer.getPositionNavigability());
    }
    
    public void loadCell(Cell cell) {
        this.cell = cell;
        cellViewer.loadCell(cell, null, null);
        cellNavigator.setNavigability(cellViewer.getPositionNavigability());
    }
    
}
