package org.thearchitect.agp;

import java.util.function.Supplier;

/**
 *
 * @author x4rb
 * @param <T>
 */
public class ActionBuilder<T> {
    
    private String label;
    private String description;
    private Supplier<T> supplier;

    ActionBuilder() {
    }

    public ActionBuilder label(final String value) {
        this.label = value;
        return this;
    }

    public ActionBuilder description(final String value) {
        this.description = value;
        return this;
    }

    public ActionBuilder supplier(final Supplier<T> supplier) {
        this.supplier = supplier;
        return this;
    }

    public Action<T> build() {
        return new Action(label, description, supplier);
    }
    
}
