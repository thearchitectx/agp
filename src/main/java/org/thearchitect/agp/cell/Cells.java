package org.thearchitect.agp.cell;

/**
 *
 */
public class Cells {
    public static final Cell MY_CITY() {
        return Cell.loadFromYaml("/cell/mycity.yml");
    }
}
