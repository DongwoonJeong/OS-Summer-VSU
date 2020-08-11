/**
* @CourseNumber  CS 4345
* @Semester  Summer 2020
* @version  Assignment 2
* @Author  Hoyong Lee, DongWoon Jeong, Tianna Vazquez
*/
package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class ServerDriver {

	public static ArrayList<PrintWriter> outputList;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		outputList = new ArrayList<PrintWriter>();
		
			try {
				ServerSocket serverSocket = new ServerSocket(8888);
				
				while(true)
				{
					Socket clientSocket = serverSocket.accept();
					ClientManager clientThread = new ClientManager();
					clientThread.setSocket(clientSocket);
					
					outputList.add(new PrintWriter(clientSocket.getOutputStream()));
					
					clientThread.start();
				}
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
