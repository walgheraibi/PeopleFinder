import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Finder
 */
@WebServlet("/Finder")
public class Finder extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String peoleFinder = "";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Finder() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			String url = "jdbc:oracle:thin:testuser/password@localhost";

			// properties for creating connection to Oracle database
			Properties props = new Properties();
			props.setProperty("user", "testdb");
			props.setProperty("password", "password");

			// creating connection to Oracle database using JDBC
			Connection conn = null;

			conn = DriverManager.getConnection(url, props);

			String lastName = request.getParameter("lastName");
			lastName = lastName.replace(lastName.substring(0, 1), lastName
					.substring(0, 1).toUpperCase());
			String sql = "select customers.FULLNAME, customers.title, customers.STREETADDRESS, LOCATIONS.city, LOCATIONS.STATES, customers.ZIPCODE,  customers.EMAILADDRESS, customers.POSITION, COMPANIES.company from customers, COMPANIES, LOCATIONS where customers.lastname ='"
					+ lastName + "' and COMPANIES.id = customers.company and LOCATIONS.id=customers.city";
			// creating PreparedStatement object to execute query
			PreparedStatement preStatement = conn.prepareStatement(sql);
			ResultSet result = preStatement.executeQuery();
			peoleFinder = "<table class=\"table\"> <thead><tr><th>FULLNAME</th><th>TITLE</th><th>STREETADDRESS</th><th>CITY</th><th>STATE</th><th>ZIPCODE</th><th>EMAILADDRESS</th><th>POSITION</th><th>COMPANY NAME</th></tr> </thead> <tbody>";

			// System.out.println("<ul class=\"list-group\">");
			if (result.next()) {
				while (result.next()) {
					peoleFinder += "<tr><td>" + result.getString("FULLNAME")
							+ "</td><td>" + result.getString("TITLE")
							+ "</td><td>" + result.getString("STREETADDRESS")
							+ "</td><td>" + result.getString("CITY")
							+ "</td><td>" + result.getString("STATES")
							+ "</td><td>" + result.getString("ZIPCODE")
							+ "</td><td>" + result.getString("EMAILADDRESS")
							+ "</td><td>" + result.getString("POSITION")
							+ "</td><td>" + result.getString("COMPANY")
							+ "</td></tr>";

				}
				peoleFinder += "<tbody></table>";
			}

			else {
				sql = "select customers.FULLNAME, customers.title, customers.STREETADDRESS, LOCATIONS.city, LOCATIONS.STATES,  customers.ZIPCODE,  customers.EMAILADDRESS, customers.POSITION, COMPANIES.company from customers, COMPANIES, LOCATIONS where (lastname like '%"
						+ lastName
						+ "%' or COMPANIES.company like '%"
						+ lastName + "%') and (COMPANIES.id = customers.company"
								+ " and LOCATIONS.id=customers.city)";
				// creating PreparedStatement object to execute query
				preStatement = conn.prepareStatement(sql);
				result = preStatement.executeQuery();
				if(result.next())
					{
					while (result.next()) {
						peoleFinder += "<tr><td>" + result.getString("FULLNAME")
								+ "</td><td>" + result.getString("TITLE")
								+ "</td><td>" + result.getString("STREETADDRESS")
								+ "</td><td>" + result.getString("CITY")
								+ "</td><td>" + result.getString("STATES")
								+ "</td><td>" + result.getString("ZIPCODE")
								+ "</td><td>" + result.getString("EMAILADDRESS")
								+ "</td><td>" + result.getString("POSITION")
								+ "</td><td>" + result.getString("COMPANY")
								+ "</td></tr>";

					}
					
				} 
				
				lastName = lastName.replace(lastName.substring(0, 1), lastName
						.substring(0, 1).toLowerCase());
				sql = "select customers.FULLNAME, customers.title, customers.STREETADDRESS, LOCATIONS.city, LOCATIONS.STATES,  customers.ZIPCODE,  customers.EMAILADDRESS, customers.POSITION, COMPANIES.company from customers, COMPANIES, LOCATIONS where (lastname like '%"
						+ lastName
						+ "%' or COMPANIES.company like '%"
						+ lastName + "%') and (COMPANIES.id = customers.company"
								+ " and LOCATIONS.id=customers.city)";
				// creating PreparedStatement object to execute query
				preStatement = conn.prepareStatement(sql);
				result = preStatement.executeQuery();
			
				while (result.next()) {
					peoleFinder += "<tr><td>" + result.getString("FULLNAME")
							+ "</td><td>" + result.getString("TITLE")
							+ "</td><td>" + result.getString("STREETADDRESS")
							+ "</td><td>" + result.getString("CITY")
							+ "</td><td>" + result.getString("STATES")
							+ "</td><td>" + result.getString("ZIPCODE")
							+ "</td><td>" + result.getString("EMAILADDRESS")
							+ "</td><td>" + result.getString("POSITION")
							+ "</td><td>" + result.getString("COMPANY")
							+ "</td></tr>";

				}
				
				 if(peoleFinder.equals("<table class=\"table\"> <thead><tr><th>FULLNAME</th><th>TITLE</th><th>STREETADDRESS</th><th>CITY</th><th>STATE</th><th>ZIPCODE</th><th>EMAILADDRESS</th><th>POSITION</th><th>COMPANY NAME</th></tr> </thead> <tbody>"))
					{
						peoleFinder = "There are no result";
						conn.close();
						}
				
			}
				peoleFinder += "<tbody></table>";
			conn.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		request.setAttribute("peoleFinder", peoleFinder);
		getServletContext().getRequestDispatcher("/output.jsp").forward(
				request, response);
	}

}
