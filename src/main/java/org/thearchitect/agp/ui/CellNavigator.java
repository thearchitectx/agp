package org.thearchitect.agp.ui;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import org.thearchitect.agp.cell.Direction;

public class CellNavigator implements Initializable {
    private Consumer<Direction> navigationConsumer;
    @FXML
    private Button btNorth;
    @FXML
    private Button btSouth;
    @FXML
    private Button btWest;
    @FXML
    private Button btEast;
    
    public CellNavigator() {
        setNavigationConsumer(null);
    }
    
    public final void setNavigationConsumer(Consumer<Direction> navigationConsumer) {
        this.navigationConsumer = navigationConsumer;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.navigationConsumer = d -> {};
    }    
    
    public void setNavigability(Direction ... directions) {
        List<Direction> d = Arrays.asList(directions);
        btNorth.setDisable(!d.contains(Direction.NORTH));
        btSouth.setDisable(!d.contains(Direction.SOUTH));
        btWest.setDisable(!d.contains(Direction.WEST));
        btEast.setDisable(!d.contains(Direction.EAST));
    }
    
    public void north() {
        navigationConsumer.accept(Direction.NORTH);
    }
    
    public void south() {
        navigationConsumer.accept(Direction.SOUTH);
    }
    
    public void west() {
        navigationConsumer.accept(Direction.WEST);
    }
    
    public void east() {
        navigationConsumer.accept(Direction.EAST);
    }
}
