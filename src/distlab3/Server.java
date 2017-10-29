package distlab3;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Server extends JFrame
{
	Connection conn;
	Statement theStatement;
	ResultSet resultSet1; // students table (contents: all)
	ResultSet resultSet2; // modulegrades (contents: all)
	
	/** The name of the MySQL account to use (or empty for anonymous) */
	private final String userName = "root";

	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "";

	/** The name of the computer running MySQL */
	private final String serverName = "localhost";

	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;

	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String dbName = "gradedatabase";

	/** The name of the table we are testing with */
	private final String tableName1 = "modulegrades";

	/** The name of the table we are testing with */
	private final String tableName2 = "students";
	
	// Text area for displaying contents
	private JTextArea jta = new JTextArea();

	public static void main(String[] args)
	{
		//new Server();
		
		Server serverApp = new Server();
		serverApp.execute(); // db set up (should only have to happen once).

	}
	
	public Server()
	{

		// Place text area on the frame
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);

		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try
		{
			// Create a server socket (why is the below line never used?)
			ServerSocket serverSocket = new ServerSocket(8000); // 8000 is port
			jta.append("Server started at " + new Date() + '\n');

			// Listen for a connection request
			//Socket socket = serverSocket.accept();

			//			// Create data input and output streams
			//			DataInputStream inputFromClient = new DataInputStream(
			//					socket.getInputStream() );
			//			DataOutputStream outputToClient = new DataOutputStream(
			//					socket.getOutputStream() );

			while (true)
			{
				// Listen for a connection request
				Socket socket = serverSocket.accept(); // Connect to a client

				Thread thread = new TheThreadClass(socket);
				thread.start();
				
				// Create data input and output streams
//				DataInputStream inputFromClient = new DataInputStream( socket.getInputStream() );
//				DataOutputStream outputToClient = new DataOutputStream( socket.getOutputStream() );

				// Receive radius from the client
				//double radius = inputFromClient.readDouble();

				// Compute area
				//double area = radius * radius * Math.PI;

				// Send area back to the client
				//outputToClient.writeDouble(area);

				//jta.append("Radius received from client: " + radius + '\n');
				//jta.append("Area found: " + area + '\n');
			}
		}
		catch(IOException ex)
		{
			System.err.println(ex);
		}
	}
	
	/**
	 * Connect to MySQL and do some stuff.
	 */
	public void execute() {

		// Connect to MySQL for first and only time needed
		//Connection conn = null;
		try {
			System.out.println("got here 1");
			conn = this.getConnection();
			System.out.println("Connected to database");

			theStatement = conn.createStatement(); // let this happen ONE time.

			resultSet1 = theStatement.executeQuery("select * from students"); // ONE time.
			resultSet2 = theStatement.executeQuery("select * from modulegrades"); // ONE time.

		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}

		try {
			resultSet1.absolute(1);
			resultSet2.absolute(1);
		} catch (SQLException e) {
			//Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This only gets a new database connection 
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException { // for initial use only! establishes new connection.
		Connection conn = null;
		Properties connectionProps = new Properties();
		
		connectionProps.put("user", this.userName);
		connectionProps.put("password", this.password);

		conn = DriverManager.getConnection("jdbc:mysql://"
				+ this.serverName + ":" + this.portNumber + "/" + this.dbName,
				connectionProps);

		return conn;
	}

	// TheThreadClass is a class within a class.
	private class TheThreadClass extends Thread
	{
		// 1 socket per client. 1 socket per thread. (1 thread per client)
		private Socket socket;

		// making the streams global variables.
		private DataInputStream clientInput;
		private DataOutputStream clientOutput;
		
		// the constructor.
		public TheThreadClass(Socket socket)
		{
			// The initial appearing of the client's GUI goes in here
			
			// Ok so each client has 1 thread and each thread (of the client) has 1 D.I.S and 1 D.O.S. 
			
			// Create data input and output streams for the single thread for a single client.
			try 
			{
				// creating the D.I.S and D.O.S for each thread (that each client will have)
				DataInputStream inputFromClient = new DataInputStream( socket.getInputStream() );
				DataOutputStream outputToClient = new DataOutputStream( socket.getOutputStream() );
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
		}

		// This is the run method.
		public void run()
		{
			while(true)
			{
				// in here, I will do the transferring of data (input and output)
				
			}
		}
		
	}
	
}