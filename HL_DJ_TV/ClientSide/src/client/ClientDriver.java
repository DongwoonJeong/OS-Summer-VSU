/**
* @CourseNumber  CS 4345
* @Semester  Summer 2020
* @version  Assignment 2
* @Author  Hoyong Lee, DongWoon Jeong, Tianna Vazquez
*/
package client;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientDriver {

	public static String UserID;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket clientSocket = new Socket("127.0.0.1", 8888);
			
			RecieveThread receiveThread = new RecieveThread();
			receiveThread.setSocket(clientSocket);
			
			SendThread sendThread = new SendThread();
			sendThread.setSocket(clientSocket);
			
			sendThread.start();
			receiveThread.start();
		
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
