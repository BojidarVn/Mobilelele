package bg.softuni.lection2.demo.service;

public interface UserService {

    /**
     *
     * @param userName
     * @param password
     * @return returns true if the user is authenticate successful ;
     */

    boolean authenticate(String userName, String password);

    void loginUser(String userName);
}
