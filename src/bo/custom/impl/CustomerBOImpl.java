package bo.custom.impl;

import bo.custom.CustomerBO;
import dao.DAOFactory;
import dao.custom.CustomerDAO;
import dto.CustomerDTO;
import entity.Customer;

public class CustomerBOImpl implements CustomerBO {

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) {
        return false;
    }
}