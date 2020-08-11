/**
* @CourseNumber  CS 4345
* @Semester  Summer 2020
* @version  Assignment 4
* @Author  Hoyong Lee, Dongwoon Jeong, Tianna Vazquez
*/

import java.util.concurrent.*;
import java.util.*;

class mountain extends Thread {
	Semaphore sem;
	public int leftOrright;
	public mountain(Semaphore sem, int leftOrright) {
		this.sem = sem;
		this.leftOrright = leftOrright;
	}
	@Override
	    public void run() {
		// Start thread if the car number is even. 
		if(leftOrright % 2 == 0) {
			try {
				//car getting in the tunnel.
				System.out.println("Left-bound Car " + leftOrright + " wants to enter the tunnel.");
				// acquiring the lock 
				sem.acquire();
				System.out.println("Left-bound Car " + leftOrright + " is in the tunnel.");
				Thread.sleep(2000);
				// thread release the lock
			}
			catch (InterruptedException exc) {
				System.out.println(exc);
			}
			// exiting. 
			System.out.println("Left-bound Car "+ leftOrright + " is exiting the tunnel.");
			sem.release();
		} else {
			try {
				//car getting in the tunnel. 
				System.out.println("Right-bound Car " + leftOrright + " wants to enter the tunnel.");
				//acquiring the lock 
				sem.acquire();
				System.out.println("Right-bound Car " + leftOrright + " is in the tunnel.");
				Thread.sleep(2000);
			}
			catch (InterruptedException exc) {
				System.out.println("Something went wrong..!");
			}
			// exiting. 
			System.out.println("Right-bound Car "+ leftOrright + " is exiting the tunnel.");
			sem.release();
		}
	}
}
//driver 
public class HL_DJ_TV {
	public static void main(String args[]) throws InterruptedException {
		// creating a Semaphore object 
		Semaphore sem = new Semaphore(1);
		// creating two threads with Left bounds cars and Right boudns cars 
		boolean temp = true;
		int rb = 1;
		int lb =0;
		while(temp) {
			// stating threads.
			mountain mt1 = new mountain(sem, rb);
			mountain mt2 = new mountain(sem, lb);
			mt1.start();
			mt2.start();
			mt1.join();
			mt2.join();
			rb=rb+2;
			lb= lb+2;
		}
	}
}