package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class Server {
	private ServerSocket serverSocket;
	private boolean running;
	// private Client client;
	private static final Logger LOGGER = Logger.getLogger(RobotLogger.class.getName());

	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
			ClientHandler ch = new ClientHandler();
			ch.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	 /**
	 * Log for the claw
	 */
	 public void clawServo() {
	 // Log claw
		 LOGGER.info("Claw");
	 }
	
	 /**
	 * Log for the second arm
	 */
	 public void axis2Servo() {
	 // Log second arm
		 LOGGER.info("Axis 2");
	 }
	
	 /**
	 * Log for the first arm
	 */
	 public void axisServo() {
	 // Log first arm
		 LOGGER.info("Axis 1");
	 }
	
	 /**
	 * Log for the base servo
	 *
	 */
	public void baseServo() {
		// Log base servo
		LOGGER.info("Base");
	}

	private class ClientHandler extends Thread {
		private Socket clientSocket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		String recv;

		public void run() {
			try {
//				 ois = new ObjectInputStream(clientSocket.getInputStream());
//				 oos = new ObjectOutputStream(clientSocket.getOutputStream());
				while (running) {
					clientSocket = serverSocket.accept();
					 recv = ois.readUTF();
					 LOGGER.info(recv);
				}
			} catch (IOException e) {

			}
			
			//HERE IS THE PROBLEM. NEED TO WRITE 2x baseServo(); , Skapa en map som heter files så kan du logga
			 baseServo();
			 baseServo();
			 axisServo();
			 axis2Servo();
			 clawServo();
		}

	}

	public static void main(String[] args) {
		new Server(0);
		new RobotLogger();
	}
}
