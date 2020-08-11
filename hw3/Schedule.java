/*
Dongwoon Jeong
Summer 2020
Operating System
Assignment 3
*/
import java.util.*;
public class Schedule {
	public static void main(String[]args) {
		ArrayList<Process> process = new ArrayList<Process>();
		int count = 0;
		int processId = 0;
		int totalProcess =10;
		int priorityRange = 10;
		int t = 0;
		int quantum = 20;
		int RRtotalwt = 0;
		int RRaverageWaiting = 0;
		int PaverageWaiting;
		Random rand = new Random();
		for (int i = 0; i < 6; i++) {
			boolean repeat = false;
			Process proc = new Process();
			while(!repeat) {
				processId=rand.nextInt(10)+1;
				if(i == 0)
				       break;
				for (int j=0; j<i; j++) {
					if(processId==process.get(j).id) {
						repeat=false;
						break;
					} else
					            repeat=true;
				}
			}
			proc.id = processId;
			proc.priority = rand.nextInt(priorityRange) + 1;
			proc.burst = rand.nextInt(80) + 20;
			process.add(proc);
			count++;
		}
		System.out.println("Process ID  |  Priority  |  Burst-length");
		for (int i=0; i<process.size(); i++) {
			System.out.println("\t\t" + process.get(i).id+"\t\t\t\t"+process.get(i).priority+"\t\t\t\t"+process.get(i).burst);
		}
		Scanner scan = new Scanner(System.in);
		System.out.println("Wish to enter new process? (yes/no)");
		String answer = scan.nextLine();
		Process proc = new Process();
		if(answer.equals("yes")) {
			System.out.println("what is the process ID? Type the number between 1-10 that is not existing on the list already.");
			int newID = scan.nextInt();
			for (int i = 0; i < process.size(); i++) {
				while(newID == process.get(i).id) {
					if(newID == process.get(i).id) {
						System.out.println("Adding failed.");
						System.out.println("Process ID already exist. Type process ID that does not exist already. Try again.");
						newID = scan.nextInt();
					}
					if(newID >10) {
						System.out.println("Adding failed.");
						System.out.println("Please type the process ID between 1-10");
						newID = scan.nextInt();
					}
				}
			}
			proc.id=newID;
			System.out.println("Enter the burst time of the process range between 20 - 100");
			int newBurst = scan.nextInt();
			while((newBurst < 20) || (newBurst > 100)) {
				if((newBurst < 20) || (newBurst > 100)) {
					System.out.println("Adding failed.");
					System.out.println("The burst time shoule be between 20 - 100. Enter the burst time between 20 - 100");
					newBurst = scan.nextInt();
				}
			}
			proc.burst = newBurst;
			System.out.println("Enter the priority of the process range between 1 - 10");
			int newPro = scan.nextInt();
			if(newPro > 10) {
				while(newPro > 10) {
					System.out.println("Adding failed.");
					System.out.println("Priority cannot be greater then 10. Enter the number between 1 - 10");
					newPro = scan.nextInt();
				}
			}
			proc.priority= newPro;
			process.add(proc);
			System.out.println("New process added by user");
			System.out.println("Process ID  |  Priority  |  Burst-length");
			for (int q=0; q<process.size(); q++) {
				System.out.println("\t\t" + process.get(q).id+"\t\t\t\t"+process.get(q).priority+"\t\t\t\t"+process.get(q).burst);
			}
		} else {
			System.out.println("Calculation performing without user input.");
		}
		ArrayList<Process> SJFprocess = new ArrayList<Process>(process);
		ArrayList<Process> SJFpro = new ArrayList<Process>(process);
		ArrayList<Process> Pprocess = new ArrayList<Process>(process);
		ArrayList<Process> Ppro = new ArrayList<Process>(process);
		ArrayList<Process> RRprocess = new ArrayList<Process>(process);
		ArrayList<Process> RRpro = new ArrayList<Process>(process);
		//Keeps affecting original arraylist. Roundrobin method affects burst time to go 0 Also making waiting time same for each calculation.
		/* ArrayList<Process> SJFprocess = new ArrayList<Process>();
      for(Process o : process){
      SJFprocess.add((Process)o.clone());
      }
      ArrayList<Process> SJFpro = new ArrayList<Process>();
      for(Process ob : SJFprocess){
      SJFpro.add((Process)ob.clone());
      }
      ArrayList<Process> Pprocess = new ArrayList<Process>();
      for(Process obj : process){
      Pprocess.add((Process)obj.clone());
      }
      ArrayList<Process> Ppro = new ArrayList<Process>();
      for(Ppro obje : Pprocess){
      Ppro.add((Process)obje.clone());
      }
      ArrayList<Process> RRprocess = new ArrayList<Process>();
      for(RRprocess objec : process){
      RRprocess.add((Process)objec.clone());
      }
      ArrayList<Process> RRpro = new ArrayList<Process>();
      for(RRpro object : RRprocess){
      RRpro.add((Process)object.clone());
      }*/
		//Tried to clone using clone interface but somehow it throws error.
		//SJF
		int max=0;
		int in=0;
		int time=0;
		while(SJFpro.size()!=0) {
			max=100;
			for (int i=0; i < SJFpro.size(); i++) {
				if(SJFpro.get(i).burst < max) {
					max=SJFpro.get(i).burst;
					in=i;
				} else if(SJFpro.get(i).burst == max) {
					if(SJFpro.get(i).burst < SJFpro.get(in).burst) {
						in=i;
					}
				}
			}
			SJFpro.get(in).tw=time-0;
			time +=SJFpro.get(in).burst;
			SJFpro.remove(in);
		}
		int SJFaverageWaiting;
		int totalwt=0;
		for (int i=0;i<SJFprocess.size();i++) {
			totalwt = totalwt + SJFprocess.get(i).tw;
		}
		SJFaverageWaiting= totalwt / SJFprocess.size();
		//Priority
		time=0;
		in=0;
		while(Pprocess.size()!=0) {
			max=11;
			for (int i=0;i<Pprocess.size();i++) {
				if(Pprocess.get(i).priority<max) {
					max=Pprocess.get(i).priority;
					in=i;
				} else if(Pprocess.get(i).priority==max) {
					if(Pprocess.get(i).priority < Pprocess.get(in).priority) {
						in=i;
					}
				}
			}
			Pprocess.get(in).tw=time-0;
			time += Pprocess.get(in).burst;
			Pprocess.remove(in);
		}
		int Ptotalwt = 0;
		for (int i=0;i<Ppro.size();i++) {
			Ptotalwt=Ptotalwt+Ppro.get(i).tw;
		}
		PaverageWaiting = Ptotalwt / Ppro.size();
		//RR
		while(RRpro.size()!=0) {
			boolean done = true;
			for (int i=0; i < RRprocess.size();i++) {
				if (RRprocess.get(i).burst > 0) {
					done = false;
					if(RRprocess.get(i).burst > quantum) {
						t += quantum;
						RRprocess.get(i).setBurst(RRprocess.get(i).burst - quantum);
					} else {
						t = t + RRprocess.get(i).burst;
						RRprocess.get(i).setWaiting(t - RRprocess.get(i).burst);
						RRprocess.get(i).setBurst(0);
					}
				}
			}
			if (done == true)
			                  break;
		}
		for (int i = 0; i <RRpro.size(); i++) {
			RRtotalwt = RRtotalwt + RRpro.get(i).wt;
		}
		RRaverageWaiting = RRtotalwt / RRpro.size();
		System.out.println(" Process ID |  Priority  | Burst-length | Scheduling algorithm | Waiting time");
		for (int i=0;i<SJFprocess.size();i++) {
			System.out.println("\t\t" + SJFprocess.get(i).id +"\t\t\t\t"+ SJFprocess.get(i).priority +"\t\t\t\t\t"+SJFprocess.get(i).burst +"\t\t\t\t\t SJF \t\t\t\t\t"+ SJFprocess.get(i).tw);
		}
		for (int i=0;i<Ppro.size();i++) {
			System.out.println("\t\t" + Ppro.get(i).id +"\t\t\t\t"+ Ppro.get(i).priority +"\t\t\t\t\t"+ Ppro.get(i).burst +"\t\t\t\t Priority \t\t\t\t" + Ppro.get(i).tw);
		}
		for (int i=0;i<RRpro.size();i++) {
			System.out.println("\t\t" +RRpro.get(i).id +"\t\t\t\t"+ RRpro.get(i).priority +"\t\t\t\t\t"+ RRpro.get(i).burst +"\t\t\t\t Round Robin \t\t\t" + RRpro.get(i).tw);
		}
      
      
		System.out.println("");
		System.out.println("Non-preemptive SJF average: "+ SJFaverageWaiting);
		System.out.println("Non-preemptive priority average: "+ PaverageWaiting);
		System.out.println("Round Robin average: "+ RRaverageWaiting);
      int[] sort = {SJFaverageWaiting, PaverageWaiting, RRaverageWaiting};
      Arrays.sort(sort);
      System.out.println("Lowest to highest:"+ Arrays.toString(sort));
	}
}