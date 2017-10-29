package distlab3;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ThreadClass extends Thread
{
	private Thread t;
	private String threadName;
	
	private Socket socket2;

	ThreadClass(Socket theSocket) // constructor
	{
		Socket socket2 = theSocket;
		//System.out.println("Creating " +  threadName );
	}

	public static void main(String args[]) throws Exception
	{ 

		ServerSocket serverSocket = new ServerSocket(8000);

		System.out.println("Listening");

		while (true)
		{
			Socket sock = serverSocket.accept();
			System.out.println("Connected");
			new Thread(new ThreadClass(sock)).start();
		}

	}

	public void run()
	{

		try
		{

			//addition start
			
			
			
			// Create data input and output streams
			DataInputStream inputFromClient = new DataInputStream(
					socket2.getInputStream() );
			DataOutputStream outputToClient = new DataOutputStream(
					socket2.getOutputStream() );

			while (true) {
				// Receive radius from the client
				double radius = inputFromClient.readDouble();

				// Compute area
				double area = radius * radius * Math.PI;

				// Send area back to the client
				outputToClient.writeDouble(area);

				//jta.append("Radius received from client: " + radius + '\n');
				//jta.append("Area found: " + area + '\n');
			}
			
			//addition end
			
			
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

	}

}