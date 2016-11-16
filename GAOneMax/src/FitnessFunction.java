/*
 # GAOneMax - Tool to solve the MaxOne problem
 # 
 # Created by Julian Camilo Castañeda Alonso and David Estteban Prieto Mendoza
 #
 # This file is part of GAOneMax
 #
 */

/**
 * TODO
 * @author Julian Camilo Castañeda Alonso - David Estteban Prieto Mendoza
 */

public class FitnessFunction {
	
	/**
	 * Class constructor without parameters.
	 */
	public FitnessFunction() {
	
	}
	
	/**
	 * This method calculate fitness
	 * @param fenotipo
	 * @return fitness
	 */
	public int calculateFitness(int[] fenotipo) {
		
		int fitness = 0;
		
		// Counts the occurrences of ones that has the genotype of the individual
		for (int i = 0; i < fenotipo.length; i++) {
			
			fitness += fenotipo[i];
			
		}
		
		return fitness;
	}

}