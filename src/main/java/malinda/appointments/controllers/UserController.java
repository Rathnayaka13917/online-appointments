package malinda.appointments.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.filters.ExpiresFilter.XHttpServletResponse;

import malinda.appointments.models.User;
import malinda.appointments.services.UserService;

/**
 * Servlet implementation class UserController
 */
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println("action"+action);
		if(action.equals("/login")) {
			if(request.getSession().getAttribute("user")==null) {
				getLogin(request, response);
			}else {
				getHomePage(request, response);
			}
		}else if(action.equals("/users")) {
			getAllUsers(request,response);
		}else if(action.equals("/users/new")) {
			getCreatePage(request, response);
		}else if(action.equals("/users/view")) {
			getUserById(request, response);
		}else if(action.equals("/users/update")) {
			getUserById(request, response);
		}else if(action.equals("/users/delete")) {
			deleteUser(request, response);
		}else if(action.equals("/logout")){
			request.getSession().invalidate();
			getLogin(request, response);
		}else {
			getHomePage(request, response);
		}
	}
//	Get Methods
	public void getHomePage(HttpServletRequest req,HttpServletResponse res) {
		try {
			res.sendRedirect("home");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void getLogin(HttpServletRequest req,HttpServletResponse res) {
		try {
			req.getRequestDispatcher("views/login.jsp").forward(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void getCreatePage(HttpServletRequest req,HttpServletResponse res) {
		try {
			req.getRequestDispatcher("/views/users/_form.jsp").forward(req, res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getRequestURI().substring(request.getContextPath().length());
		
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa"+action);
		response.getOutputStream().println(action);
		if(action.equals("/login")) {
			loginUser(request,response);
		}
		else if(action.equals("/users/new")) 
		{
			addUser(request, response);
		}
		else if(action.equals("/users/update")) 
		{
			updateUser(request,response);
		}
	}
//	Post Methods
	public void loginUser(HttpServletRequest req,HttpServletResponse res) {
		
		try {
			String email=req.getParameter("email");
			String password=req.getParameter("password");
			User user=new UserService().userLogin(email, password);
			if(user!=null) {
				req.getSession(true).setAttribute("user",user);
				getHomePage(req, res);
			}else {
				res.sendRedirect("http://localhost:8080/online-appointments/login");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void getAllUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg ="";
		UserService service = new UserService();
		try {
			List<User> users = service.getAll();
			
			if (users.isEmpty()) {
				msg = "Empty users!!!";
			}
			request.setAttribute("userList", users);
			
		} catch (Exception e) {
			msg = e.getMessage();
			System.out.println(e.getMessage());
		}
		
		request.setAttribute("message", msg);

		request.getRequestDispatcher("/views/users/index.jsp").forward(request, response);
	}
	
	private void getUserById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		UserService service = new UserService();
		int id = Integer.parseInt(request.getParameter("id"));
	   
		User user= new User();
		try {
		    user = service.findById(id);
		    if(user.getName().isEmpty() ) {
		    	msg = "There is no any user under User Id:" +id;
		    }
		} catch (ClassNotFoundException | SQLException e) {
		   msg = e.getMessage();
		}
	   
	    request.setAttribute("message", msg);
	    request.setAttribute("user", user);
		RequestDispatcher rd = request.getRequestDispatcher("/views/users/_form.jsp");
		rd.forward(request, response);
	}
	
	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		UserService service = new UserService();
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setType(request.getParameter("type"));
		user.setIs_active(Integer.parseInt(request.getParameter("status")));
		
		try {
			boolean result = service.create(user);
			 if(result) {
				msg = "This user has been added successfully! User Name:" + user.getName();
			 }
			 else {
				 msg = "Failed to add the user! User Name:" + user.getName();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
		
		request.setAttribute("message", msg);	
		response.sendRedirect("http://localhost:8080/online-appointments/users");
	}
	
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = "";
		UserService service = new UserService();
		int id = Integer.parseInt(request.getParameter("id"));
		User user = new User();
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setType(request.getParameter("type"));
		user.setIs_active(Integer.parseInt(request.getParameter("status")));
		
		try {
			boolean result = service.update(user, id);
			 if(result) {
				msg = "This user has been updated successfully! User Name:" + user.getName();
			 }
			 else {
				 msg = "Failed to update the user! User Name:" + user.getName();
			 }
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
		   
		request.setAttribute("message", msg);	
		response.sendRedirect("http://localhost:8080/online-appointments/users");
	}
	
	private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = "";
		int id =Integer.parseInt(request.getParameter("id"));
		UserService service = new UserService();
		try {
			service.delete(id);
		} catch (ClassNotFoundException | SQLException e) {
			msg = e.getMessage();
		}
	   
		HttpSession session = request.getSession();
		session.setAttribute("deleteMessage", msg);
	   
	   response.sendRedirect("http://localhost:8080/online-appointments/users");
	}

}
