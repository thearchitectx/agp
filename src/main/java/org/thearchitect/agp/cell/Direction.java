package org.thearchitect.agp.cell;

/**
 *
 * @author x4rb
 */
public enum Direction {
    NORTH(-1,0), SOUTH(1,0), EAST(0,1), WEST(0,-1);
    
    public final int[] vector;

    private Direction(int vectorX, int vectorY) {
        this.vector = new int[]{vectorX, vectorY};
    }
    
}
