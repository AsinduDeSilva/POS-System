package dao.custom;

import dao.SuperDAO;
import entity.Customer;

public interface CustomerDAO extends SuperDAO {
    boolean saveCustomer(Customer customer);
}
