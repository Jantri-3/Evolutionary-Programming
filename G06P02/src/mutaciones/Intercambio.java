package mutaciones;

import java.util.Arrays;
import java.util.Random;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Intercambio {
	public static void intercambio(AlgoritmoGenetico alg) {
		Random rand = new Random();
		Individuo[] pob = alg.getPoblacion();
		for (int i = 0; i < alg.getTamPoblacion(); i++) {
			if (alg.getProbMutacion() <= rand.nextDouble()) {
				
				//Se obtienen dos indices
				int[] is = new int[2];
				for(int j = 0; j < is.length; j++)
					is[j] = Math.abs(rand.nextInt())%(pob[i].getCrom().length);
				Arrays.sort(is);
				
				//Se intercambian los valores de los índices
				int aux = pob[i].getCrom()[is[0]];
				pob[i].setCrom(is[0], pob[i].getCrom()[is[1]]);
				pob[i].setCrom(is[1], aux);
				
			}
		}
	}
}
