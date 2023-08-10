package dao;

import dao.custom.impl.ItemDAOImpl;
import dao.custom.impl.LoginDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;

    public enum DAOTypes{
        LOGIN, ITEM
    }

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch(daoTypes){
            case LOGIN:
                return new LoginDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            default:
                return null;
        }
    }

}
