package org.thearchitect.agp.cell;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

/**
 *
 * @author x4rb
 */
public class Cell {
    private int width;
    private int[] tiles;
    private int[][] layers;
    private Sheet sheet;

    Cell() {
    }

    Cell(int width, int[] tiles, int[][] layer1, Sheet sheet) {
        this.width = width;
        this.tiles = tiles;
        this.layers = layer1;
        this.sheet = sheet;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return tiles.length / width;
    }

    public int[] getTiles() {
        return tiles;
    }

    public int[][] getLayers() {
        return layers;
    }

    public Sheet getSheet() {
        return sheet;
    }
    
    public int pointToIndex(int[] point) {
        return point[0]*this.width + point[1];
    }
    
    public static final Cell loadFromYaml(String cellResource) {
        YamlReader yaml = new YamlReader(new InputStreamReader(Cell.class.getResourceAsStream(cellResource)));
        Map ymlMap = null;
        try {
            ymlMap = (Map) yaml.read();
        } catch (YamlException ex) {
            throw new RuntimeException(ex);
        }
        
        Cell cell = new Cell();
        cell.width = Integer.parseInt((String)ymlMap.get("width"));
        cell.tiles = parseTiles((String)ymlMap.get("tiles"));
        cell.sheet = Sheet.load( (String) ymlMap.get("sheet") );
        cell.layers = new int[5][cell.width];
        for (int i = 0; i < 5; i++) {
            cell.layers[i] = parseTiles((String)ymlMap.get("layer"+i));
        }
        return cell;
    }
    
    public static int[] parseTiles(String tilesString) {
        String[] tilesArray = tilesString.split("[\\s\\r\\n,]+");
        return Arrays.stream(tilesArray)
                .mapToInt(t -> Integer.parseInt(t) )
                .toArray();
    }
        
    public static boolean[] parseVisibles(String visiblesString) {
        String[] tilesArray = visiblesString.split("[\\s\\r\\n,]+");
        boolean[] visibles = new boolean[tilesArray.length];
        for (int i = 0; i < tilesArray.length; i++) {
            visibles[i] = tilesArray[i].equals("1");
        }
        return visibles;
    }


    public static CellBuilder builder() {
        return new CellBuilder();
    }
    
}
