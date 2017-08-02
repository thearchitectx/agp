package org.thearchitect.agp;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author x4rb
 */
public class Game {
    //<editor-fold defaultstate="collapsed" desc="Singleton boilerplate">
    private static Game instance;
    
    public static Game get() {
        if (instance==null) {
            instance = new Game();
        }
        return instance;
    }
//</editor-fold>

    private final ObjectProperty<GameMode> currentMode = new SimpleObjectProperty();
    
    private Game() {
        currentMode.set(GameMode.SEQUENCE);
    }

    public ObjectProperty<GameMode> currentModeProperty() {
        return currentMode;
    }
    
    
}
