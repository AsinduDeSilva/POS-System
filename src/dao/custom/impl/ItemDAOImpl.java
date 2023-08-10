package dao.custom.impl;

import dao.CRUDUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.SQLException;

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
}
