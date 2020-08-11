/**
* @CourseNumber  CS 4345
* @Semester  Summer 2020
* @version  Assignment 2
* @Author  Hoyong Lee, DongWoon Jeong, Tianna Vazquez
*/
package client;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class RecieveThread extends Thread{

	private Socket socket;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		
		try {
			BufferedReader tempBuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String receiveString;
			String[] split;
			
			while(true)
			{
				receiveString = tempBuffer.readLine();
				
				split = receiveString.split(">");
				if(split.length >= 2 && split[0].equals(ClientDriver.UserID))
				{
					continue;
				}
				
				System.out.println(receiveString);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	public void setSocket(Socket _socket)
	{
		socket = _socket;
	}

}
