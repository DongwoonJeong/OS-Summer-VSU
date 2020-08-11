/**
* @CourseNumber  CS 4345
* @Semester  Summer 2020
* @version  Assignment 2
* @Author  Hoyong Lee, DongWoon Jeong, Tianna Vazquez
*/
package client;

import java.io.*;
import java.net.Socket;

public class SendThread extends Thread{

	private Socket socket;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			BufferedReader tempBuffer = new BufferedReader(new InputStreamReader(System.in));
			
			PrintWriter sendWriter = new PrintWriter(socket.getOutputStream());
			
			String sendString;
			
			System.out.print("Enter you name: ");
			ClientDriver.UserID = tempBuffer.readLine();
			
			sendWriter.println("IDLeeJeongVazquez123" + ClientDriver.UserID);
			sendWriter.flush();
			
			while(true)
			{
				sendString = tempBuffer.readLine();

				if(sendString.equals("exit"))
				{
					break;
				}
				
				sendWriter.println(sendString);
				sendWriter.flush();
			}
			
			sendWriter.close();
			tempBuffer.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setSocket(Socket _socket)
	{
		socket = _socket;
	}	
}
