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

public class Individual {
	
	// Representation of individual
	private int[] phenotype;
	// Representation genetic of individual
	private int[] genotype;
	// Individual performance measure
	private int fitness;
	
	/**
	 * Constructor of the class that requires the genetic representation of new individual	
	 * @param fenotipo
	 * @param fitness
	 */
	public Individual(int[] fenotipo, int fitness) {
		this.phenotype = fenotipo; 
		this.genotype = fenotipo; 
		this.fitness = fitness;
	}

	/**
	 * @return the fenotipo
	 */
	public int[] getPhenotype() {
		return phenotype;
	}

	/**
	 * @param fenotipo the fenotipo to set
	 */
	public void setPhenotype(int[] fenotipo) {
		this.phenotype = fenotipo;
	}

	/**
	 * @return the genotipo
	 */
	public int[] getGenotype() {
		return genotype;
	}

	/**
	 * @param genotipo the genotipo to set
	 */
	public void setGenotype(int[] genotipo) {
		this.genotype = genotipo;
	}

	/**
	 * @return the fitness
	 */
	public int getFitness() {
		return fitness;
	}

	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}
	
	/**
	 * @return the individual as string
	 */
	public String getGenotypeString() {
		String str = "";
		
		for (int i = 0; i < genotype.length; i++) {
		
			str += genotype[i] + " ";
			
		}
		
		return str;
		
	}
	
}