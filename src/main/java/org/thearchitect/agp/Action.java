package org.thearchitect.agp;

import java.util.function.Supplier;

/**
 *
 * @author x4rb
 * @param <T>
 */
public class Action<T> {
    private final String label;
    private final String description;
    private final Supplier<T> supplier;

    public static ActionBuilder builder() {
        return new ActionBuilder();
    }
    
    public static <T> ActionBuilder builder(Class<T> clazz) {
        return new ActionBuilder<>();
    }

    Action(final String label, final String description, final Supplier<T> supplier) {
        this.label = label;
        this.description = description;
        this.supplier = supplier;
    }

    public String getLabel() {
        return label;
    }

    public String getDescription() {
        return description;
    }

    public Supplier<T> getSupplier() {
        return supplier;
    }

}
