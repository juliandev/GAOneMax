/*
 # GAOneMax - Tool to solve the MaxOne problem
 # 
 # Created by Julian Camilo Castañeda Alonso and David Estteban Prieto Mendoza
 #
 # This file is part of GAOneMax
 #
 */

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Random;
import java.util.Vector;

public class Population {
	
	private int individualSize = 10;
	private int populationSize = 0;
	private int iterations = 0;
	private String selectionMethod = "";
	private String replacementMethod = "";
	private FitnessFunction fitnessFuction = null;
	private GeneticOperator geneticOperator = null;
	private Replacement replacementOperator = null;
	private Vector<Individual> population = null;
	private BufferedWriter bw = null;
	private Random rnd = null;
	
	public Population(int populationSize, int iterations, String selectionMethod, String replacementMethod) {
		
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		try {
			bw.write("\n\nInitializing algorithm...\n");
			bw.flush();			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.populationSize = populationSize;
		this.iterations = iterations;
		this.selectionMethod = selectionMethod;
		this.replacementMethod = replacementMethod;
		this.fitnessFuction = new FitnessFunction();
		this.geneticOperator = new GeneticOperator();
		this.replacementOperator= new Replacement();
		this.population = new Vector<>();
		this.rnd = new Random();
		
		try {
			bw.write("Building initial population...\n");
			bw.flush();			
			this.initialPopulation();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			bw.write("Start Genetic Algorithm...\n\n");
			bw.flush();
			this.generations();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void initialPopulation() {
		int fitness = 0;
		for (int i = 0; i < populationSize; i++) {
			int[] individualGenotipe = new int[individualSize];
			for (int j = 0; j < individualSize; j++) {
				individualGenotipe[j] = (int) Math.rint(Math.random());
			}
			fitness = fitnessFuction.calculateFitness(individualGenotipe);
			this.population.add(new Individual(individualGenotipe, fitness));
		}
	}
	
	private void generations() {		
		
		int tempFitness;
		Individual father_1 = null;
		Individual father_2 = null;
		Individual[] sons = new Individual[2];
		int[][] sonsGenotypes  = new int[individualSize][2];
		Individual[] newGeneration = new Individual[2];
		Vector<Individual> nextGeneration = null;
		Vector<Individual> bestPopulation = null;
		
		for (int i = 0; i < this.iterations; i++) {
			
			System.gc();
			
			System.out.println("Iteration: " + i);
			
			nextGeneration = new Vector<>();
			bestPopulation = new Vector<>();
			bestPopulation = sortPopulation(this.population);
			
			for (int j = 0; j < (populationSize / 2); j++) {
				
				if (selectionMethod.equals("uniform")) {
					
					father_1 = uniformSelection();
					father_2 = uniformSelection();
					
				}
				else if (selectionMethod.equals("elitism")) {
					
					father_1 = elitism(bestPopulation);
					father_2 = elitism(bestPopulation);
					
				}
				
				if (rnd.nextDouble() < 0.3) {
					
					sonsGenotypes = this.geneticOperator.mutation(father_1.getGenotype(), father_2.getGenotype());
					
				}
				else {
					
					sonsGenotypes = this.geneticOperator.crossover(father_1.getGenotype(), father_2.getGenotype());
					
				}
				
				tempFitness = this.fitnessFuction.calculateFitness(sonsGenotypes[0]);
				sons[0] = new Individual(sonsGenotypes[0], tempFitness);
				
				tempFitness = this.fitnessFuction.calculateFitness(sonsGenotypes[1]);
				sons[1] = new Individual(sonsGenotypes[1], tempFitness);
				
				if (replacementMethod.equals("tournament")) {
				
					newGeneration = this.replacementOperator.steadyState(father_1, father_2, sons[0], sons[1]);
					
				}
				else if(replacementMethod.equals("roulette")) {
				
					newGeneration = this.replacementOperator.roulette(father_1, father_2, sons[0], sons[1]);
					
				}
				
				nextGeneration.add(newGeneration[0]);
				nextGeneration.add(newGeneration[1]);
			}
			
			this.population = nextGeneration;
			
		}
		
	}
	
	
	private Individual uniformSelection() {
		
		int position = rnd.nextInt(populationSize);
		
		return this.population.get(position);
		
	}
	
	private Individual elitism(Vector<Individual> bestPopulation) {
		
		int percentPopulation = (bestPopulation.size() * 10) / 100;
		Vector<Individual>selectedPopulation = new Vector<>();
		selectedPopulation.addAll(bestPopulation.subList(0, percentPopulation));
		int position = rnd.nextInt(selectedPopulation.size());
		
		return selectedPopulation.get(position);
		
	}
	
	private Vector<Individual> sortPopulation(Vector<Individual> population) {
		
		if (population.size() <= 1) {
			
			return population;
			
		}
		else {
			
			Vector<Individual> left = new Vector<>();
			Vector<Individual> right = new Vector<>();
			Vector<Individual> left_vector = new Vector<>();
			Vector<Individual> right_vector = new Vector<>();
			Vector<Individual> populationSort = new Vector<>();
			
			int mitad = population.size() / 2;
			int x = 0, y = 0;
			
			left.addAll(population.subList(0, mitad));
			right.addAll(population.subList(mitad, population.size()));
						
			left_vector = sortPopulation(left);
			right_vector = sortPopulation(right);
			
			while (x < left_vector.size() && y < right_vector.size()) {
				
				if (left_vector.get(x).getFitness() > right_vector.get(y).getFitness()) {
					
					populationSort.add(left_vector.get(x));
					x += 1;
					
				}
				else {
					
					populationSort.add(right_vector.get(y));
					y += 1;
					
				}
				
			}
			
			populationSort.addAll(left_vector.subList(x, left_vector.size()));
			populationSort.addAll(right_vector.subList(y, right_vector.size()));
			
			return populationSort;
			
		}
		
	}
	
	public String getBest() {
		
		int index = 0;
		
		int fitness = this.population.get(0).getFitness();
		
		for (int i = 0; i < this.populationSize; i++) {
			
			if (this.population.get(i).getFitness() > fitness) {

				index = i;
				
				fitness = this.population.get(i).getFitness();
						
			}
			
		}
		
		String best = "Genotype of the best individual: " + this.population.get(index).getGenotypeString();
		best += "\nFitness: " + fitness;
		
		return best;
		
	}

}