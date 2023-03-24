package cruces;

import java.util.List;
import java.util.Random;

import main.AlgoritmoGenetico;

public class Cruce {
	private void seleccionaPadres(AlgoritmoGenetico alg,List<Integer> indicesCruce, Random rand) {
		for(int i=0; i< alg.getTamPoblacion(); i++) {//emparejamiento
			if (rand.nextDouble() < alg.getProbCruce()) {
				int ind  = Math.abs(rand.nextInt()%alg.getTamPoblacion());
				indicesCruce.add(ind);
			}
		}
	}

}
