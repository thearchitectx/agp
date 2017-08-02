package org.thearchitect.agp.ui;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class CharacterViewer extends StackPane {
    
    public CharacterViewer() {
        loadFXML((Node)this, "/fxml/CharacterViewer.fxml");
        
        getBackgroundCharacterImage().setImage(new Image(getClass().getResourceAsStream("/img/character/background-00.jpg")));
        getBody().setImage(new Image(getClass().getResourceAsStream("/img/character/female/body-fit.png")));
        getLayer(1).setImage(new Image(getClass().getResourceAsStream("/img/character/female/lower-fit-01.png")));
    } 
    
    private ImageView getBackgroundCharacterImage() {
        return (ImageView) getChildrenUnmodifiable().get(0);
    }
    
    private ImageView getBody() {
        return (ImageView) getChildrenUnmodifiable().get(0);
    }
    
    private ImageView getLayer(int layer) {
        return (ImageView) getChildrenUnmodifiable().get(1+layer);
    }
      
}
