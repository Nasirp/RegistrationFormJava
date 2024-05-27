package nasir.com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class FormServlet extends HttpServlet {
	private static final String query = "INSERT INTO ENQUIRY(NAME,EMAIL,MOBILE,STREAM,GENDER) VALUES(?,?,?,?,?)";

	
   @Override
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   PrintWriter pw = res.getWriter();
	   res.setContentType("text/html");
	   
	   //User Information
	   String Name = req.getParameter("name");
	   String Email = req.getParameter("email");
       String mobile = req.getParameter("mobile");
       String stream = req.getParameter("stream");
       String gender = null;
       if(gender == "Select") {
    	   pw.println("null");
       }
       else {
    	   gender = req.getParameter("gender");
       }
      
	   
	 //Load JDBC Driver
	   
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
		  
	   } catch(ClassNotFoundException cnf) {
		   cnf.printStackTrace();
	   }
	   
	 //generate the connection
	   try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/form", "root", "");
			   PreparedStatement ps = con.prepareStatement(query);   ){
	       ps.setString(1, Name);
	       ps.setString(2, Email);
	       ps.setString(3, mobile);
	       ps.setString(4, stream);
	       ps.setString(5, gender);
	       int count = ps.executeUpdate();
	       if(count==1) {
	    	   
	    	   pw.println("<script src=\"https://cdn.tailwindcss.com\"></script>");
	    	   pw.println("<div class='flex justify-center'>");
	    	   pw.println("<h1 class='item-center bg-gray-200 mt-10 h-32 w-1/3 absolute text-center content-center shadow-lg shadow-black text-2xl font-bold'>Your response is recorded.</h1>");
	          pw.println("</div>");
	       
	       }else {
	    	   pw.println("<h2>Record Not Registered❎ </h2>");
	       }
   } catch(SQLException se) {
	  se.printStackTrace();
	  pw.println("<h1>"+ se.getMessage()+"<h1>");
  }catch(Exception e) {
	  e.printStackTrace();
	  pw.println("<h1>"+ e.getMessage()+"<h1>");

  }
	   
	   


}

   
   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	   doGet(req,res);
	}
}
