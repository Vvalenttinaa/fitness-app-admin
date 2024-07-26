package beans;

import java.io.Serializable;
import java.util.List;

import dao.AdvicerDAO;
import dto.Advicer;


public class AdvicerBean implements Serializable {


    private Advicer advicer;
    public AdvicerBean()
    {
    }

    public Advicer getAdvicer() {
        return advicer;
    }
    
    public void setAdvicer(Advicer advicer) {
    	this.advicer = advicer;
    }

    public void setCustomadvicer(Advicer advicer) {
        this.advicer = advicer;
    }

    public List<Advicer> getAllAdvicers()
    {
    	List<Advicer> advicers = AdvicerDAO.getAll();
    	for(Advicer u: advicers) {
    		System.out.println(u);
    	}
        return AdvicerDAO.getAll();
    }

    public Advicer getAdvicerById(Integer id)
    {
        return AdvicerDAO.getById(id);
    }

    public boolean updateAdvicer(Advicer advicer)
    {
        return AdvicerDAO.updateAdvicer(advicer);
    }

    public boolean insertAdvicer(Advicer advicer)
    {
        return AdvicerDAO.insertAdvicer(advicer);
    }
    
    public boolean deleteAdvicer(Integer id) {
    	return AdvicerDAO.deleteAdvicer(id);
    }
}
