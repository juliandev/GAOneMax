/*
 # GAOneMax - Tool to solve the MaxOne problem
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

public class Replacement {
	
	Random rnd = new Random();
	
	/**
	 * Class constructor without parameters.
	 */	
	
	public Replacement() {
	
	}
	
	/**
	 * This method implements traditional roulette strategy for two individuals
	 * @param player_1
	 * @param player_2
	 * @return
	 */
	
	public Individual roulette(Individual player_1, Individual player_2) {
		
		double totalFitness = player_1.getFitness() + player_2.getFitness();
		
		double point = player_1.getFitness() / totalFitness;
		
		return rnd.nextDouble() < point ? player_1 : player_2;
		
	}
	
	/**
	 * This method implements traditional roulette strategy for four individuals
	 * @param player_1
	 * @param player_2
	 * @return
	 */
	
	public Individual[] roulette(Individual player_1, Individual player_2, Individual player_3, Individual player_4) {
		
		Individual[] winners = new Individual[2];
		boolean select = true;
		
		double totalFitness = player_1.getFitness() + player_2.getFitness() + player_3.getFitness() + player_4.getFitness();
		double point_player_1 = player_1.getFitness() / totalFitness;		
		double point_player_2 = player_2.getFitness() / totalFitness;
		double point_player_3 = player_3.getFitness() / totalFitness;
		double point_player_4 = player_4.getFitness() / totalFitness;
		
		double random_point = rnd.nextDouble();
		double random_point_1 = rnd.nextDouble();
		
		// Select the first individual
		// The selected individual is assigned the value of -1 in his fitness so that 
		// he is not selected again when choosing the second individual
		
		while (select) {
			if (random_point < point_player_1) {
				winners[0] = player_1;
				point_player_1 = -1;
				select = false;
			}
			else if (random_point < point_player_2) {
				winners[0] = player_2;
				point_player_2 = -1;
				select = false;
			}
			else if (random_point < point_player_3) {
				winners[0] = player_3;
				point_player_3 = -1;
				select = false;
			}
			else if (random_point < point_player_4) {
				winners[0] = player_4;
				point_player_4 = -1;
				select = false;
			}
			random_point = rnd.nextDouble();
		}
		
		// Select the second individual
		
		select = true;
		
		while (select) {
			if (random_point_1 < point_player_1) {
				winners[1] = player_1;
				select = false;
			}
			else if (random_point_1 < point_player_2) {
				winners[1] = player_2;
				select = false;
			}
			else if (random_point_1 < point_player_3) {
				winners[1] = player_3;
				select = false;
			}
			else if (random_point_1 < point_player_4) {
				winners[1] = player_4;
				select = false;
			}
			random_point_1 = rnd.nextDouble();
		}
		
		return winners;

	}
	
	public Individual[] steadyState(Individual father_1, Individual father_2, Individual son_1, Individual son_2) {
		
		Individual[] winners = new Individual[2];
		
		if (father_1.getFitness() < father_2.getFitness()) {
			if (son_1.getFitness() < son_2.getFitness()) {
				winners[0] = this.roulette(father_2, son_2); // Best father - Best son
				winners[1] = this.roulette(father_1, son_1); // Worst father - Worst son
			}
			else {
				winners[0] = this.roulette(father_2, son_1); // Best father - Best son
				winners[1] = this.roulette(father_1, son_2); // Worst father - Worst son
			}
		}
		else {
			if (son_1.getFitness() < son_2.getFitness()) {
				winners[0] = this.roulette(father_1, son_2); // Best father - Best son
				winners[1] = this.roulette(father_2, son_1); // Worst father - Worst son				 
			}
			else {
				winners[0] = this.roulette(father_1, son_1); // Best father - Best son
				winners[1] = this.roulette(father_2, son_2); // Worst father - Worst son
			}
		}
		
		return winners;
		
	}

}
