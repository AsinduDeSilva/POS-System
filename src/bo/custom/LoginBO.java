package bo.custom;

public interface LoginBO{
    boolean checkPassword(String username, String password);
    public String getUserFullName(String username);
}
