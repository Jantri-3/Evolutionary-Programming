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
				int indH = Math.max(pC1, pC2)+1;
				for(int j = Math.max(pC1, pC2)+1; indH != Math.min(pC1, pC2); j = (j+1)%padre1.length) {
					if(!contieneSub(hijo1, padre1[j],pC1, pC2)) {
						hijo1[indH]= padre1[j];
						indH = (indH+1)%padre1.length;
					}
					
				}
				
				indH = Math.max(pC1, pC2)+1;
				for(int j = Math.max(pC1, pC2)+1; indH != Math.min(pC1, pC2); j = (j+1)%padre1.length) {
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
	
	public static void ox_pp(AlgoritmoGenetico alg) {
		Random rand = new Random();
		List<Integer> indicesCruce = new ArrayList<>();
		
		seleccionaPadres(alg,indicesCruce, rand);
		
		if (indicesCruce.size() > 0) {
			for (int i = 0; i < indicesCruce.size()-1; i+=2) {
	        	int[] padre1 = alg.getPoblacion()[indicesCruce.get(i)].getCrom();
	        	int[] padre2 = alg.getPoblacion()[indicesCruce.get(i+1)].getCrom();
	        	int[] hijo1 = new int[padre1.length];
		        int[] hijo2 = new int[padre1.length];
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
				int indH = is[3] +1;
				for(int j = is[3]+1; indH != is[3]; j = (j+1)%padre1.length) {
					if(!contieneSub(is, padre1[j], 0, is.length-1)) {
						hijo1[indH]= padre1[j];
						indH = (indH+1)%padre1.length;
					}
					
				}
				
				indH = is[3] +1;
				for(int j = is[3]+1; indH != is[3]; j = (j+1)%padre1.length) {
					if(!contieneSub(is, padre2[j], 0, is.length-1)) {
						hijo2[indH]= padre2[j];
						indH = (indH+1)%padre2.length;
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
	
	public static void ox_op(AlgoritmoGenetico alg) {
		Random rand = new Random();
		List<Integer> indicesCruce = new ArrayList<>();
		
		seleccionaPadres(alg,indicesCruce, rand);
		
		if (indicesCruce.size() > 0) {
	        for (int i = 0; i < indicesCruce.size()-1; i+=2) {
	        	int[] padre1 = alg.getPoblacion()[indicesCruce.get(i)].getCrom();
	        	int[] padre2 = alg.getPoblacion()[indicesCruce.get(i+1)].getCrom();
	        	int[] hijo1 = new int[padre1.length];
		        int[] hijo2 = new int[padre1.length];
		        //Se sacan 4 índices, se pueden poner más
				Random rand1 = new Random();
				int[] is = new int[4];
				for(int j = 0; j < is.length; j++)
					is[j] = Math.abs(rand1.nextInt())%(padre1.length);
				Arrays.sort(is);
				
				//Se toman los valores en los índices aleatorios de un padre
				int[] posPadre1 = new int[is.length];
				int[] posPadre2 = new int[is.length];
				for(int j= 0;j< posPadre1.length;j++) {
	             posPadre1[j] = padre1[is[j]];
	             posPadre2[j] = padre2[is[j]];
				}
				
				//Buscamos en qué posiciones están en el padre contrario
				for(int j= 0;j< posPadre1.length;j++) {
		             posPadre1[j] = getIndex(padre2, posPadre1[j],0, padre1.length);
		             posPadre2[j] = getIndex(padre1, posPadre2[j],0, padre1.length);
					}
	            Arrays.sort(posPadre1);
	            Arrays.sort(posPadre2);
				//Se rellenan los hijos: si la posicion es la siguiente del array, se copia la del padre homólogo
				int indPos1 = 0;
				int indPos2 = 0;
				for(int j = 0; j < padre1.length; j++) {
					if(posPadre1[indPos1] == j) {
						hijo1[j]= padre1[j];
						indPos1++;
					}
					else
						hijo1[j]= padre2[j];
					
					if(posPadre2[indPos2] == j) {
						hijo2[j]= padre2[j];
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
