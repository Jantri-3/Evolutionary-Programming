package selecciones;

import java.util.Arrays;

import Funciones.Individuo;
import main.AlgoritmoGenetico;
import main.FitnessComparatorMin;

public class Truncamiento {

	public void truncamiento(AlgoritmoGenetico alg,int nseleccionados) {

		//PorcentajeSeleccion = 10%
		double porcentajeSeleccion = 0.1;
		//En este caso ESPECIFICO (10%)

		
		//Primero, calculamos el número de individuos que queremos seleccionar a partir del porcentaje indicado.
		int numSeleccionados = (int) (nseleccionados * porcentajeSeleccion);//Los X mejores
		
		//Luego, creamos una copia ordenada de la población original en orden decreciente de puntuación.
		Arrays.sort(alg.getPoblacion(), new FitnessComparatorMin()); 
			
		
       
		//A continuación, seleccionamos los numSeleccionados primeros individuos de la población ordenada y los devolvemos como los seleccionados.
        Individuo[] seleccionados = new Individuo[numSeleccionados];
        for(int i = 0; i< numSeleccionados; i++) {
        	seleccionados[i]= alg.getPoblacion()[i].clonar();
        }
		//Finalmente igualamos la población antigua a la de individuos seleccionados
        int j = 0;
        for(int i = seleccionados.length; i < nseleccionados; i++) {
        	alg.getPoblacion()[i]= seleccionados[j].clonar();//Clonamos otra vez!!!
        	alg.getFitness()[i] = alg.getPoblacion()[i].getFitness();
        	j++;
        	j%=numSeleccionados;
        }

	}
}
