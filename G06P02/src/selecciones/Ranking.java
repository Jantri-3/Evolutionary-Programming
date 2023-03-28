package selecciones;

import java.util.ArrayList;
import java.util.List;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Ranking {
	
	static private final double _beta = 1.5;
	
	public static void ranking(AlgoritmoGenetico alg, int nseleccionados) {
		alg.ordenaCreciente();
		
		double[] pAcum = new double[nseleccionados];
		List<Individuo> seleccionados = new ArrayList<>();
		
		//Probabilidades acumuladas según el ranking
		pAcum[0]= 1/nseleccionados*((_beta-2)*(_beta-1)*((-1)/(nseleccionados-1)));
		for (int i = 1; i < nseleccionados; ++i) {
			pAcum[i]= (1/nseleccionados*((_beta-2)*(_beta-1)*((i-1)/(nseleccionados-1))))+ pAcum[i-1];
		}
		//Ruleta según las probabilidades acumuladas
		double valorAleatorio;

		for(int i = 0 ; i < nseleccionados; i++){
			valorAleatorio = Math.random();
			int j = 0;
			while(j < nseleccionados && pAcum[j] <= valorAleatorio){
				j++;
			}
			
			seleccionados.add(alg.getPoblacion()[j].clonar());
		}
		
		for (int i = 0; i < nseleccionados; i++) {
			alg.getPoblacion()[i] = seleccionados.get(i);
			alg.getFitness()[i]= alg.getPoblacion()[i].getFitness();
		}
	}

}
