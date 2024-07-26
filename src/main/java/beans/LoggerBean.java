package beans;

import java.io.Serializable;
import java.util.List;

import dao.LoggerDAO;
import dto.Logger;

public class LoggerBean implements Serializable {

    public LoggerBean() {
    }

    public List<Logger> getAll()
    {
        return LoggerDAO.getAll();
    }
}