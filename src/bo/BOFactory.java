package bo;

import bo.custom.LoginBO;
import bo.custom.impl.ItemBOImpl;
import bo.custom.impl.LoginBoImpl;


public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getInstance(){
        if (boFactory == null){
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes boTypes){
        switch (boTypes){
            case LOGIN:
                return new LoginBoImpl();
            case ITEM:
                return new ItemBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes{
        LOGIN, ITEM
    }

}