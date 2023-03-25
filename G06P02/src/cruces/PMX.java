package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.AlgoritmoGenetico;

public class PMX extends Cruce {

	public static void pmx(AlgoritmoGenetico alg) {
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
				
				//Se comprueba si los valores de fuera se repiten con los de dentro
				for (int j = 0; j < padre1.length; j++) {
		
					if (j < Math.min(pC1, pC2) || j > Math.max(pC1, pC2)) {
		                
						//Rellena hijo1
		                if (!contieneSub(hijo1, padre1[j], pC1, pC2)) {
		                    hijo1[j] = padre1[j];
		                }
		                else {
		                	int ind = getIndex(padre2, padre1[j],pC1, pC2);
		                	while (contieneSub(hijo1, padre1[ind], pC1, pC2)) {//Puede que el valor obtenido vuelva a causar conflicto
		                		ind = getIndex(padre2, padre1[ind] ,pC1, pC2);
		                	}
		                	if (ind != -1) 
		                		hijo1[j] = padre1[ind];
		                }
		                
		                //Rellena hijo2
		                if (!contieneSub(hijo2, padre2[j], pC1, pC2)) {
		                    hijo2[j] = padre2[j];
		                }
		                else {
		                	int ind = getIndex(padre1, padre2[j],pC1, pC2);
		                	while (contieneSub(hijo2, padre2[ind], pC1, pC2)) {//Puede que el valor obtenido vuelva a causar conflicto
		                		ind = getIndex(padre1, padre2[ind] ,pC1, pC2);
		                	}
		                	if (ind != -1) 
		                		hijo2[j] = padre2[ind];
		                }
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
