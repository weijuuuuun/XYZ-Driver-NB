package chai.Services;

import chai.dao.UserDAO;
import chai.models.User;

public class AuthenticationService {

    private UserDAO userDAO;


    public AuthenticationService(){
        userDAO = new UserDAO();
    }

    public User userLogIn(String userId, String enteredPassword){
        User retrievedUser = this.userDAO.getUser(userId);

        if(retrievedUser == null){
            return null;
        }

        if(!retrievedUser.getPassword().equals(enteredPassword)){
            return null;
        }

        return retrievedUser;
    }

    public User adminLogin(String userId, String enteredPassword){
        User retrievedAdmin = this.userDAO.getUserAdmin(userId);

        if(retrievedAdmin == null){
            return null;
        }

        if(!retrievedAdmin.getPassword().equals(enteredPassword)){
            return null;
        }

        return retrievedAdmin;
    }
}
