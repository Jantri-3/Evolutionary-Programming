package selecciones;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Ranking {
	
	static private final double _beta = 1.5;
	
	public void ranking(AlgoritmoGenetico alg, int nseleccionados) {
		
		rankingPunctuation(alg.getPoblacion());
		Ruleta.ruleta(alg, nseleccionados);
		
	}
	
	private void rankingPunctuation(Individuo[] pop) {
		double accPunc = 0.0;
		for (int i = 0; i < pop.length; ++i) {
			double probOfIth = (double)i/pop.length;
			probOfIth *= 2*(_beta-1);
			probOfIth = _beta - probOfIth;
			probOfIth = (double)probOfIth * ((double)1/pop.length);
			pop[i].setAccPunc(accPunc);
			pop[i].setPunc(probOfIth);
			accPunc += probOfIth;
			}
	}
}
