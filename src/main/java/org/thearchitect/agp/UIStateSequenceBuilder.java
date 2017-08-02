package org.thearchitect.agp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class UIStateSequenceBuilder {
    
    private final List<String> images = new ArrayList();
    private final List<String> texts = new ArrayList();

    UIStateSequenceBuilder() {
    }

    public UIStateSequenceBuilder state(String image, String value) {
        this.images.add(image);
        this.texts.add(value);
        return this;
    }

    public UIStateSequence build() {
        return new UIStateSequence(images.toArray(new String[images.size()]), texts.toArray(new String[texts.size()]));
    }
    
}
