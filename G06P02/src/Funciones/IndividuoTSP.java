package Funciones;

import java.util.Random;

public class IndividuoTSP extends Individuo{	
	//(NI EL PRINCIPIO NI EL FINAL DE CROMOSOMA DEBEN DE SER MODIFICADOS EN NINGUN NMOMENTO)
	//NINGUNA PARTE DEL CROMOSOMA DEBE MUTAR/CRUZARSE PARA SER MADRID
	public IndividuoTSP(int d, double prec) {
		this.tamGenes = 1;//Las ciudades se indican con un int del 0 al 27
		this.valorError= prec;
		tamTotal = 28;//Numero de ciudades que recorre (Madrid->Ciudad->...->Madrid)
		
		this.cromosoma = new int[tamTotal];
		this.rand = new Random();
		//El recorrido del Comerciante siempre debe empezar y acabar en Madrid = _posMadrid
		this.cromosoma[0] = _posMadrid;
		this.cromosoma[cromosoma.length]= _posMadrid ;
		int aux = 0;
	    int[] cities = new int[26];//We create an array of 26 cities (not including Madrid)
        for (int i = 0; i < 26; i++) {
        	if(i == 24) {
        		cities[i] = 26;
        	}
        	else {
        		cities[i] = i;
        	}  
        }
        int j = 0;
        shuffleArray(cities);//we create a permutation of that array
        for (int i = 1; i< tamTotal-1 ;i++) {//we assign it to the TSP
        	this.cromosoma[i] = cities[j];
        	j++;
        }
	}
	
	public static void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = rand.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

	
	@Override
	public Individuo clonar(){
		Individuo clon = new IndividuoTSP(this.tamTotal,this.valorError);
		
		for(int i =0; i < tamTotal; i++) {
			clon.cromosoma[i] = this.cromosoma[i];
		}
		clon.calculaFitness();
		
		return clon;
	}
	
	@Override
	protected double getValor() {//tomaremos como valor la distancias entre las ciudades (es un problema de minimizacion sobre distancia del recorrido)
		double res=0;
		for(int i = 0; i < (tamTotal-1); i++) {
			res+=_DIST[this.cromosoma[i]][this.cromosoma[i+1]];
		}
		return res;
	}
        @Override
        public double getFenotipo(int i){
            return cromosoma[i];
        }
        
        @Override
    	public void calculaFitness() {
    		fitness= getValor();
    	}
    	
    	@Override
    	public double getFitness() {
    		calculaFitness();//distinto a la prev
    		return fitness;
    	}
}
