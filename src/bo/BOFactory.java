package bo;

import bo.custom.LoginBO;
import bo.custom.impl.LoginBoImpl;


public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public BOFactory getBoFactory(){
        if (boFactory == null){
            return boFactory = new BOFactory();
        }else {
            return boFactory;
        }
    }

    public static LoginBO getInstance(BoTypes boTypes){
        switch (boTypes){
            case LOGIN:
                return new LoginBoImpl();
            default:
                return null;
        }
    }

    public enum BoTypes{
        LOGIN
    }

}