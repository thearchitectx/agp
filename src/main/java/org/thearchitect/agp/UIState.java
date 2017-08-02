package org.thearchitect.agp;

import java.util.List;
import java.util.function.Supplier;

/**
 *
 */
public interface UIState {
    public String getImage();
    public String getText();
    public Supplier<List<Action>> getActionSupplier();
}
