package cruces;

import java.util.ArrayList;
import java.util.Arrays;
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
		        if (!sonIguales(padre1,padre2)) {	
		        	int[] hijo1 = new int[padre1.length];
			        int[] hijo2 = new int[padre1.length];
			        //Se generan 2 puntos de corte
			        Random rand1 = new Random();
					int[] is = new int[2];
					for(int j = 0; j < is.length; j++)
						is[j] = Math.abs(rand1.nextInt())%(padre1.length);
					Arrays.sort(is);
					
					//Se copia la subcadena
					for (int j =is[0]; j <= is[1]; j++) {
		                hijo1[j] = padre2[j];
		                hijo2[j] = padre1[j];
					}
					
					//Se comprueba si los valores de fuera se repiten con los de dentro
					for (int j = 0; j < padre1.length; j++) {
			
						if (j < is[0] || j > is[1]) {
			                
							//Rellena hijo1
			                if (!contieneSub(hijo1, padre1[j], is[0], is[1])) {
			                    hijo1[j] = padre1[j];
			                }
			                else {
			                	int ind = getIndex(padre2, padre1[j],is[0], is[1]);
			                	while (contieneSub(hijo1, padre1[ind], is[0], is[1])) {//Puede que el valor obtenido vuelva a causar conflicto
			                		ind = getIndex(padre2, padre1[ind] ,is[0], is[1]);
			                	}
			                	if (ind != -1) 
			                		hijo1[j] = padre1[ind];
			                }
			                
			                //Rellena hijo2
			                if (!contieneSub(hijo2, padre2[j],is[0], is[1])) {
			                    hijo2[j] = padre2[j];
			                }
			                else {
			                	int ind = getIndex(padre1, padre2[j],is[0], is[1]);
			                	while (contieneSub(hijo2, padre2[ind], is[0], is[1])) {//Puede que el valor obtenido vuelva a causar conflicto
			                		ind = getIndex(padre1, padre2[ind] ,is[0], is[1]);
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
	
}
