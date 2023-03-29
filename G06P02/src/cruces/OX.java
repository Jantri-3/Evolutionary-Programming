package cruces;

import java.util.ArrayList;
import java.util.Arrays;
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
	        	if (!sonIguales(padre1,padre2)) {	
		        	int[] hijo1 = new int[padre1.length];
			        int[] hijo2 = new int[padre1.length];
			        
			        for(int j = 0; j < hijo2.length; j++) {
			        	hijo1[j]=-1;
			        	hijo2[j]=-1;
			        }
			        
			        Random rand1 = new Random();
					int[] is = new int[2];
					for(int j = 0; j < is.length; j++)
						is[j] = Math.abs(rand1.nextInt())%(padre1.length);
					Arrays.sort(is);
					
					//Se copia la subcadena
					for (int j = is[0]; j <= is[1]; j++) {
		                hijo1[j] = padre2[j];
		                hijo2[j] = padre1[j];
					}
					//Recorrido circular desde el segundo punto de corte
					int indH = (is[1]+1)%padre1.length;
					for(int j = (is[1]+1)%padre1.length; indH != is[0]; j = (j+1)%padre1.length) {
						if(!contieneSub(hijo1, padre1[j],is[0], is[1])) {
							hijo1[indH]= padre1[j];
							indH = (indH+1)%padre1.length;
						}
						
					}
					
					indH = (is[1]+1)%padre1.length;
					for(int j = (is[1]+1)%padre1.length; indH != is[0]; j = (j+1)%padre1.length) {
						if(!contieneSub(hijo2, padre2[j],is[0], is[1])) {
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
	
	public static void ox_pp(AlgoritmoGenetico alg) {
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
			        
			        for(int j = 0; j < hijo2.length; j++) {
			        	hijo1[j]=-1;
			        	hijo2[j]=-1;
			        }
			        
			        //Se sacan 4 índices, se pueden poner más
					Random rand1 = new Random();
					int[] is = new int[4];
					for(int j = 0; j < is.length; j++)
						is[j] = Math.abs(rand1.nextInt())%(padre1.length);
					Arrays.sort(is);
					
					//Se copian los valores de esos indices
					for(int j= 0;j< is.length;j++) {
					 hijo1[is[j]] = padre2[is[j]];
		             hijo2[is[j]] = padre1[is[j]];
					}
		             
					//Recorrido circular desde el indice más alto
					int indH = (is[3]+1)%padre1.length;
					for(int j = (is[3]+1)%padre1.length; indH != is[3]; j = (j+1)%padre1.length) {		
						if(!contieneSub(is, indH, 0, is.length-1) && !contieneSub(hijo1, padre1[j], 0, padre1.length-1)) {
							hijo1[indH]= padre1[j];
							indH = (indH+1)%padre1.length;
						}
						else if(contieneSub(is, indH, 0, is.length-1)&& !contieneSub(hijo1, padre1[j], 0, padre1.length-1)) {
							indH = (indH+1)%padre1.length;
							j--;
						}
						else if (contieneSub(is, indH, 0, is.length-1)) {
							indH = (indH+1)%padre1.length;
						}
					}
					
					indH = (is[3]+1)%padre1.length;
					for(int j = (is[3]+1)%padre1.length; indH != is[3]; j = (j+1)%padre1.length) {
						if(!contieneSub(hijo2, padre2[j], 0, padre1.length-1) && !contieneSub(is, indH, 0, is.length-1)) {
							hijo2[indH]= padre2[j];
							indH = (indH+1)%padre2.length;
						}	else if(!contieneSub(hijo2, padre2[j], 0, padre1.length-1) && contieneSub(is, indH, 0, is.length-1)) {
							indH = (indH+1)%padre1.length;
							j--;
						}
						else if (contieneSub(is, indH, 0, is.length-1)) {
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
	
	public static void ox_op(AlgoritmoGenetico alg) {
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
			        
			        for(int j = 0; j < hijo2.length; j++) {
			        	hijo1[j]=-1;
			        	hijo2[j]=-1;
			        }
			        
			        //Se sacan 4 índices, se pueden poner más
					Random rand1 = new Random();
					int[] is = new int[4];
					for(int j = 0; j < is.length; j++)
						is[j] = Math.abs(rand1.nextInt())%(padre1.length);
					Arrays.sort(is);
					
					//Se toman los valores en los índices aleatorios de un padre
					
					int[] posContraria1 = new int[is.length];
					int[] posContraria2 = new int[is.length];
					//Buscamos en qué posiciones están en el padre contrario
					for(int j= 0;j< is.length;j++) {
						posContraria1[j] = getIndex(padre2, padre1[is[j]],0, padre1.length-1);
						posContraria2[j] = getIndex(padre1, padre2[is[j]],0, padre1.length-1);
						}
		            Arrays.sort(posContraria1);
		            Arrays.sort(posContraria2);
					//Se rellenan los hijos: si la posicion es la siguiente del array, se copia la del padre homólogo
					int indPos1 = 0;
					int indPos2 = 0;
					for(int j = 0; j < padre1.length; j++) {
						if(indPos1 < is.length && posContraria1[indPos1] == j) {
							hijo1[j]= padre1[is[indPos1]];
							indPos1++;
						}
						else
							hijo1[j]= padre2[j];
						
						if(indPos2 < is.length &&posContraria2[indPos2] == j) {
							hijo2[j]= padre2[is[indPos2]];
							indPos2++;
						}
						else
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
