package org.thearchitect.agp.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.application.Platform;
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
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class CellViewer extends GridPane {
    public static final String IMAGE_LOCATION = "/img/cell/location.png";
    public static final int RECT_SIZE = 100;
    public static final int TILE_SHEET_SIZE = 8;
   
    // Loaded cell
    private Cell cell;
    // Display data
    private Node nodeLocation;
    private Image imgSheet;
    private boolean[] visibleTiles;
    private int[] location;

    public CellViewer() {
        loadFXML((Node)this, "/fxml/CellViewer.fxml");
        ImageView imgView = new ImageView(ImageStore.getImage(IMAGE_LOCATION));
        imgView.setFitWidth(RECT_SIZE);
        imgView.setFitHeight(RECT_SIZE);
        this.nodeLocation = imgView;
    }
   
    /**
     * Load a cell
     * @param cell
     * @param visibleTiles
     * @param location 
     */
    public void loadCell(Cell cell, boolean[] visibleTiles, int[] location) {
        this.getChildren().clear();
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
            
            this.add(g, i % cell.getWidth(),  i/cell.getWidth());
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
        this.getChildren().remove(this.nodeLocation);
        
        this.getChildren().forEach( node -> {
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
                Platform.runLater( () -> this.add(nodeLocation, column, row));
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
        if (this.cell==null) {
            return new Direction[0];
        }
        
        List<Direction> l = new ArrayList<>(Arrays.asList(Direction.values()));
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
