package mutaciones;

import java.util.Arrays;
import java.util.Random;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Inversion {
	public static void inversion(AlgoritmoGenetico alg) {
		Random rand = new Random();
		Individuo[] pob = alg.getPoblacion();
		for (int i = 0; i < alg.getTamPoblacion(); i++) {
			if (alg.getProbMutacion() >= rand.nextDouble()) {
				
				//Se obtienen dos puntos de corte
				int[] is = new int[2];
				for(int j = 0; j < is.length; j++)
					is[j] = Math.abs(rand.nextInt())%(pob[i].getCrom().length);
				Arrays.sort(is);
				
				//Se copia la subcadena entre puntos de corte al revés
				int[] sub = new int[is[1]-is[0]+1];
				int indSub = is[1]-is[0];
				for(int j = is[0]; j <= is[1]; j++) {
					sub[indSub]= pob[i].getCrom()[j];
					indSub--;
				}
				
				//Se pone la subcadena en el individuo
				indSub = 0;
				for(int j = is[0]; j <= is[1]; j++) {
					pob[i].setCrom(j,sub[indSub]);
					indSub++;
				}
			}
		}
	}
}
