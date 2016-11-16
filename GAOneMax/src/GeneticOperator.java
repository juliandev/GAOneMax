/*
 # GAOneMax - Tool to solve the OneMax problem
 # 
 # Created by Julian Camilo Castañeda Alonso and David Estteban Prieto Mendoza
 #
 # This file is part of GAOneMax
 #
 */

import java.util.Random;

/**
 * TODO
 * @author Julian Camilo Castañeda Alonso - David Estteban Prieto Mendoza
 */

public class GeneticOperator {
	
	/**
	 * Class constructor without parameters.
	 */	
	public GeneticOperator() {
	
	}
	
	/**
	 * This method implements traditional mutation strategy
	 * @param parent_1
	 * @param parent_2
	 * @return sons in array
	 */
	public int[][] mutation(int[] parent_1, int[] parent_2) {
		Random rnd = new Random();
		
		// Array of son's chromosomes
		int[][] sons = new int[2][parent_1.length];
		
		int random_1 = rnd.nextInt(parent_1.length);
		int random_2 = rnd.nextInt(parent_2.length);
		
		while (random_1 == random_2) {
			
			random_2 = rnd.nextInt(parent_2.length);
			
		}
		
		sons[0] = parent_1.clone();
		sons[1] = parent_2.clone();
		
		if (sons[0][random_1] == 1) {
			
			sons[0][random_1] = 0;
			
		}
		else {
			
			sons[0][random_1] = 1;
			
		}
		
		if (sons[1][random_2] == 1) {
			
			sons[1][random_2] = 0;
			
		}
		else {
			
			sons[1][random_2] = 1;
			
		}
		
		return sons;
		
	}
	
	/**
	 * This method implements traditional one point crossover strategy
	 * @param parent_1
	 * @param parent_2
	 * @return sons in array
	 */
	public int[][] crossover(int[] parent_1, int[] parent_2) {
		Random rnd = new Random();
		int crosspoint = rnd.nextInt(parent_1.length);
		
		// Array of son's chromosomes
		int[][] sons = new int[2][parent_1.length];
		
		// Array of first and second son chromosomes
		int[] son_1 = parent_1.clone();
		int[] son_2 = parent_2.clone();
		
		for (int i = crosspoint; i < son_1.length; i++) {
			
			son_1[i] = parent_2[i];
			
			son_2[i] = parent_1[i];
			
		}
		
		sons[0] = son_1;
		sons[1] = son_2;
		
		return sons;
		
	}

}
