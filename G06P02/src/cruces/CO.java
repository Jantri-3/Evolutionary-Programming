package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.AlgoritmoGenetico;

public class CO extends Cruce { // con monopunto
	public static void co(AlgoritmoGenetico alg) {
		Random rand = new Random();
		List<Integer> indicesCruce = new ArrayList<>();
		
		seleccionaPadres(alg,indicesCruce, rand);
		
		if (indicesCruce.size() > 0) {
	        for (int i = 0; i < indicesCruce.size()-1; i+=2) {
	        	int[] padre1 = alg.getPoblacion()[indicesCruce.get(i)].getCrom();
	        	int[] padre2 = alg.getPoblacion()[indicesCruce.get(i+1)].getCrom();
	        	int[] aux1 = new int[padre1.length];//padre1 codificado de forma ordinal
	        	int[] aux2 = new int[padre1.length];//padre2 codificado de forma ordinal
	        	 if (!sonIguales(padre1,padre2)) {	
		        	int[] hijo1 = new int[padre1.length];
			        int[] hijo2 = new int[padre1.length];
			        
			        for(int j = 0; j < hijo2.length; j++) {
			        	hijo1[j]=-1;
			        	hijo2[j]=-1;
			        }
			        
			        int[][]hijos =new int[2][padre1.length];
			        //lista ordinal
			        List<Integer> lcordinal = new ArrayList<Integer>();
			        for(int j = 0 ; j< padre1.length;j++) {
			        	lcordinal.add(j);
			        }
			        
			        //Codificamos a los padres
			        for(int gen = 0;gen <padre1.length;gen++) {
			        	aux1[gen] = lcordinal.indexOf(padre1[gen]);
			        	lcordinal.remove(padre1[gen]);
			        }
			        List<Integer> lcordinal1 = new ArrayList<Integer>();
			        for(int j = 0 ; j< padre1.length;j++) {
			        	lcordinal1.add(j);
			        }
			        for(int gen = 0;gen <padre1.length;gen++) {
			        	aux2[gen] = lcordinal1.indexOf(padre1[gen]);
			        	lcordinal1.remove(padre1[gen]);
			        }
			        hijos = monopunto(aux1,aux2,padre1.length);//los hijos vienen codificados asi que los decodificamos
			        
			        //decodificacion de hijo1
			        List<Integer> lcordinal2 = new ArrayList<Integer>();
			        for(int j = 0 ; j< padre1.length;j++) {
			        	lcordinal2.add(j);
			        }
			        for(int gen = 0;gen <padre1.length;gen++) {
			        	 hijo1[gen]= lcordinal2.indexOf(hijos[0][gen]);
			        	lcordinal2.remove(hijos[0][gen]);
			        }
			        //decodificacion de hijo2
			        List<Integer> lcordinal3 = new ArrayList<Integer>();
			        for(int j = 0 ; j< padre1.length;j++) {
			        	lcordinal3.add(j);
			        }
			        for(int gen = 0;gen <padre1.length;gen++) {
			        	 hijo2[gen]= lcordinal3.indexOf(hijos[0][gen]);
			        	lcordinal3.remove(hijos[0][gen]);
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
	public static int[][] monopunto(int[] i1,int[] i2,int length) {
        Random rand1 = new Random();
        int[][]hijos =new int[2][length]; 
        int puntoCorte = 1 + Math.abs(rand1.nextInt())%(length);
        for (int j = 0; j <puntoCorte; j++){
            i2[j] = i1[j];
        }
        for (int j = puntoCorte; j <length; j++){
        	i1[j] = i2[j];
        }
        for (int i = 0 ; i< length;i++) {
        	hijos[0][i] = i1[i];
        	hijos[1][i] = i2[i];
        }
        return hijos;
    }
}