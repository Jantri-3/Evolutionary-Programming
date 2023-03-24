package selecciones;

import java.util.ArrayList;
import java.util.List;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Restos {
	public void restos(AlgoritmoGenetico alg, int nseleccionados) {//Edu (el resto simplemente haz que haga un random y tire por cualquiera de los otros métodos o hazlo fijo)
		double[] p = new double[nseleccionados];
		List<Individuo> seleccionados = new ArrayList<>(); 
		calculaPi(alg, nseleccionados, p);
		
		for(int i = 0; i < nseleccionados; ++i) {
			int pik = (int)(p[i] * nseleccionados);
			if (pik > 0) 
				seleccionados.add(alg.getPoblacion()[i].clonar());
		}
		int nRestos = nseleccionados-seleccionados.size();
		if (seleccionados.size() < nseleccionados) {
			Ruleta.ruleta(alg,nRestos);
		}
		
		for (int i = nRestos; i < seleccionados.size(); i++) {
			alg.getPoblacion()[i] = seleccionados.get(i);
			alg.getFitness()[i]=alg.getPoblacion()[i].getFitness();
		}
	}
	
	private static void calculaPi(AlgoritmoGenetico alg, int nseleccionados, double[] p) {
		double sumFitness = 0;
		for(int i=0; i < nseleccionados; i++) {
			sumFitness += alg.getPoblacion()[i].getFitness();
		}
		
		for(int i=0; i < nseleccionados; i++) {
			p[i] = alg.getPoblacion()[i].getFitness()/sumFitness;
		}
	}
}
