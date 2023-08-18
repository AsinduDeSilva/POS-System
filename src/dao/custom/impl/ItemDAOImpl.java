package dao.custom.impl;

import dao.CRUDUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public boolean save(Item item) {
        try {
            return CRUDUtil.executeUpdate("insert into item values(?,?,?,?,?,?,?)",
                    item.getItemID(),
                    item.getItemName(),
                    item.getBatchNumber(),
                    item.getPrice(),
                    item.getQty(),
                    item.getSupplier(),
                    item.getExpireDate()
            );
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getLastItemId() {
        try {
            ResultSet resultSet = CRUDUtil.executeQuery("SELECT*FROM item ORDER BY itemID DESC LIMIT 1");
            if(resultSet.next()){
                return resultSet.getString("itemID");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return "I000";
    }

    @Override
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> allItems = new ArrayList<>();
        try {
            ResultSet resultSet = CRUDUtil.executeQuery("SELECT * FROM item");
            while(resultSet.next()){
                allItems.add(new Item(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchNumber"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty"),
                        resultSet.getString("supplier"),
                        resultSet.getDate("expireDate")
                ));
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return allItems;
    }

    @Override
    public Item getItemById(String itemId) {
        try {
            ResultSet resultSet = CRUDUtil.executeQuery("select * from item where itemID=?", itemId);
            if(resultSet.next()){
                return new Item(
                        resultSet.getString("itemID"),
                        resultSet.getString("itemName"),
                        resultSet.getString("batchNumber"),
                        resultSet.getDouble("price"),
                        resultSet.getDouble("qty"),
                        resultSet.getString("supplier"),
                        resultSet.getDate("expireDate")
                );
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean updateItem(Item item) {
        try {
            return CRUDUtil.executeUpdate("UPDATE item SET itemName=?, batchNumber=?, price=?, " +
                            "qty=?, supplier=?, expireDate=? WHERE itemID=?",
                    item.getItemName(),
                    item.getBatchNumber(),
                    item.getPrice(),
                    item.getQty(),
                    item.getSupplier(),
                    item.getExpireDate(),
                    item.getItemID()
            );
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
