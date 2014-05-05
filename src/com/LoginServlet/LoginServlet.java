package com.LoginServlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

//import java.io.PrintWriter;
/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String NULL = null;

    /**
     * Default constructor. 
     */
    public LoginServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		if(request.getParameter("code")==NULL)
		{
			response.sendRedirect("http://graph.facebook.com/oauth/authorize?client_id=598128043614342&redirect_uri=http://localhost:8080/FoodBook/LoginServlet&response_type=code");
		}
		else
		{
			
			HttpClient client = new DefaultHttpClient();
			HttpGet req=new HttpGet("https://graph.facebook.com/oauth/access_token?client_id=598128043614342&redirect_uri=http://localhost:8080/FoodBook/LoginServlet&client_secret=3d726e3d1910f42eaa12b8b3dbda4d54&code="+request.getParameter("code"));
			HttpResponse resp = client.execute(req);
			BufferedReader rd = new BufferedReader(new InputStreamReader(resp.getEntity().getContent()));
			String result ="";
			String str = "";
			while ((str = rd.readLine()) != null)
			{	
				result = result.concat(str);
			}
			
			String []temp1 = result.split("&");
			String []temp2 = temp1[0].split("=");
			HttpSession session = request.getSession();
			session.setAttribute("fb-access-token", temp2[1]);
			
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
