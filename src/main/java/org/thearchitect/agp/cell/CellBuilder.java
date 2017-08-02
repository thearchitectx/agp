package org.thearchitect.agp.cell;

/**
 *
 * @author x4rb
 */
public class CellBuilder {
    
    private int width;
    private int[] tiles;
    private int[][] layers;
    private Sheet sheet;

    CellBuilder() {
    }

    public CellBuilder width(final int value) {
        this.width = value;
        return this;
    }

    public CellBuilder tiles(final int[] value) {
        this.tiles = value;
        return this;
    }

    public CellBuilder layers(final int[][] layers) {
        this.layers = layers;
        return this;
    }

    public CellBuilder sheet(final Sheet sheet) {
        this.sheet = sheet;
        return this;
    }

    public Cell build() {
        return new Cell(width, tiles, layers, sheet);
    }
    
}
