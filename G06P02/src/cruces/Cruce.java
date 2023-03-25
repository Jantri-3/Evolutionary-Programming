package cruces;

import java.util.List;
import java.util.Random;

import main.AlgoritmoGenetico;

public class Cruce {
	protected static void seleccionaPadres(AlgoritmoGenetico alg,List<Integer> indicesCruce, Random rand) {
		for(int i=0; i< alg.getTamPoblacion(); i++) {//emparejamiento
			if (rand.nextDouble() < alg.getProbCruce()) {
				int ind  = Math.abs(rand.nextInt()%alg.getTamPoblacion());
				indicesCruce.add(ind);
			}
		}
	}
	
	protected static boolean contieneSub(int[] array, int value, int a, int b) {
        for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
            if (array[i] == value) {
                return true;
            }
        }
        return false;
    }
    
    protected static int getIndex(int[] array, int value, int a, int b) {
        for (int i = Math.min(a, b); i <= Math.max(a, b); i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

}
