package org.thearchitect.agp.ui;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import org.thearchitect.agp.cell.Cell;
import org.thearchitect.agp.cell.Sheet;

public class CellEditor implements Initializable {
    
    @FXML
    private BorderPane root;
    @FXML
    private TextField textWidth;
    @FXML
    private TextArea textTiles;
    @FXML
    private TextArea textLayer0;
    @FXML
    private TextArea textLayer1;
    @FXML
    private TextArea textLayer2;
    @FXML
    private TextArea textLayer3;
    @FXML
    private TextArea textLayer4;
    @FXML
    private CellViewer cellViewerController;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textWidth.setText("4");
        textTiles.setText("04 01 14 09\n16 16 11 13\n10 01 01 08\n11 01 03 16");
        textLayer0.setText("17 17 00 00\n00 00 00 00\n00 00 00 17\n17 17 17 00");
        textLayer1.setText("18 00 00 00\n00 00 18 00\n18 00 00 00\n18 00 00 00");
        textLayer2.setText("00 00 00 19\n00 00 00 19\n00 00 00 19\n00 00 19 00");
        textLayer3.setText("20 20 20 20\n00 00 00 00\n20 20 00 00\n00 00 00 00");
        textLayer4.setText("00 00 00 00\n00 00 00 00\n00 00 00 00\n00 00 00 00");
    }    
    
    @FXML
    public void reload() {
        int[] tiles = Cell.parseTiles(textTiles.getText());
        int[][] layers = new int[5][tiles.length];
        layers[0] = Cell.parseTiles(textLayer0.getText());
        layers[1] = Cell.parseTiles(textLayer1.getText());
        layers[2] = Cell.parseTiles(textLayer2.getText());
        layers[3] = Cell.parseTiles(textLayer3.getText());
        layers[4] = Cell.parseTiles(textLayer4.getText());
        
        Cell cell = Cell.builder()
                .sheet(Sheet.load("/sheet/city.yml"))
                .tiles(tiles)
                .layers(layers)
                .width(Integer.parseInt(textWidth.getText()))
                .build();
        
        boolean [] visibles = new boolean[tiles.length];
        Arrays.fill(visibles, true);
        cellViewerController.load(cell, null, null);
    }


}
