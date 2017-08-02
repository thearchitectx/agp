package org.thearchitect.agp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 *
 */
public class ActionSuppliers {
    
    public static Supplier<List<Action>> staticActions(Action ... actions) {
        return new StaticActionSupplier(actions);
    }


    private static class StaticActionSupplier implements Supplier<List<Action>> {
        private final List<Action> actions;
        
        StaticActionSupplier(Action ... actions) {
            this.actions = Arrays.asList(actions);
        }

        @Override
        public List<Action> get() {
            return actions;
        }
    }
    
}
