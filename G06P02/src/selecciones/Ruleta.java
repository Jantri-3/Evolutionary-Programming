package selecciones;

import java.util.ArrayList;
import java.util.List;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Ruleta {
	
	public static void ruleta(AlgoritmoGenetico alg, int nseleccionados) {
		double[] puntAcum = new double[nseleccionados];
		List<Individuo> seleccionados = new ArrayList<>();
		double[] correctedFitness = new double[nseleccionados];
		
		//corregimos el uso del fitness;
		double maxim = alg.getMaxFit();
		alg.corrigeMinimizar(maxim, correctedFitness);	

		//Primero, sumamos todas las puntuaciones de los individuos en la población para obtener un una lista de valores acumulados.
		puntAcum[0] = correctedFitness[0];
        for (int i = 1; i < nseleccionados; i++) {
            puntAcum[i] = correctedFitness[i] + puntAcum[i-1];
        }
		//despues generamos el valor aleatorio;
		double valorAleatorio;

		for(int i = 0 ; i < nseleccionados; i++){
			valorAleatorio = Math.random() * puntAcum[nseleccionados-1];
			int j = 0;
			while(j < nseleccionados && puntAcum[j] <= valorAleatorio){
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
