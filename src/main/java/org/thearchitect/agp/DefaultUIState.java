package org.thearchitect.agp;

import java.util.List;
import java.util.function.Supplier;

public class DefaultUIState implements UIState {
    private String image;
    private String text;
    private Supplier<List<Action>> actionSupplier;

    public DefaultUIState() {
    }

    public DefaultUIState(String image, String text, Supplier<List<Action>> actionSupplier) {
        this.image = image;
        this.text = text;
        this.actionSupplier = actionSupplier;
    }
    
    @Override
    public String getImage() {
        return image;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public Supplier<List<Action>> getActionSupplier() {
        return actionSupplier;
    }
    
}
