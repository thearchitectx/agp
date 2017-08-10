package org.thearchitect.agp.ui;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import org.thearchitect.agp.cell.Direction;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class CellNavigator extends BorderPane {
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
        loadFXML((Node)this, "/fxml/CellNavigator.fxml");
        this.navigationConsumer = d -> {};
        btNorth.addEventHandler(ActionEvent.ACTION, this::north);
        btSouth.addEventHandler(ActionEvent.ACTION, this::south);
        btWest.addEventHandler(ActionEvent.ACTION, this::west);
        btEast.addEventHandler(ActionEvent.ACTION, this::east);
    }
    
    public void setNavigationConsumer(Consumer<Direction> navigationConsumer) {
        this.navigationConsumer = navigationConsumer;
    }
    
    
    public void setNavigability(Direction ... directions) {
        List<Direction> d = Arrays.asList(directions);
        btNorth.setDisable(!d.contains(Direction.NORTH));
        btSouth.setDisable(!d.contains(Direction.SOUTH));
        btWest.setDisable(!d.contains(Direction.WEST));
        btEast.setDisable(!d.contains(Direction.EAST));
    }
    
    protected void north(Event event) {
        navigationConsumer.accept(Direction.NORTH);
    }
    
    protected void south(Event event) {
        navigationConsumer.accept(Direction.SOUTH);
    }
    
    protected void west(Event event) {
        navigationConsumer.accept(Direction.WEST);
    }
    
    protected void east(Event event) {
        navigationConsumer.accept(Direction.EAST);
    }
}
