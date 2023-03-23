package main;

import java.util.Comparator;

import Funciones.Individuo;

public class FitnessComparatorMin implements Comparator<Individuo> {
	@Override
	public int compare(Individuo o1, Individuo o2) {
		
		return Double.compare(o1.getFitness(), o2.getFitness());
	}
}
