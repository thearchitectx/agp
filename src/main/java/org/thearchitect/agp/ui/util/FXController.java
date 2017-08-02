package org.thearchitect.agp.ui.util;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.Region;

/**
 * 
 * @author x4rb
 * @param <R> 
 */
public abstract class FXController<R extends Region> implements Initializable {
    @FXML
    R root;
    
    protected void initializeWidthAsParentProportion(float scale) {
        Platform.runLater( () -> {
            root.prefWidthProperty().bind(  ((Region)root.getParent()).widthProperty().multiply(scale) );
        });
    }
    
    protected void initializeHeightAsParentProportion(float scale) {
        Platform.runLater( () -> {
            root.prefHeightProperty().bind(  ((Region)root.getParent()).heightProperty().multiply(scale) );
        });
    }
    
    protected void bindWidth(Region child, float scale) {
        child.prefWidthProperty().bind(root.prefWidthProperty().multiply(scale));
    }
    
}
