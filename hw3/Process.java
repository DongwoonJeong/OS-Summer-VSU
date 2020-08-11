/*
Dongwoon Jeong
Summer 2020
Operating System
Assignment 3
*/
public class Process implements Cloneable{
	int id;
   int priority;
   int burst;
   int wt;
   int tw;
   public void setId(int id){
		this.id = id;
	}
   public void setBurst(int burst){
		this.burst = burst;
	}
   public void setPriority(int priority){
		this.priority = priority;
	}
   public void setWaiting(int wt){
      this.wt = wt;
   }
   public void setTotalwaiting(int tw){
      this.tw = tw;
   }
   public int getid(){
		return id;
	}
   public int getBurst(){
		return burst;
	}
   public int getpriority(){
		return priority;
	}
   public int getTotalwaiting(){
        return tw;
   }
   public int getWaiting(){
        return wt;
   }
   public Process clone() {
        try {
            return (Process) super.clone();
        } catch (CloneNotSupportedException e) {
            System.out.println("Error");
            throw new RuntimeException();
        }
    }
}