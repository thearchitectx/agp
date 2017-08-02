package org.thearchitect.agp.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class CellPlayer implements Initializable {
    @FXML
    private CellViewer cellViewerController;
    @FXML
    private CellNavigator cellNavigatorController;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cellNavigatorController.setNavigationConsumer( direction -> {
            cellViewerController.navigate(direction);
            cellViewerController.update();
            cellNavigatorController.setNavigability(cellViewerController.getPositionNavigability());
        });
        
        cellNavigatorController.setNavigability(cellViewerController.getPositionNavigability());
    }    
    
}
