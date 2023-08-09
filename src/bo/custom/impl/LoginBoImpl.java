package bo.custom.impl;

import bo.custom.LoginBO;
import dao.DAOFactory;
import dao.custom.LoginDAO;

public class LoginBoImpl implements LoginBO {

    LoginDAO loginDAO = (LoginDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.LOGIN);
    @Override
    public boolean checkPassword(String username, String password) {
        /*
            If user entered password is matched with the passwordByUsername
            Then this logic will return true. else any other case will return false.
         */

        String passwordByUsername = loginDAO.getPasswordByUsername(username);
        if(passwordByUsername == null){
            return false;
        }else return passwordByUsername.equals(password);

    }

    @Override
    public String getUserFullName(String username) {
        return loginDAO.getUserFullName(username);
    }

}