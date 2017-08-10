package org.thearchitect.agp.ui;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import org.thearchitect.agp.Action;
import org.thearchitect.agp.ui.util.GenericListCell;
import static org.thearchitect.agp.ui.util.NodeUtils.bindHeightAsParentProportion;
import static org.thearchitect.agp.ui.util.NodeUtils.bindWidthAsParentProportion;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class ActionPane extends BorderPane {
    public static final EventType EVENT_TYPE_ACTION_FIRED = new EventType(EventType.ROOT, "ActionFiredEvent"); 
    
    @FXML
    private ListView<Action> listView;
    @FXML
    private Button btDoIt;
        
    public ActionPane() {
        loadFXML((Node)this, "/fxml/ActionPane.fxml");
        
        bindWidthAsParentProportion((Region)this,1f);
        bindHeightAsParentProportion((Region)this,1f);
        
        listView.setCellFactory(GenericListCell.factory( data -> data.cell.setText(data.item.getLabel()) ) );
        listView.itemsProperty().get().addListener((ListChangeListener)(ListChangeListener.Change c) -> { listView.getSelectionModel().clearAndSelect(0); });
        btDoIt.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
    }
    
    public ObjectProperty<ObservableList<Action>> actionsProperty() {
        return listView.itemsProperty();
    }
    
    public void doit() {
        Action action = listView.getSelectionModel().getSelectedItem();
        Event.fireEvent(this, new ActionFiredEvent(action));
    }
    
    public class ActionFiredEvent extends Event {
        public final Action action;
        
        public ActionFiredEvent(Action action) {
            super(EVENT_TYPE_ACTION_FIRED);
            this.action = action;
        }
    }
    
}
