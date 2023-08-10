package bo.custom;

import bo.SuperBO;

public interface LoginBO extends SuperBO {
    boolean checkPassword(String username, String password);
    public String getUserFullName(String username);
}
