package main;

import Funciones.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class AlgoritmoGenetico implements Seleccion, Cruce, Mutacion  {
	//(NI EL PRINCIPIO NI EL FINAL DE CROMOSOMA DEBEN DE SER MODIFICADOS EN NINGUN NMOMENTO)
	//NINGUNA PARTE DEL CROMOSOMA DEBE MUTAR/CRUZARSE PARA SER MADRID
	private MainComposite ui;
	private int tamPoblacion;
	private Individuo[] poblacion;
	private double[] fitness;
	private int maxGeneraciones;
    private String metodoSelec;
    private String metodoCruce;
	private double probCruce;
    private String metodoMut;
	private double probMutacion;
	//private int tamTorneo; no usado
	//private Individuo elMejor;
	private boolean firstIt;
	private int pos_mejor;
	private Individuo[] elite;
	private double elitismo;
	private double mejorFit;
	private double mejorAbsFit;
    private int funcion;
    private double prec;
    private int d;
	
    public AlgoritmoGenetico(MainComposite ui){
            this.ui=ui;
            this.firstIt=true;
            this.tamPoblacion = ui.getTam_pob();;
            this.poblacion = new Individuo[tamPoblacion];
            this.fitness = new double[tamPoblacion];
            this.metodoSelec = ui.getSelec();
            this.maxGeneraciones = ui.getNum_gen();
            this.metodoCruce = ui.getCruce();
            this.probCruce = ui.getProb_cruce();
            this.metodoMut = ui.getMut();
            this.probMutacion= ui.getProb_mut();
            this.elitismo = ui.getElitismo();
            this.elite = new Individuo[(int)Math.ceil(tamPoblacion*elitismo)];
            this.funcion= ui.getFuncion();
            this.prec = ui.getPrec();
            this.d= ui.getD();
        }
	public void run() {
		init_pob();
		ev_pob();
		mejorAbsFit = mejorFit;
		for (int i = 0; i < this.maxGeneraciones; i++) {
			generaElite();
			seleccion();			
			cruce();
			mutacion();
			introduceElite();
			ev_pob();
			if(mejorFit< mejorAbsFit)
				mejorAbsFit = mejorFit;
			LinePlot.setCoords(i,getMaxFit());
			LinePlot.setBestCoords(i, mejorAbsFit);
			LinePlot.setMeanCoords(i, getMeanFit());
		}
		pos_mejor= getMaxFitInd();
		
		LinePlot.print();
	}
	public String getElMejor() {
		String s = "";
		s += "Fitness: " + mejorAbsFit + " Genes: ";
		for(int i = 0; i < poblacion[pos_mejor].getNGen(); i++) {
			s+= "x" + i + " (" + poblacion[pos_mejor].getFenotipo(i) + ") ";
		}
		return s;
	}

    private void introduceElite() {
    	Arrays.sort(poblacion, new FitnessComparatorMin());//antes fitness comparator sin el min
    	for(int i = 0; i< this.tamPoblacion; i++)
    		fitness[i] *= -1;
    	Arrays.sort(fitness);
    	for(int i = 0; i< this.tamPoblacion; i++)
    		fitness[i] *= -1;
    	for(int i = 0; i < elite.length; i++) {
			poblacion[tamPoblacion - 1 - i] = elite[i];
		}
    }
	private void generaElite() {
		Arrays.sort(poblacion, new FitnessComparatorMin());//antes fitness comparator sin el min
		for(int i = 0; i< this.tamPoblacion; i++)
    		fitness[i] *= -1;
    	Arrays.sort(fitness);
    	for(int i = 0; i< this.tamPoblacion; i++)
    		fitness[i] *= -1;
    	for(int i = 0; i < elite.length; i++) {
			elite[i] = poblacion[i];
		}	
    }
	
	private double getMeanFit() {
    	double ret = 0;
    	
    	for(int i = 0; i < this.tamPoblacion; i++) {
    		ret += poblacion[i].getFitness(); 
    	}
    	ret /= this.tamPoblacion;
    	return ret;
    }
	
	private void init_pob() {
	    for(int i =0; i< this.tamPoblacion; i++){
               poblacion[i] = new IndividuoTSP(d, prec);
            }
	}

	private void ev_pob() {
		for (int i = 0; i< this.tamPoblacion; i++) {
			poblacion[i].calculaFitness();//actualiza fitness
			fitness[i] = poblacion[i].getFitness();
		}
		//calcula el mejor dependiendo de si es maximizacion o minimizacion
		if (this.firstIt && getMinFit()< mejorFit) {
			mejorFit = getMinFit();
			this.firstIt=false;
		}
	}
	
	public Individuo[] seleccion() {
		switch (this.metodoSelec) { 
	    case "Ruleta":
	    	ruleta(tamPoblacion);
	    	break;
	    case "Torneo det.":
	    	torneoDet(tamPoblacion);
	    	break;
	    case "Torneo prob.":
		    torneoProb(tamPoblacion);
		    break;
	    case "Estocástico universal":
	    	estUniversal(tamPoblacion);
	    	break;
	    case "Truncamiento":
		    truncamiento(tamPoblacion);
		    break;
	    case "Restos":
	    	restos(tamPoblacion);
		    break;
	  }
		return poblacion;
	}
	
	public void cruce() {
		if (poblacion[0].getTamTotal()>1) {
			switch (this.metodoCruce) { 
			 case "Pmx":
		    	pmx();
		    	break;
			 case "Ox":
		    	ox();
		    	break;
			 case "oxV2":
				oxV2();
				break;
			 case "Cx":
			    cx();
			    break;
			 case "Erx":
			    erx();
			    break;
			 case "Co":
			    co();
			    break;
			 case "MetodoPropio":
				metodopropioCRUC();
			    break;
			
			}
		}
	}
	
	@Override
	public void pmx() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void ox() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void oxV2() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void cx() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void erx() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void co() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void metodopropioCRUC() {
		// TODO Auto-generated method stub
		
	}
        
    public void mutacion() {
		switch (this.metodoMut) { 
		case "Insercion":
			insercion(funcion);
			break;
		case "Intercambio":
			intercambio(funcion);
			break;
		case "Inversion":
			inversion(funcion);
			break;
		case "heuristica":
			heuristica(funcion);
			break;
		case "MetodoPropio":
	    	metodopropioMUT(funcion);
	    	break;
		}
	}
    
    @Override
	public void insercion(int funcion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void intercambio(int funcion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void inversion(int funcion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void heuristica(int funcion) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void metodopropioMUT(int funcion) {
		// TODO Auto-generated method stub
		
	}

	private void seleccionaPadres(List<Integer> indicesCruce, Random rand) {
		for(int i=0; i< this.tamPoblacion; i++) {//emparejamiento
			if (rand.nextDouble() < this.probCruce) {
				int ind  = Math.abs(rand.nextInt()%this.tamPoblacion);
				indicesCruce.add(ind);
			}
		}
	}


	@Override
	public void ruleta(int nseleccionados) {
		double[] puntAcum = new double[nseleccionados];
		List<Individuo> seleccionados = new ArrayList<>();
		double[] correctedFitness = new double[nseleccionados];
		
		//corregimos el uso del fitness;
		double maxim = getMaxFit();
		corrigeMinimizar(maxim, correctedFitness);	

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
			
			seleccionados.add(poblacion[j].clonar());
		}
		
		for (int i = 0; i < nseleccionados; i++) {
			poblacion[i] = seleccionados.get(i);
			fitness[i]= poblacion[i].getFitness();
		}

	}

	@Override
	public void torneoDet(int nseleccionados) {//Edu
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
			
			selecMin(seleccionados, i1, i2, i3);

		}
		//se sobreescribe poblacion[] con los seleccionados
 		for (int i = 0; i < seleccionados.size(); ++i) {
 			poblacion[i] = seleccionados.get(i).clonar();
 			fitness[i]=poblacion[i].getFitness();
 		}
	}

	@Override
	public void torneoProb(int nseleccionados) {//Edu
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
				selecMax(seleccionados, i1, i2, i3);
			else
				selecMin(seleccionados, i1, i2, i3);
		}
		//se sobreescribe poblacion[] con los seleccionados
 		for (int i = 0; i < seleccionados.size(); ++i) {
 			poblacion[i] = seleccionados.get(i).clonar();
 			fitness[i]=poblacion[i].getFitness();
 		}
	}
	
	private void selecMax(List<Individuo> seleccionados, int i1, int i2, int i3) {
		if (poblacion[i1].getFitness() >= poblacion[i2].getFitness() && 
				poblacion[i1].getFitness() >= poblacion[i3].getFitness())
			seleccionados.add(poblacion[i1].clonar());
		else if(poblacion[i2].getFitness() >= poblacion[i1].getFitness() &&
					poblacion[i2].getFitness() >= poblacion[i3].getFitness())
				seleccionados.add(poblacion[i2].clonar());
			else
				seleccionados.add(poblacion[i3].clonar());
	}
	private void selecMin(List<Individuo> seleccionados, int i1, int i2, int i3) {
		if (poblacion[i1].getFitness() <= poblacion[i2].getFitness() && 
				poblacion[i1].getFitness() <= poblacion[i3].getFitness())
			seleccionados.add(poblacion[i1].clonar());
		else if(poblacion[i2].getFitness() <= poblacion[i1].getFitness() &&
					poblacion[i2].getFitness() <= poblacion[i3].getFitness())
				seleccionados.add(poblacion[i2].clonar());
			else
				seleccionados.add(poblacion[i3].clonar());
	}

	@Override
	public void estUniversal(int nseleccionados) {
		double[] puntAcum = new double[nseleccionados];
		List<Individuo> seleccionados = new ArrayList<>();
		double[] correctedFitness = new double[nseleccionados];

		double maxim = getMaxFit();
		corrigeMinimizar(maxim, correctedFitness);

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
			seleccionados.add(poblacion[j].clonar());
		}
		
		for (int i = 0; i < nseleccionados; i++) {
			poblacion[i] = seleccionados.get(i);
			fitness[i]=poblacion[i].getFitness();
		}

    }
		

	@Override
	public void truncamiento(int nseleccionados) {

		//PorcentajeSeleccion = 10%
		double porcentajeSeleccion = 0.1;
		//En este caso ESPECIFICO (10%)

		
		//Primero, calculamos el número de individuos que queremos seleccionar a partir del porcentaje indicado.
		int numSeleccionados = (int) (nseleccionados * porcentajeSeleccion);//Los X mejores
		
		//Luego, creamos una copia ordenada de la población original en orden decreciente de puntuación.
		Arrays.sort(poblacion, new FitnessComparatorMin()); 
			
		
       
		//A continuación, seleccionamos los numSeleccionados primeros individuos de la población ordenada y los devolvemos como los seleccionados.
        Individuo[] seleccionados = new Individuo[numSeleccionados];
        for(int i = 0; i< numSeleccionados; i++) {
        	seleccionados[i]= poblacion[i].clonar();
        }
		//Finalmente igualamos la población antigua a la de individuos seleccionados
        int j = 0;
        for(int i = seleccionados.length; i < nseleccionados; i++) {
        	poblacion[i]= seleccionados[j].clonar();//Clonamos otra vez!!!
        	fitness[i] = poblacion[i].getFitness();
        	j++;
        	j%=numSeleccionados;
        }
       /* for(int i = nseleccionados - numSeleccionados;i<nseleccionados;i++){
			poblacion[i] = seleccionados[j];
			fitness[i]=poblacion[i].getFitness();
			j++;
			j %= nseleccionados;
		}*/
		
        /*for(int i = numSeleccionados; i < nseleccionados; i++) {//Sustituye  los dos bucles for(y omite la lista de seleccionados)
        	poblacion[i]= poblacion[i%numSeleccionados].clonar();//Clonamos otra vez!!!
        	fitness[i] = poblacion[i].getFitness();
        	
        }
		*/



	}

	@Override
	public void restos(int nseleccionados) {//Edu (el resto simplemente haz que haga un random y tire por cualquiera de los otros métodos o hazlo fijo)
		double[] p = new double[nseleccionados];
		List<Individuo> seleccionados = new ArrayList<>(); 
		calculaPi(nseleccionados, p);
		
		for(int i = 0; i < nseleccionados; ++i) {
			int pik = (int)(p[i] * nseleccionados);
			if (pik > 0) 
				seleccionados.add(poblacion[i].clonar());
		}
		int nRestos = nseleccionados-seleccionados.size();
		if (seleccionados.size() < nseleccionados) {
			torneoProb(nRestos);
		}
		
		for (int i = nRestos; i < seleccionados.size(); i++) {
			poblacion[i] = seleccionados.get(i);
			fitness[i]=poblacion[i].getFitness();
		}
	}

	private void calculaPi(int nseleccionados, double[] p) {
		double sumFitness = 0;
		for(int i=0; i < nseleccionados; i++) {
			sumFitness += poblacion[i].getFitness();
		}
		
		for(int i=0; i < nseleccionados; i++) {
			p[i] = poblacion[i].getFitness()/sumFitness;
		}
	}

	private void corrigeMinimizar(double max, double[] cf){
		for(int i = 0; i < this.tamPoblacion; i++){
			cf[i] = Math.abs((1.05 * max)) - this.fitness[i];
		}
	}

	protected double getMaxFit(){
		double maxim = poblacion[0].getFitness();
		
		for (int i = 0; i < tamPoblacion; i++) {

			if (maxim < poblacion[i].getFitness()) {
				maxim = poblacion[i].getFitness();
			}
			
		}
		return maxim;
	}
	protected int getMaxFitInd(){
		double maxim = poblacion[0].getFitness();
		int ind=0;
		for (int i = 0; i < tamPoblacion; i++) {

			if (maxim < poblacion[i].getFitness()) {
				maxim = poblacion[i].getFitness();
				ind = i;
			}
			
		}
		return ind;
	}

	protected double getMinFit(){
		double minim = poblacion[0].getFitness();
		
		for (int i = 0; i < tamPoblacion; i++) {

			if (minim > poblacion[i].getFitness()) {
				minim = poblacion[i].getFitness();
			}
			
		}
		return minim;
	}
	

	
}
