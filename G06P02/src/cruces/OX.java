package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.AlgoritmoGenetico;

public class OX extends Cruce {
	
	public static void ox(AlgoritmoGenetico alg) {
		Random rand = new Random();
		List<Integer> indicesCruce = new ArrayList<>();
		
		seleccionaPadres(alg,indicesCruce, rand);
		
		if (indicesCruce.size() > 0) {
	        for (int i = 0; i < indicesCruce.size()-1; i+=2) {
	        	int[] padre1 = alg.getPoblacion()[indicesCruce.get(i)].getCrom();
	        	int[] padre2 = alg.getPoblacion()[indicesCruce.get(i+1)].getCrom();
	        	int[] hijo1 = new int[padre1.length];
		        int[] hijo2 = new int[padre1.length];
				Random rand1 = new Random();
				int pC1 = Math.abs(rand1.nextInt())%(padre1.length);
				Random rand2 = new Random();
				int pC2 = Math.abs(rand2.nextInt())%(padre1.length);
				
				//Se copia la subcadena
				for (int j = Math.min(pC1, pC2); j <= Math.max(pC1, pC2); j++) {
	                hijo1[j] = padre2[j];
	                hijo2[j] = padre1[j];
				}
				//Recorrido circular desde el segundo punto de corte
				int indH = Math.max(pC1, pC2);
				for(int j = Math.max(pC1, pC2); indH != Math.min(pC1, pC2); j = (j+1)%padre1.length) {
					if(!contieneSub(hijo1, padre1[j],pC1, pC2)) {
						hijo1[indH]= padre1[j];
						indH = (indH+1)%padre1.length;
					}
					
				}
				
				indH = Math.max(pC1, pC2);
				for(int j = Math.max(pC1, pC2); indH != Math.min(pC1, pC2); j = (j+1)%padre1.length) {
					if(!contieneSub(hijo2, padre2[j],pC1, pC2)) {
						hijo2[indH]= padre2[j];
						indH = (indH+1)%padre1.length;
					}
					
				}
				//Se sustituyen los padres
				for (int j = 0; j < padre1.length; j++) {
					alg.getPoblacion()[indicesCruce.get(i)].setCrom(j, hijo1[j]);
					alg.getPoblacion()[indicesCruce.get(i+1)].setCrom(j, hijo2[j]);
				}	
	        }
		}
	}
}
