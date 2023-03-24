package selecciones;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Funciones.Individuo;
import main.AlgoritmoGenetico;

public class Torneo {
	public static void deterministico(AlgoritmoGenetico alg,int nseleccionados) {//Edu
		List<Individuo> seleccionados = new ArrayList<>();
		int i1, i2, i3;

		for (int i = 0; i < nseleccionados; i++) {//se generan tantos ganadores como poblacion se necesita
			//se generan 3 indices aleatorios de la poblacion
			Random r1 = new Random();
			i1 = Math.abs(r1.nextInt()) % nseleccionados;
			Random r2 = new Random();
			i2 = Math.abs(r2.nextInt()) % nseleccionados;
			Random r3 = new Random();
			i3 = Math.abs(r3.nextInt()) % nseleccionados;
			
			selecMin(alg,seleccionados, i1, i2, i3);

		}
		//se sobreescribe poblacion[] con los seleccionados
 		for (int i = 0; i < seleccionados.size(); ++i) {
 			alg.getPoblacion()[i] = seleccionados.get(i).clonar();
 			alg.getFitness()[i]= alg.getPoblacion()[i].getFitness();
 		}
	}

	public static void probabilistico(AlgoritmoGenetico alg,int nseleccionados) {//Edu
		List<Individuo> seleccionados = new ArrayList<>();
		int i1, i2, i3;
		double ganador;
		Random r = new Random();

		for (int i = 0; i < nseleccionados; i++) {//se generan tantos ganadores como poblacion se necesita
			//se generan 3 indices aleatorios de la poblacion
			Random r1 = new Random();
			i1 = Math.abs(r1.nextInt()) % nseleccionados;
			Random r2 = new Random();
			i2 = Math.abs(r2.nextInt()) % nseleccionados;
			Random r3 = new Random();
			i3 = Math.abs(r3.nextInt()) % nseleccionados;
			
			ganador = r.nextDouble();
			
			if (ganador >0.6)
				selecMax(alg,seleccionados, i1, i2, i3);
			else
				selecMin(alg,seleccionados, i1, i2, i3);
		}
		//se sobreescribe poblacion[] con los seleccionados
 		for (int i = 0; i < seleccionados.size(); ++i) {
 			alg.getPoblacion()[i] = seleccionados.get(i).clonar();
 			alg.getFitness()[i]=alg.getPoblacion()[i].getFitness();
 		}
	}
	
	private static void selecMax(AlgoritmoGenetico alg,List<Individuo> seleccionados, int i1, int i2, int i3) {
		if (alg.getPoblacion()[i1].getFitness() >= alg.getPoblacion()[i2].getFitness() && 
				alg.getPoblacion()[i1].getFitness() >= alg.getPoblacion()[i3].getFitness())
			seleccionados.add(alg.getPoblacion()[i1].clonar());
		else if(alg.getPoblacion()[i2].getFitness() >= alg.getPoblacion()[i1].getFitness() &&
					alg.getPoblacion()[i2].getFitness() >= alg.getPoblacion()[i3].getFitness())
				seleccionados.add(alg.getPoblacion()[i2].clonar());
			else
				seleccionados.add(alg.getPoblacion()[i3].clonar());
	}
	private static void selecMin(AlgoritmoGenetico alg,List<Individuo> seleccionados, int i1, int i2, int i3) {
		if (alg.getPoblacion()[i1].getFitness() <= alg.getPoblacion()[i2].getFitness() && 
				alg.getPoblacion()[i1].getFitness() <= alg.getPoblacion()[i3].getFitness())
			seleccionados.add(alg.getPoblacion()[i1].clonar());
		else if(alg.getPoblacion()[i2].getFitness() <= alg.getPoblacion()[i1].getFitness() &&
				alg.getPoblacion()[i2].getFitness() <= alg.getPoblacion()[i3].getFitness())
				seleccionados.add(alg.getPoblacion()[i2].clonar());
			else
				seleccionados.add(alg.getPoblacion()[i3].clonar());
	}
}
