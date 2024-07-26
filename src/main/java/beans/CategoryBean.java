package beans;

import java.io.Serializable;
import java.util.List;

import dao.CategoryDAO;
import dto.Attribute;
import dto.Category;

public class CategoryBean implements Serializable {

	    private Category category;
	    public CategoryBean()
	    {
	    }
	    

	    public Category getCategory() {
			return category;
		}


		public void setCategory(Category category) {
			this.category = category;
		}



		public List<Category> getAllCategories()
	    {
	    	List<Category> users = CategoryDAO.getAll();
	    	
	        return CategoryDAO.getAll();
	    }

	    public Category getCategoryById(Integer id)
	    {
	        return CategoryDAO.getById(id);
	    }

	    public boolean updateCategory(Category category)
	    {
	        return CategoryDAO.updateCategory(category);
	    }

	    public boolean insertCategory(Category category)
	    {
	        return CategoryDAO.insertCategory(category.getName());
	    }
	    
	    public boolean deleteCategory(Integer id) {
	    	return CategoryDAO.deleteCategory(id);
	    }
	    
	    public List<Attribute> getAttributesByCategory()
	    {
	    	System.out.println("sada ce get attributes");
	    	return CategoryDAO.getAttributesByCategory(this.category);
	    }
	


}
