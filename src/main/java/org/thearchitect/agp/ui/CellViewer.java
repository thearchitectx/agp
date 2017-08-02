package org.thearchitect.agp.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import org.apache.commons.lang.ArrayUtils;
import org.thearchitect.agp.cell.Cell;
import org.thearchitect.agp.cell.Direction;
import org.thearchitect.agp.io.ImageStore;

public class CellViewer implements Initializable {
    public static final String IMAGE_LOCATION = "/img/cell/location.png";
    public static final int RECT_SIZE = 100;
    public static final int TILE_SHEET_SIZE = 8;
   
    @FXML
    private GridPane root;
    // Loaded cell
    private Cell cell;
    // Display data
    private Node nodeLocation;
    private Image imgSheet;
    private boolean[] visibleTiles;
    private int[] location;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView imgView = new ImageView(ImageStore.getImage(IMAGE_LOCATION));
        imgView.setFitWidth(RECT_SIZE);
        imgView.setFitHeight(RECT_SIZE);
        this.nodeLocation = imgView;
        
        cell = Cell.loadFromYaml("/cell/mycity.yml");
                
        load(cell, null, null);
    }
   
    /**
     * Load a cell
     * @param width
     * @param tiles
     * @param visibleTiles
     * @param sheetResource
     */
    public void load(Cell cell, boolean[] visibleTiles, int[] location) {
        this.root.getChildren().clear();
        this.cell = cell;
        this.imgSheet = ImageStore.getImage(cell.getSheet().getImage());
        
        this.visibleTiles = visibleTiles;
        if (this.visibleTiles==null) {
            this.visibleTiles = new boolean[cell.getTiles().length];
            Arrays.fill(this.visibleTiles, true);
        }

        this.location = location;
        if (this.location==null) {
            this.location = new int[] {0,0};
        }
        
        this.visibleTiles[this.cell.pointToIndex(this.location)] = true;
        
        for (int i = 0; i < cell.getTiles().length; i++) {
            Integer tile = this.cell.getTiles()[i];
            float[] anchor = tileIndexToPoint(tile);
            
            ImagePattern ip = new ImagePattern(imgSheet, anchor[0], anchor[1], TILE_SHEET_SIZE, TILE_SHEET_SIZE, true);
            Group g = new Group();
            Rectangle r = new Rectangle(RECT_SIZE, RECT_SIZE);
            r.setFill(ip);
            g.getChildren().add(r);
            
            for (int layer = 0; layer < this.cell.getLayers().length; layer++) {
                tile = this.cell.getLayers()[layer][i];
                if (tile>0) {
                    anchor = tileIndexToPoint(tile);
                    ip = new ImagePattern(imgSheet, anchor[0], anchor[1], TILE_SHEET_SIZE, TILE_SHEET_SIZE, true);
                    r = new Rectangle(RECT_SIZE, RECT_SIZE);
                    r.setFill(ip);
                    g.getChildren().add(r);
                }
            }
            
            this.root.add(g, i % cell.getWidth(),  i/cell.getWidth());
        }
        
        this.update();
    }
   
    private float[] tileIndexToPoint(int tileIndex) {
        return new float[] { TILE_SHEET_SIZE-(tileIndex%TILE_SHEET_SIZE), 
                            TILE_SHEET_SIZE-(tileIndex/TILE_SHEET_SIZE)};
    }
    
    
    /**
     * Update cell viewer
     */
    public void update() {
        this.root.getChildren().remove(this.nodeLocation);
        
        this.root.getChildren().forEach( node -> {
            int row = GridPane.getRowIndex(node);
            int column = GridPane.getColumnIndex(node);
            int tileIndex = (row * this.cell.getWidth()) + column;
            
            float distance = 
                /* x */ Math.abs(row-this.location[0]) * 0.5f
                /* y */ + Math.abs(column-this.location[1]) * 0.5f;
            
            float b = 0 - distance;
            
            // Force visible distant tiles
            if (b <= -1 && this.visibleTiles[tileIndex]) {
                b = -0.65f;
            }
            
            // Pin location
            if (distance==0) {
                Platform.runLater( () -> this.root.add(nodeLocation, column, row));
            }
            
            node.setEffect(new ColorAdjust(0, 0, b, 0));
        });
    }
    
    public void navigate(Direction direction) {
        this.location[0] += direction.vector[0];
        this.location[1] += direction.vector[1];
        this.visibleTiles[this.cell.pointToIndex(this.location)] = true;
    }

    public Direction[] getPositionNavigability() {
        List<Direction> l = new ArrayList<Direction>(Arrays.asList(Direction.values()));
        if (this.location[1]<=0) {
            l.remove(Direction.WEST);
        } else if (ArrayUtils.contains(this.cell.getSheet().getBlocked(), this.cell.getTiles()[ indexPositionMoving(0,-1) ])) {
            l.remove(Direction.WEST);
        } 
        
        if (this.location[1]>=this.cell.getWidth()-1) {
            l.remove(Direction.EAST);
        } else if (ArrayUtils.contains(this.cell.getSheet().getBlocked(), this.cell.getTiles()[ indexPositionMoving(0,1) ])) {
            l.remove(Direction.EAST);
        } 
        
        if (this.location[0]<=0) {
            l.remove(Direction.NORTH);
        } else if (ArrayUtils.contains(this.cell.getSheet().getBlocked(), this.cell.getTiles()[ indexPositionMoving(-1,0) ])) {
            l.remove(Direction.NORTH);
        } 
        
        if (this.location[0]>=this.cell.getHeight()-1) {
            l.remove(Direction.SOUTH);
        } else if (ArrayUtils.contains(this.cell.getSheet().getBlocked(), this.cell.getTiles()[ indexPositionMoving(1,0) ])) {
            l.remove(Direction.SOUTH);
        } 
        
        
        
//        if (ArrayUtils.contains(this.cell.getSheet().getBlocked(), positionIndex+1)) {
//            l.remove(Direction.EAST);
//        }
        return l.toArray(new Direction[l.size()]);
    }
        
    private int indexPositionMoving(int x, int y) {
        int[] p = ArrayUtils.clone(this.location);
        p[0]+=x;
        p[1]+=y;
        return this.cell.pointToIndex(p);
    }
}