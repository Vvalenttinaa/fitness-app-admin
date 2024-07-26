package beans;

import java.io.Serializable;
import java.util.List;

import dao.AttributeDAO;
import dto.Attribute;

public class AttributeBean implements Serializable {

	private Attribute attribute;
    public AttributeBean()
    {
    }

    public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public List<Attribute> getAllAttributes()
    {    	
        return AttributeDAO.getAll();
    }

    public Attribute getAttributeById(Integer id)
    {
        return AttributeDAO.getById(id);
    }

    public boolean updateAttribute(Attribute attribute)
    {
    	System.out.println("update atributa");
        return AttributeDAO.updateAttribute(attribute);
    }

    public boolean insertAttribute(Attribute attribute)
    {
        return AttributeDAO.insertAttribute(attribute.getName(), attribute.getCategory_id());
    }
    
    public boolean deleteAttribute(Integer id) {
    	return AttributeDAO.deleteAttribute(id);
    }

}
