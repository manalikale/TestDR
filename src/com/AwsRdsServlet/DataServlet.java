package com.AwsRdsServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PostMessageServlet
 */
@WebServlet("/DataServlet")
public class DataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DataServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try{
			HttpSession session = request.getSession();
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mydbinstance.cmsv2jptww03.us-west-2.rds.amazonaws.com:3306/mydb","root","password");
			PreparedStatement pstmt = conn.prepareStatement("select * from messages order by id desc limit 5");
			ResultSet result = pstmt.executeQuery();
			
			ArrayList<String> messages = new ArrayList<String>();
			if(!result.wasNull()){
				if(result.next()){
					messages.add(result.getString("message"));
			
				}
				while(result.next()){
					messages.add(result.getString("message"));
				}
				session.setAttribute("messages", messages);
			
			}
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://mydbinstance.cmsv2jptww03.us-west-2.rds.amazonaws.com:3306/mydb","root","password");
			
			PreparedStatement pstmt = conn.prepareStatement("insert into messages(message) values(?)");
			pstmt.setString(1, request.getParameter("message"));
			pstmt.executeUpdate();
			
			pstmt = conn.prepareStatement("select * from messages order by id desc limit 5");
			ResultSet result = pstmt.executeQuery();
			
			ArrayList<String> messages = new ArrayList<String>();
			if(!result.wasNull()){
				while(result.next()){
					messages.add(result.getString("message"));
				}
				HttpSession session = request.getSession();
				session.setAttribute("messages", messages);
			
			}
			response.sendRedirect(request.getContextPath()+"/index.jsp");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}

}
