package mutaciones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Heuristica {
	public static void heuristica(AlgoritmoGenetico alg) {
		Random rand = new Random();
		final int N = 3;
		Individuo[] pob = new Individuo[alg.getTamPoblacion()];
		for (int i = 0; i < alg.getTamPoblacion(); i++) {
			if (alg.getProbMutacion() <= rand.nextDouble()) {
				
				//N ciudades al azar (sus indices)
				int[] is = new int[N];
				for(int j = 0; j < is.length; j++)
					is[j] = Math.abs(rand.nextInt())%(pob[i].getCrom().length);
				Arrays.sort(is);
				
				//Se calculan las permutaciones (con los valores de los indices)
					//Traduccion a valores
				int[] valores = new int[is.length];
				for(int j = 0; j < is.length; j++)
					valores[j]= pob[i].getCrom()[is[j]];
					//Permutaciones
				List<List<Integer>> result = new ArrayList<>();
				List<Integer> tempList = new ArrayList<Integer>();
				permutaciones(result, tempList, valores);
				
				//Se generan las posibles mutaciones
				Individuo[] muts = new Individuo[result.size()];
				for(int j = 0; j < result.size(); j++) {
					int ind = 0;
					for(int k = 0; k < pob[i].getCrom().length; k++) {
						if (k == is[ind]) {
							muts[j].setCrom(k, valores[ind]);
							ind++;
						}
						else
							muts[j].setCrom(k, pob[i].getCrom()[k]);
					}
				}

				//Se selecciona la mejor mutacion
				int indMejor = 0, mejorFit = muts[indMejor].getFitness();
				for(int j = 1; j < is.length; j++) {
					if(muts[j].getFitness() > mejorFit) {
						mejorFit = muts[j].getFitness();
						indMejor = j;
					}
				}
				
				//Se muta el individuo
				for (int j =0; j < pob[i].getCrom().length; j++) {
					pob[i].setCrom(j, muts[indMejor].getCrom()[j]);
				}
			}
		}
	}
	
	
	
	private static void permutaciones(List<List<Integer>> result, List<Integer> tempList, int[] nums) {
        if(tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
        } else {
            for(int i = 0; i < nums.length; i++) {
                if(tempList.contains(nums[i])) continue;
                tempList.add(nums[i]);
                permutaciones(result, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}
