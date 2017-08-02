package org.thearchitect.agp.ui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CharacterViewer implements Initializable {
    
    @FXML
    private Pane root;
    private List<Node> layers;
    
    private ImageView getBackground() {
        return (ImageView) layers.get(0);
    }
    
    private ImageView getBody() {
        return (ImageView) layers.get(0);
    }
    
    private ImageView getLayer(int layer) {
        return (ImageView) layers.get(1+layer);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        layers = root.getChildrenUnmodifiable();
        
        getBackground().setImage(new Image(getClass().getResourceAsStream("/img/character/background-00.jpg")));
        getBody().setImage(new Image(getClass().getResourceAsStream("/img/character/female/body-fit.png")));
        getLayer(1).setImage(new Image(getClass().getResourceAsStream("/img/character/female/lower-fit-01.png")));
    }    
}
