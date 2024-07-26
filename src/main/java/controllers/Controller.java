package controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.AdminBean;
import beans.AdvicerBean;
import beans.AttributeBean;
import beans.CategoryBean;
import beans.LoggerBean;
import beans.UserBean;
import dto.Advicer;
import dto.Attribute;
import dto.Category;
import dto.User;


@WebServlet(name = "controller", value = "/admin-controller")
public class Controller extends HttpServlet {
	
    private static final String LOGIN = "/WEB-INF/pages/login.jsp";
    private static final String HOME = "/WEB-INF/pages/home.jsp";
    private static final String USERS = "/WEB-INF/pages/users.jsp";
    private static final String ADD_USER = "/WEB-INF/pages/add-user.jsp";
    private static final String EDIT_USER = "/WEB-INF/pages/edit-user.jsp";
    private static final String ADVICERS = "/WEB-INF/pages/advicers.jsp";
    private static final String ADD_ADVICER = "/WEB-INF/pages/add-advicer.jsp";
    private static final String EDIT_ADVICER = "/WEB-INF/pages/edit-advicer.jsp";
    private static final String CATEGORIES = "/WEB-INF/pages/category.jsp";
    private static final String ADD_CATEGORY = "/WEB-INF/pages/add-category.jsp";
    private static final String EDIT_CATEGORY = "/WEB-INF/pages/editCategory.jsp";
    private static final String ATTRIBUTES = "/WEB-INF/pages/attributes.jsp";
    private static final String ADD_ATTRIBUTE = "/WEB-INF/pages/add-attribute.jsp";
    private static final String EDIT_ATTRIBUTE = "/WEB-INF/pages/edit-attribute.jsp";
    private static final String LOGS = "/WEB-INF/pages/logs.jsp";



	public Controller() {
		super();
	}
	
	 @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        req.setCharacterEncoding("UTF-8");
	        String address = LOGIN;
	        String action = req.getParameter("action");
	        HttpSession session = req.getSession();
	        session.setAttribute("notification", "");
	        
	        if (action == null || action.equals("")) {
	            address = LOGIN;
	            System.out.print("Login");

	        } else if (action.equals("logout")) {
	            session.invalidate();
	            resp.sendRedirect(req.getContextPath() + "/admin-controller");
	            return;
	        } else if (action.equals("login")) {
	            String username = req.getParameter("username");
	            String password = req.getParameter("password");
	            AdminBean adminBean = new AdminBean();
	            if (adminBean.getUser(username, password) != null) {
	                session.setAttribute("adminBean", adminBean);
	                LoggerBean logBean = new LoggerBean();
	                UserBean userBean = new UserBean();
	                AdvicerBean advicerBean = new AdvicerBean();
	                CategoryBean categoryBean = new CategoryBean();
	                AttributeBean attributeBean = new AttributeBean();
	                session.setAttribute("logBean", logBean);
	                session.setAttribute("userBean", userBean);
	                session.setAttribute("advicerBean", advicerBean);
	                session.setAttribute("categoryBean", categoryBean);
	                session.setAttribute("attributeBean", attributeBean);
	                address = HOME;
	            } else {
	                session.setAttribute("notification", "Invalid credentials");
	            }
	        }else {
	        	AdminBean adminBean = (AdminBean) session.getAttribute("adminBean");
	            if (adminBean == null || !adminBean.getLoggedIn()) {
	                address = LOGIN;
	            } else {
	            	UserBean userBean = (UserBean) session.getAttribute("userBean");
	            	AdvicerBean advicerBean = (AdvicerBean) session.getAttribute("advicerBean");
	                CategoryBean categoryBean = (CategoryBean) session.getAttribute("categoryBean");
	                AttributeBean attributeBean = (AttributeBean) session.getAttribute("attributeBean");
	                LoggerBean loggerBean = (LoggerBean) session.getAttribute("loggerBean");
	                if (action.equals("users")) {
	                    address = USERS;
	                }else if(action.equals("home")){
	                	address = HOME;
	                }else if (action.equals("addUser")) {
	                    address = ADD_USER;
	                    if (req.getParameter("submit") != null) {
	                        User user = new User(0, req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("username"),
	                        		req.getParameter("password"), req.getParameter("card"), "true", req.getParameter("mail"), req.getParameter("city"),
	                        		null);
                        	System.out.println("Submit != null");

	                        if (userBean.insertUser(user)) {
	                        	System.out.println("insert");

	                            address = USERS;
	                        }else {
	                        	System.out.println("Error while adding in base");
	                        }
	                    }
	                } else if(action.equals("editUser")) {
	                	address = EDIT_USER;
	                    Integer id = Integer.parseInt(req.getParameter("id"));
	                    User user = userBean.getUserById(id);
	                    userBean.setUser(user);
	                    System.out.print("usao u edit i uspjesno dobavio" + user);

	                    if (req.getParameter("submit") != null) {
	                    	System.out.println("i submiy usao");
	                        User userUpdate = new User(id, req.getParameter("firstName"), req.getParameter("lastName"), req.getParameter("username"),
	                        		req.getParameter("password"), req.getParameter("card"), "true", req.getParameter("mail"), req.getParameter("city"),
	                        		null);
	                        if (userBean.updateUser(userUpdate)) {
	                            address = USERS;
	                        }
	                    }else {
	                    	System.out.println("prazan submit");
	                    }
	                } else if(action.equals("deleteUser")) {
	                	Integer id = Integer.parseInt(req.getParameter("id"));
	                	User user = userBean.getUserById(id);
	                    userBean.setUser(user);
	                    if(userBean.deleteUser(id)) {
	                    	address=USERS;
	                    }
	                } else if(action.equals("advicers")){
	                	address = ADVICERS;
	                } else if(action.equals("addAdvicer")) {
	                	address = ADD_ADVICER;
	                    if (req.getParameter("submit") != null) {
	                        Advicer advicer = new Advicer(req.getParameter("username"),
	                        		req.getParameter("password"), req.getParameter("firstName"), req.getParameter("lastName"), 0);
                        	System.out.println("Submit != null");

	                        if (advicerBean.insertAdvicer(advicer)) {
	                        	System.out.println("insert");
	                            address = ADVICERS;
	                        }else {
	                        	System.out.println("Error while adding in base");
	                        }
	                    }
	                } else if(action.equals("editAdvicer")) {
	                	address = EDIT_ADVICER;
	                    Integer id = Integer.parseInt(req.getParameter("id"));
	                    Advicer advicer = advicerBean.getAdvicerById(id);
	                    advicerBean.setAdvicer(advicer);
	                    System.out.print("usao u edit i uspjesno dobavio" + advicer);

	                    if (req.getParameter("submit") != null) {
	                    	System.out.println("i submiy usao");
	                        Advicer advicerUpdate = new Advicer(req.getParameter("username"),
	                        		req.getParameter("password"),req.getParameter("firstName"), req.getParameter("lastName"),  id);
	                        if (advicerBean.updateAdvicer(advicerUpdate)) {
	                            address = ADVICERS;
	                        }
	                    }else {
	                    	System.out.println("prazan submit");
	                    }
	                } else if(action.equals("deleteAdvicer")) {
	                	Integer id = Integer.parseInt(req.getParameter("id"));
	                	Advicer advicer = advicerBean.getAdvicerById(id);
	                    advicerBean.setAdvicer(advicer);
	                    if(advicerBean.deleteAdvicer(id)) {
	                    	address = ADVICERS;
	                    }
	                } else if(action.equals("categories")) {
	                	address = CATEGORIES;
	                } else if(action.equals("addCategory")) {
	                	address = ADD_CATEGORY;
	                    if (req.getParameter("submit") != null) {
	                        Category category = new Category(0, req.getParameter("name")
	                        		);
                        	System.out.println("Submit != null");

	                        if (categoryBean.insertCategory(category)) {
	                            address = CATEGORIES;
	                        }else {
	                        	System.out.println("Error while adding in base");
	                        }
	                    }
	                } else if(action.equals("editCategory")) {
	                	address = EDIT_CATEGORY;
	                    Integer id = Integer.parseInt(req.getParameter("id"));
	                    Category category = categoryBean.getCategoryById(id);
	                    categoryBean.setCategory(category);
	                    System.out.print("usao u edit i uspjesno dobavio" + category);

	                    if (req.getParameter("submit") != null) {
	                    	System.out.println("i submiy usao");
	                        Category categoryUpdate = new Category(id, req.getParameter("name")
	                        		);
	                        if (categoryBean.updateCategory(categoryUpdate)) {
	                            address = CATEGORIES;
	                        }
	                    }else {
	                    	System.out.println("prazan submit");
	                    }
	                } else if(action.equals("deleteCategory")) {
	                	Integer id = Integer.parseInt(req.getParameter("id"));
	                	Category category = categoryBean.getCategoryById(id);
	                    categoryBean.setCategory(category);
	                    if(categoryBean.deleteCategory(id)) {
	                    	address = CATEGORIES;
	                    }else {
	                    	address = CATEGORIES;
	                    }
	                } else if(action.equals("openAttributes")) {
	                    System.out.println("bar je u dobroj putanji");
	                	Integer id = Integer.parseInt(req.getParameter("id"));
	                	Category category = categoryBean.getCategoryById(id);
	                    categoryBean.setCategory(category);
	                    System.out.println("bar je u dobroj putanji");
	                	address = ATTRIBUTES;   
	                } else if(action.equals("editAttribute")) {
	                	address = EDIT_ATTRIBUTE;
	                    Integer id = Integer.parseInt(req.getParameter("id"));
	                    Attribute attribute = attributeBean.getAttributeById(id);
	                    attributeBean.setAttribute(attribute);
	                    if (req.getParameter("submit") != null) {
	                    	System.out.println("i submiy usao");
	                        Attribute attributeUpdate = new Attribute(id, req.getParameter("name"), attributeBean.getAttribute().getCategory_id());
	                        if (attributeBean.updateAttribute(attributeUpdate)) {
	                            address = ATTRIBUTES;
	                        }
	                    }else {
	                    	System.out.println("prazan submit");
	                    }
	                } else if(action.equals("deleteAttribute")) {
	                	Integer id = Integer.parseInt(req.getParameter("id"));
	                	Attribute attribute = attributeBean.getAttributeById(id);
	                    attributeBean.setAttribute(attribute);
	                    if(attributeBean.deleteAttribute(id)) {
	                    	address = ATTRIBUTES;
	                    }else {
	                    	address = ATTRIBUTES;
	                    }
	                } else if(action.equals("addAttribute")) {
	                	System.out.println("bar je u dobroj putanji");
	                	Integer id = Integer.parseInt(req.getParameter("id"));
	                	Category category = categoryBean.getCategoryById(id);
	                    categoryBean.setCategory(category);
	                	address = ADD_ATTRIBUTE;
	                    if (req.getParameter("submit") != null) {
	                        Attribute attribute = new Attribute(0, req.getParameter("name"), id
	                        		);
                        	System.out.println("Submit != null");

	                        if (attributeBean.insertAttribute(attribute)) {
	                            address = ATTRIBUTES;
	                        }else {
	                        	System.out.println("Error while adding in base");
	                        }
	                    }
	                } else if(action.equals("logs")) {
	                	address = LOGS;
	                }
	            }
	        }
	        
	        RequestDispatcher dispatcher = req.getRequestDispatcher(address);
	        dispatcher.forward(req, resp);
	        
	 }

	
	@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
