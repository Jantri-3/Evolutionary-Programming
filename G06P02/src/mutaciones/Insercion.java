package mutaciones;

import java.util.Random;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Insercion {
	public static void insercion(AlgoritmoGenetico alg) {
		Random rand = new Random();
		Individuo[] pob = new Individuo[alg.getTamPoblacion()];
		for (int i = 0; i < alg.getTamPoblacion(); i++) {
			if (alg.getProbMutacion() <= rand.nextDouble()) {
				
				//Se obtienen un indice (is[0]) y una pos (is[1]) aleatoria
				int[] is = new int[2];
				for(int j = 0; j < is.length; j++)
					is[j] = Math.abs(rand.nextInt())%(pob[i].getCrom().length);
				
				//Se inserta el valor del índice en la pos
				int valor = pob[i].getCrom()[is[0]];
				for (int j = is[0]; j> is[1]; j--) {
					pob[i].setCrom(j, pob[i].getCrom()[j-1]);
				}
				pob[i].setCrom(is[1], valor);
			}
		}
	}
}
