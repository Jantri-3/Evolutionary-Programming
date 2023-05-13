package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.AlgoritmoGenetico;

public class CX extends Cruce {
	
	public static void cx(AlgoritmoGenetico alg) {
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
					
			        //Inicializamos los hijos a -1, para saber qué índices no se han puesto en los ciclos
			        for(int j = 0; j < hijo2.length; j++) {
			        	hijo1[j]=-1;
			        	hijo2[j]=-1;
			        }
			        
			        //Se hacen los ciclos
			        int ind = 0;
					while (!contieneSub(hijo1, padre1[ind], 0, padre1.length-1)) {
						hijo1[ind] = padre1[ind];
						ind = getIndex(padre1, padre2[ind], 0, padre1.length-1);
					}					
					ind = 0;
					while (!contieneSub(hijo2, padre2[ind], 0, padre1.length-1)) {
						hijo2[ind] = padre2[ind];
						ind = getIndex(padre2, padre1[ind], 0, padre1.length-1);
						
					}
					
					//Los valores que faltan por poner, se copian del padre no homólogo
					for(int j = 0; j < hijo2.length; j++) {
						if (hijo1[j]== -1 && !contieneSub(hijo1, padre2[j], 0, padre1.length-1))
							hijo1[j]= padre2[j];
						if (hijo2[j]== -1 && !contieneSub(hijo2, padre1[j], 0, padre1.length-1))
							hijo2[j]= padre1[j];
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
