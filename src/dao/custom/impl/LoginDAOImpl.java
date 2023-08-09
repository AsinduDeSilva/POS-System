package dao.custom.impl;

import dao.CRUDUtil;
import dao.custom.LoginDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public String getPasswordByUsername(String username) {
        try {
            ResultSet resultSet = CRUDUtil.executeQuery("select * from users where username=?", username);
            if(resultSet.next()){
                return resultSet.getString("password");
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getUserFullName(String username) {
        try {
            ResultSet resultSet = CRUDUtil.executeQuery("SELECT*FROM users WHERE username=?", username);
            if(resultSet.next()){
                return resultSet.getString("fullName");
            }
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
