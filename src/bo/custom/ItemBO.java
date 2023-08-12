package bo.custom;

import bo.SuperBO;
import dto.ItemDTO;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean saveItem(ItemDTO itemDTO);
    String getNextID();

    ObservableList<ItemDTO> getAllItems();
}
