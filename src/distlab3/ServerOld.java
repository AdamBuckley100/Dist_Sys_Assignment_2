package distlab3;

import java.io.*;
import java.net.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ServerOld extends JFrame {

	// Text area for displaying contents
	private JTextArea jta = new JTextArea();

	public static void main(String[] args) {
		new Server();
	}

	public ServerOld() {
		// Place text area on the frame
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);

		setTitle("Server");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true); // It is necessary to show the frame here!

		try {

			// Create a server socket3 [(why is the below line never used?)]
			ServerSocket serverSocket = new ServerSocket(8000); // 8000 is port
			jta.append("Server started at " + new Date() + '\n');

			// Listen for a connection request
			Socket socket3t = serverSocket.accept();

			// note the following 5 line are taken out on here on his class (multi threaded)
			// Create data input and output streams
			DataInputStream inputFromClient = new DataInputStream(
					socket3t.getInputStream() );
			DataOutputStream outputToClient = new DataOutputStream(
					socket3t.getOutputStream() );

			while (true) {
				// Receive radius from the client
				double theAnnualInterestRate = inputFromClient.readDouble();
				double theNumberOfYears = inputFromClient.readDouble();
				double theLoanAmount = inputFromClient.readDouble();

				double theAnnualInterestRateAsADecimal = theAnnualInterestRate/100;

				// Compute
				double theInterest = theLoanAmount * theAnnualInterestRateAsADecimal * theNumberOfYears;

				double theResultingTotal = theInterest + theLoanAmount;

				// Send area back to the client
				outputToClient.writeDouble(theResultingTotal);

				jta.append("Total: " + theResultingTotal + '\n');
			}
		}
		catch(IOException ex) {
			System.err.println(ex);
		}
	}

	private class MyClient extends Thread // rn
	{

		private Socket socket3t;

		private DataInputStream clientInput; // drn
		private DataOutputStream clientOutput; // drn
		
		public MyClient(Socket socket3t) throws IOException
		{
			this.socket3t = socket3t;
			
			clientInput = new DataInputStream(socket3t.getInputStream());
			clientOutput = new DataOutputStream(socket3t.getOutputStream());
			

		}

//		public void run()
//		{
//			try
//			{
//				
//			}
//			catch(IOException ex) 
//			{
//				
//			}
//			
//		}


		
		
		
	change the while to
	
	

	
	
	have the following: ()
	
	try{
		//create a server socket3t
		ServerSocket serverSocket = new ServerSocket(8000);
		jta.append("Server started at " + new Date() + '\n');
	
	while(true) [change the while true above on line 43 to the following:]
	{
		Socket s1 = serverSocket.accept();
		myClient c = new myClient(s1);
		c.start();
	}
	
	//then commented out was the following line:
	// Listen for a connection request
		
		
		
		
		

	}

}