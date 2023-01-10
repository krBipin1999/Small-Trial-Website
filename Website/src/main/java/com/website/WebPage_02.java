package com.website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/signin")
public class WebPage_02 extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		String Id=req.getParameter("id");
		String First=req.getParameter("fname");
		String Last=req.getParameter("lname");
		String Email=req.getParameter("email");
		String Number=req.getParameter("number");
		String DOB=req.getParameter("dob");
		String Gender=req.getParameter("gender");
		String Address=req.getParameter("address");
		String State=req.getParameter("state");
		String Password=req.getParameter("password");
		System.out.println("Thank You "+First);
		String Query="insert into signup2 values (?,?,?,?,?,?,?,?,?,?)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/website","root","abcd");
			PreparedStatement ps=cn.prepareStatement(Query);
			ps.setString(1, Id);
			ps.setString(2, First);
			ps.setString(3, Last);
			ps.setString(4, Email);
			ps.setString(5, Number);
			ps.setString(6, DOB);
			ps.setString(7, Gender);
			ps.setString(8, Address);
			ps.setString(9, State);
			ps.setString(10, Password);
			int count=ps.executeUpdate();
			if(count>0)
			{
				out.println("<h1>Thank You "+First+"</h1>");
				res.sendRedirect("Home.html");
			}
			else
			{
				out.println("<h1>Something Went Wrong...</h1>");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
