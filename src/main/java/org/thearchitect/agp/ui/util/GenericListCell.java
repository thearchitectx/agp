package org.thearchitect.agp.ui.util;

import java.util.function.Consumer;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

/**
 *
 * @author x4rb
 * @param <T>
 */
public class GenericListCell<T> extends ListCell<T> {
    public static class UpdateData<T> {
        public final T item;
        public final boolean empty;
        public final ListCell<T> cell;

        public UpdateData(T item, boolean empty, ListCell<T> cell) {
            this.item = item;
            this.empty = empty;
            this.cell = cell;
        }
    }
    
    private final Consumer<UpdateData<T>> consumer;

    public GenericListCell(Consumer<UpdateData<T>> updateConsumer) {
        this.consumer = updateConsumer;
    }

    @Override
    public void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            this.setText(null);
        } else {
            this.consumer.accept(new UpdateData<>(item,empty, this));
        }
    }
    
    public static <C> Callback<ListView<C>, ListCell<C>> factory(Consumer<UpdateData<C>> consumer) {
        return list -> new GenericListCell<>(consumer);
    }
    
}
