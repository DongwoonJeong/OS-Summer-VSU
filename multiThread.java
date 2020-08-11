/*
CS4345
Summer 2020
Assignment 1
Dongwoon Jeong
*/
import java.util.*;
public class multiThread implements Runnable{
	
	      private int[][] A, B;
         private int i, j, k;
	      public static int[][] P;
         
	      public multiThread(int[][] A, int[][] B, int[][] P, int i, int j, int k){
	         this.A = A;
	         this.B = B;
	         this.P = P;
	         this.i = i;
	         this.j = j;
	         this.k = k;
	      }
	   
	      @Override
	      public void run(){
         try{      
	         for(int x = 0; x < k; x++) {
	            P[i][j] += A[i][x] * B[j][x];
	         }
            Thread.sleep(100);
            int bn = i +1;
            int an = j +1;
            System.out.println("");
	         System.out.println("Thread <" + bn + "," + an + ">" + "starts calculation");
            System.out.println("Thread <" + bn + "," + an + ">" + "returns"+ " " + P[i][j] );
           }catch(InterruptedException e){
            System.out.println(e);
            }
        System.out.println("\n"+"Matrix P");
                      for (int in = 0; in < P.length; in++) {
                         System.out.println();
                         for (int jn =0; jn <P[in].length; jn++){
                             System.out.print(P[in][jn] + "  ");
                             
                               }
                              }System.out.println(" ");

           } 

}