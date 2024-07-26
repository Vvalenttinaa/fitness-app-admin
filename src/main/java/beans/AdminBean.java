package beans;

import dao.AdminDAO;
import dto.Admin;

public class AdminBean {

    private boolean loggedIn = false;

    public Admin getUser(String username, String password)
    {

        Admin admin= AdminDAO.getAdminByUsername(username);

        if(admin!=null && password.equals(admin.getPassword()))
        {
            loggedIn=true;
            
            System.out.println(admin + "ulogovan");
            return admin;
        }
        else
        {
            return null;
        }
    }

    public boolean getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}