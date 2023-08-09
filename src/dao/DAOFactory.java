package dao;

import dao.custom.impl.LoginDAOImpl;

public class DAOFactory {

    public static DAOFactory daoFactory;

    public enum DAOTypes{
        LOGIN
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
            default:
                return null;
        }
    }

}
