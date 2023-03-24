package selecciones;

import java.util.ArrayList;
import java.util.List;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class EstocasticoUniversal {
	public static void estUniversal(AlgoritmoGenetico alg,int nseleccionados) {
		double[] puntAcum = new double[nseleccionados];
		List<Individuo> seleccionados = new ArrayList<>();
		double[] correctedFitness = new double[nseleccionados];

		double maxim = alg.getMaxFit();
		alg.corrigeMinimizar(maxim, correctedFitness);

		//Primero, sumamos todas las puntuaciones de los individuos en la población para obtener un una lista de valores acumulados.
		puntAcum[0] = correctedFitness[0];
        for (int i = 1; i < nseleccionados; i++) {
            puntAcum[i] = correctedFitness[i] + puntAcum[i-1];
        }
		//despues generamos el valor aleatorio;
		double dist = puntAcum[nseleccionados-1] / nseleccionados;
		double valorAleatorio = Math.random() * dist;

		for(int i = 0; i < nseleccionados; i++){
			int j = 0;
			while(puntAcum[j] < valorAleatorio && j < nseleccionados - 1){
				j++;
			}
			valorAleatorio = valorAleatorio + dist ;
			seleccionados.add(alg.getPoblacion()[j].clonar());
		}
		
		for (int i = 0; i < nseleccionados; i++) {
			alg.getPoblacion()[i] = seleccionados.get(i);
			alg.getFitness()[i]= alg.getPoblacion()[i].getFitness();
		}

    }
}
