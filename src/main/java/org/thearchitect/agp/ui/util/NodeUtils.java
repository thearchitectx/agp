package org.thearchitect.agp.ui.util;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.Region;

/**
 * 
 * @author x4rb
 * @param <R> 
 */
public abstract class NodeUtils<R extends Region>  {
    
    public static void loadFXML(Node node, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(NodeUtils.class.getResource(path));
            loader.setRoot(node);
            loader.setController(node);
            loader.load();
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }
    
    public static void bindWidthAsParentProportion(Region node, float scale) {
        Platform.runLater( () -> {
            node.prefWidthProperty().bind(  ((Region)node.getParent()).widthProperty().multiply(scale) );
        });
    }
    
    public static void bindHeightAsParentProportion(Region node, float scale) {
        Platform.runLater( () -> {
            node.prefHeightProperty().bind(  ((Region)node.getParent()).heightProperty().multiply(scale) );
        });
    }
    
    public static void bindWidth(Region node, Region child, float scale) {
        child.prefWidthProperty().bind(node.prefWidthProperty().multiply(scale));
    }
    
}
