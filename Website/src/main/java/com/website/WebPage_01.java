package com.website;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class WebPage_01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		String Email=req.getParameter("email");
		String Password=req.getParameter("password");
		String Query="select Email from signup2 where Email=? and Password=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/website","root","abcd");
//			Statement st=con.createStatement();
			PreparedStatement ps=con.prepareStatement(Query);
			ps.setString(1, Email);
			ps.setString(2, Password);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				RequestDispatcher rd=req.getRequestDispatcher("Home.html");
				rd.forward(req, res);
//				res.sendRedirect("Home.html");
			}
			else
			{
				RequestDispatcher rd=req.getRequestDispatcher("Wrong.html");
				rd.forward(req, res);
//				res.sendRedirect("Wrong.html");
			}
		} catch (ClassNotFoundException | SQLException e) {
			out.println("<h1>Error...</h1>");
		}
	}

}
