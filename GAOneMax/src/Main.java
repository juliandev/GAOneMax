/*
 # GAOneMax - Tool to solve the MaxOne problem
 # 
 # Created by Julian Camilo Castañeda Alonso and David Estteban Prieto Mendoza
 #
 # This file is part of GAOneMax
 #
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	public static void main(String[] args) {
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int populationSize = 0;
		int iterations = 0;
		String selectionMethod = ""; 
		String replacementMethod = ""; 
		
		try {
			
			bw.write("||---------- Welcome to GA-OneMax ----------|| \n\nAlgorithm parameters:\n");
			bw.flush();
			bw.write("\n-Population Size: ");
			bw.flush();
			populationSize = Integer.parseInt(br.readLine());
			bw.write("\n-Selection method: ");
			bw.flush();
			selectionMethod = br.readLine();
			bw.write("\n-Replacement method: ");
			bw.flush();
			replacementMethod = br.readLine();
			bw.write("\n-Number of iterations: ");
			bw.flush();
			iterations = Integer.parseInt(br.readLine());
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}    	
		
		if (populationSize != 102 && populationSize != 512) {
			
			try {
		
				bw.write("\nNo valid population size. Please insert a valid size (102 or 512).");
				bw.flush();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			System.exit(0);
		}
		
		if (!selectionMethod.equals("uniform") && !selectionMethod.equals("elitism")) {
			
			try {
				
				bw.write("\nInvalid parameter. Please enter a valid parameter (uniform or elitism).");
				bw.flush();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			System.exit(0);
		}
		
		if (!replacementMethod.equals("tournament") && !replacementMethod.equals("roulette")) {
			
			try {
				
				bw.write("\nInvalid parameter. Please enter a valid parameter (tournament or roulette).");
				bw.flush();
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
			}
			
			System.exit(0);
		}
		
		try {
			
			Population p = new Population(populationSize, iterations, selectionMethod, replacementMethod);
			String bestIndividual = "\n" + p.getBest();
			bw.write(bestIndividual);
			bw.flush();
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

}