package dao.custom;

import dao.SuperDAO;
import entity.Item;

import java.util.ArrayList;

public interface ItemDAO extends SuperDAO {
    boolean save(Item item);
    String getLastItemId() throws RuntimeException;

    ArrayList<Item> getAllItems();
    Item getItemById(String itemId);
    boolean updateItem(Item item);

}
