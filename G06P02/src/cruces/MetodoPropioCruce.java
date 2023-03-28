package cruces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import main.AlgoritmoGenetico;

public class MetodoPropioCruce extends Cruce{

	public static void metodopropiocruc(AlgoritmoGenetico alg) {
		Random rand = new Random();
		List<Integer> indicesCruce = new ArrayList<>();
		
		seleccionaPadres(alg,indicesCruce, rand);
		
		if (indicesCruce.size() > 0) {
	        for (int i = 0; i < indicesCruce.size()-1; i+=2) {
	        	int[] padre1 = alg.getPoblacion()[indicesCruce.get(i)].getCrom();
	        	int[] padre2 = alg.getPoblacion()[indicesCruce.get(i+1)].getCrom();
	        	int[] aux1 = new int[padre1.length];//padre1 codificado de forma ordinal
	        	int[] aux2 = new int[padre1.length];//padre2 codificado de forma ordinal
	        	int[] hijo1 = new int[padre1.length];
		        int[] hijo2 = new int[padre1.length];
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
		        hijos = uniforme(aux1,aux2,padre1.length);//los hijos vienen codificados asi que los decodificamos
		        
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
	public static int[][] uniforme(int[] i1,int[] i2,int length) {
        int[][]hijos =new int[2][length]; 
        int aux;
        for(int k = 0; k < length; k++) {
            Random rand1 = new Random();
            if (rand1.nextDouble() <= 0.6) {
                aux = i1[k];
                i1[k] = i2[k];
                i2[k] = aux;
            }
        }
        for (int i = 0 ; i< length;i++) {
        	hijos[0][i] = i1[i];
        	hijos[1][i] = i2[i];
        }
        return hijos;
	}

}