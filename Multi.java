/*
CS4345
Summer 2020
Assignment 1
Dongwoon Jeong
*/

import java.io.*;
import java.util.*;

public class Multi{
   public static void main(String[] args) throws IOException{

	if (args.length == 0){
		System.out.println("Please type input file to read and try again.");
		System.exit(0);
	}else{
	File file = new File(args[0]);
	if (file.exists()){
      try{
      int m, n, k, x;
      int[][] A, B, P;
    	 Scanner sc = new Scanner(new File(args[0]));
         m = sc.nextInt();
         n = sc.nextInt();
         k = sc.nextInt();
         x = sc.nextInt();
         A = new int[m][n];
         B = new int[k][x];
         P = new int[m][k];
        
         if (n == x){
            System.out.println("Dot-product available. Calculation performing");
            System.out.println(" ");
            
            System.out.println("Matrix A:" + m + "x"+n+" Matrix");
            for (int i = 0; i < m; i++){
            System.out.println();
            for (int j = 0; j < n; j++) {
               A[i][j] = sc.nextInt();
               System.out.print(A[i][j] + "  ");
            }
         }
         System.out.println("\n");
         System.out.println("Matrix B: " + k + "x"+x+" Matrix");
         for (int i = 0; i < k; i++) {
         System.out.println();
            for (int j = 0; j < x; j++) {
               B[i][j] = sc.nextInt();
               System.out.print(B[i][j] + "  ");
            }}
            System.out.println("\n");
         System.out.println("Matrix P: " + m + "x"+k+" Matrix");

            for (int i = 0; i < m; i++) {
         System.out.println();
            for (int j = 0; j < k; j++) {
               System.out.print(P[i][j] + "  ");
            }
         }System.out.println(" ");
         }
         else{
            System.out.println("Dot-product cannot be performed. Please check the matrics again.");
            System.exit(0);
         }
         
         for (int i = 0; i < m; ++i) {
            for (int j = 0; j < k; ++j) {
               Thread thread = new Thread(new multiThread(A, B, P, i, j, n));
               thread.start();
               try{
               thread.join();
               }catch(Exception e){
               System.out.print("Threads could not join.");
             }
            }
           }
      }catch (IOException e){
         e.printStackTrace();
      }
}
}
}}

