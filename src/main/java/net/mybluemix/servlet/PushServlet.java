package net.mybluemix.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PushServlet
 */
@WebServlet("/PushServlet")
public class PushServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static int counter = 0 ; 
    
    private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_CONNECTION = "jdbc:db2://awh-yp-small03.services.dal.bluemix.net:50001/BLUDB:sslConnection=true;";
	private static final String DB_USER = "dash104211";
	private static final String DB_PASSWORD = "0oRYURuUy3rG";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PushServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	String reset = request.getParameter("r"); 
	if (reset != null && !reset.isEmpty()){
		counter = 0;
	}
	   if(counter < 6 ){
		   response.getWriter().append("No ");
		   counter++;
	   }else{
		response.getWriter().append("Alter: There is a  fire.... ");
	   }
	   Connection  con = getDBConnection();
	   response.getWriter().append("Connection Successfull.... ");
	   

	}

	
	private static Connection getDBConnection() {

		Connection dbConnection = null;

		try {

			Class.forName(DB_DRIVER);

		} catch (ClassNotFoundException e) {

			System.out.println(e.getMessage());

		}

		try {

			dbConnection = DriverManager.getConnection(
                             DB_CONNECTION, DB_USER,DB_PASSWORD);
			return dbConnection;

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		}

		return dbConnection;

	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
