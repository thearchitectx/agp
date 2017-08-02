package org.thearchitect.agp;

import java.util.List;
import java.util.function.Supplier;
import static org.thearchitect.agp.ActionSuppliers.staticActions;

/**
 *
 */
public class UIStateSequence implements UIState {
    private final String[] images;
    private final String[] texts;
    private final Supplier<List<Action>> supplier;
    private int position;

    public static UIStateSequenceBuilder builder() {
        return new UIStateSequenceBuilder();
    }

    UIStateSequence(final String[] images, final String[] texts) {
        this.images = images;
        this.texts = texts;
        this.position = 0;
        this.supplier = staticActions(Action.builder().label("Next").supplier(this::next).build());
    }
    
    protected UIState next() {
        if (position>=texts.length-1) {
            return null;
        } else {
            position++;
        }
        
        return this;
    }

    @Override
    public String getImage() {
        return images[position];
    }

    @Override
    public String getText() {
        return texts[position];
    }

    @Override
    public Supplier<List<Action>> getActionSupplier() {
        return supplier;
    }
}
