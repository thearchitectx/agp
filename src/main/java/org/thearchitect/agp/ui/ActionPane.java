package org.thearchitect.agp.ui;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import org.thearchitect.agp.Action;
import org.thearchitect.agp.ui.util.GenericListCell;
import static org.thearchitect.agp.ui.util.NodeUtils.bindHeightAsParentProportion;
import static org.thearchitect.agp.ui.util.NodeUtils.bindWidthAsParentProportion;
import static org.thearchitect.agp.ui.util.NodeUtils.loadFXML;

public class ActionPane extends BorderPane {
    public static final EventType EVENT_TYPE_ACTION_FIRED = new EventType("ActionFired"); 
    
    @FXML
    private ListView<Action> listView;
    @FXML
    private Button btDoIt;
    
        
    public void ActionPane() {
        loadFXML(this, "/fxml/ActionPane.fxml");
        
        bindWidthAsParentProportion(this,1f);
        bindHeightAsParentProportion(this,1f);
        
        listView.setCellFactory(GenericListCell.factory( data -> data.cell.setText(data.item.getLabel()) ) );
        btDoIt.disableProperty().bind(listView.getSelectionModel().selectedItemProperty().isNull());
    }
    
    public ObjectProperty<ObservableList<Action>> actionsProperty() {
        return listView.itemsProperty();
    }
    
    public void doit() {
        Action action = listView.getSelectionModel().getSelectedItem();
        Object outcome = action.getSupplier().get();
        Event.fireEvent(this, new ActionFiredEvent(outcome));
    }
    
    public class ActionFiredEvent extends Event {
        public final Object actionOutcome;
        
        public ActionFiredEvent(Object actionOutcome) {
            super(EVENT_TYPE_ACTION_FIRED);
            this.actionOutcome = actionOutcome;
        }
    }
    
}
