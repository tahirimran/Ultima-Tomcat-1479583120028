package net.mybluemix.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    
    private static final String DB_DRIVER = "com.ibm.db2.jcc.DB2Driver" ;
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
	   
	   response.getWriter().append(" \n getting data value");
	   try {
		response.getWriter().append(" \n Connection Successfull.... " + dbCall());
	} catch (Exception e) {
		e.printStackTrace(response.getWriter());
		// TODO Auto-generated catch block
		//response.getWriter().append( "\n error " +  e.getMessage()  + " trace " + e.getStackTrace().toString());
	}
	   

	}

	
	private String dbCall() throws Exception{
		
		StringBuilder sb= new StringBuilder();
		
		PreparedStatement preparedStatement = null;
		Connection  dbConnection = getDBConnection();
		   String selectSQL = "select * from OCT_27_FIRE_SENTIMENTS;";
		   preparedStatement = dbConnection.prepareStatement(selectSQL); 
		   ResultSet rs = preparedStatement.executeQuery();
		  
			while (rs.next()) {

				sb.append(" " + rs.getString(1));
				sb.append(" " + rs.getString(2));


			}
			
		return sb.toString();
	}
	
	private static Connection getDBConnection()  throws Exception{

		Connection dbConnection = null;

		

			Class.forName(DB_DRIVER);

		

			dbConnection = DriverManager.getConnection(
                             DB_CONNECTION, DB_USER,DB_PASSWORD);
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
