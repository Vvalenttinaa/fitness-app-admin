package beans;

import java.io.Serializable;
import java.util.List;

import dao.UserDAO;
import dto.User;

public class UserBean implements Serializable {


    private User user;
    public UserBean()
    {
    }

    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
    	this.user = user;
    }

    public List<User> getAllUsers()
    {
    	List<User> users = UserDAO.getAll();
    	for(User u: users) {
    		System.out.println(u);
    	}
        return UserDAO.getAll();
    }

    public User getUserById(Integer id)
    {
        return UserDAO.getById(id);
    }

    public boolean updateUser(User user)
    {
        return UserDAO.updateUser(user);
    }

    public boolean insertUser(User user)
    {
        return UserDAO.insertUser(user);
    }
    
    public boolean deleteUser(Integer id) {
    	return UserDAO.deleteUser(id);
    }
}
