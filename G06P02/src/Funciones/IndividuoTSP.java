package Funciones;

import java.util.Random;

public class IndividuoTSP extends Individuo{	
	//(NI EL PRINCIPIO NI EL FINAL DE CROMOSOMA DEBEN DE SER MODIFICADOS EN NINGUN MOMENTO)
	//NINGUNA PARTE DEL CROMOSOMA DEBE MUTAR/CRUZARSE PARA SER MADRID
	public IndividuoTSP() {
		this.tamGenes = 1;//Las ciudades se indican con un int del 0 al 27
		tamTotal = 26;//Numero de ciudades que recorre sin contar Madrid al principio ni al final
		
		this.cromosoma = new int[tamTotal];
		this.rand = new Random();

        for (int i = 0; i < 26; i++) {
        	if(i == _posMadrid) {
        		cromosoma[i] = 26;
        	}
        	else {
        		cromosoma[i] = i;
        	}  
        }
        int j = 0;
        shuffleArray(cromosoma);//we create a permutation of that array
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
		Individuo clon = new IndividuoTSP();
		
		for(int i =0; i < tamTotal; i++) {
			clon.cromosoma[i] = this.cromosoma[i];
		}
		clon.calculaFitness();
		
		return clon;
	}
	
	@Override
	protected int getValor() {//tomaremos como valor la distancias entre las ciudades (es un problema de minimizacion sobre distancia del recorrido)
		int res=0;
		for(int i = 0; i < (tamTotal-1); i++) {
			res+=_DIST[Math.max(this.cromosoma[i],this.cromosoma[i+1])][Math.min(this.cromosoma[i],this.cromosoma[i+1])];
		}
		//sumamos la distancia de la primera y la última ciudad a Madrid
		res += 	_DIST[Math.max(this.cromosoma[0],_posMadrid)][Math.min(this.cromosoma[0],_posMadrid)] +
				_DIST[Math.max(this.cromosoma[tamTotal-1],_posMadrid)][Math.min(this.cromosoma[tamTotal-1],_posMadrid)];
		return res;
	}
        @Override
        public int getFenotipo(int i){
            return cromosoma[i];
        }
        
        @Override
    	public void calculaFitness() {
    		fitness= getValor();
    	}
    	
    	@Override
    	public int getFitness() {
    		calculaFitness();//distinto a la prev
    		return fitness;
    	}
}
