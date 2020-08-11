/**
* @CourseNumber  CS 4345
* @Semester  Summer 2020
* @version  Assignment 2
* @Author  Hoyong Lee, DongWoon Jeong, Tianna Vazquez
*/
package server;

import java.io.*;
import java.net.Socket;

public class ClientManager extends Thread{

	private Socket socket;
	private String ID;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			BufferedReader tmpbuffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			String text;
			
			while(true)
			{
				text = tmpbuffer.readLine();
				
				if(text == null)
				{
					System.out.println(ID + " has left the group chat.");
					for(int i = 0; i < ServerDriver.outputList.size(); ++i)
					{
						ServerDriver.outputList.get(i).println(ID + " has left the group chat.");
						ServerDriver.outputList.get(i).flush();
					}
					break;
				}
				
				String[] split = text.split("LeeJeongVazquez123");
				if(split.length == 2 && split[0].equals("ID"))
				{
					ID = split[1];
					System.out.println(ID + " has joined the group.");
					for(int i = 0; i < ServerDriver.outputList.size(); ++i)
					{
						ServerDriver.outputList.get(i).println(ID + " has joined the group.");
						ServerDriver.outputList.get(i).flush();
					}
					continue;
				}
				
				for(int i = 0; i < ServerDriver.outputList.size(); ++i)
				{
					ServerDriver.outputList.get(i).println(ID + "> "+ text);
					ServerDriver.outputList.get(i).flush();
				}
			}
			
			ServerDriver.outputList.remove(new PrintWriter(socket.getOutputStream()));
			socket.close();
			
		} catch (IOException e) {
			//When user exit without typing exit, this will print user left the chat room.
			System.out.println(ID + " has left the group chat.");
		}
	}
	
	public void setSocket(Socket _socket)
	{
		socket = _socket;
	}
}
