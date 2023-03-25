package main;

import Funciones.*;
import selecciones.*;
import cruces.*;
import mutaciones.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.lang.Math;

public class AlgoritmoGenetico{
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
            this.poblacion=new Individuo[getTamPoblacion()];
            this.fitness=new double[getTamPoblacion()];
            this.metodoSelec = ui.getSelec();
            this.maxGeneraciones = ui.getNum_gen();
            this.metodoCruce = ui.getCruce();
            this.probCruce = ui.getProb_cruce();
            this.metodoMut = ui.getMut();
            this.probMutacion= ui.getProb_mut();
            this.elitismo = ui.getElitismo();
            this.elite = new Individuo[(int)Math.ceil(getTamPoblacion()*elitismo)];
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
		for(int i = 0; i < getPoblacion()[pos_mejor].getNGen(); i++) {
			s+= "x" + i + " (" + getPoblacion()[pos_mejor].getFenotipo(i) + ") ";
		}
		return s;
	}

    private void introduceElite() {
    	this.ordenaDecreciente();
    	for(int i = 0; i < elite.length; i++) {
			getPoblacion()[getTamPoblacion() - 1 - i] = elite[i];
		}
    }
	private void generaElite() {
		this.ordenaDecreciente();
    	for(int i = 0; i < elite.length; i++) {
			elite[i] = getPoblacion()[i];
		}	
    }
	public void ordenaDecreciente() {
		Arrays.sort(getPoblacion(), new FitnessComparatorMin());//antes fitness comparator sin el min
    	for(int i = 0; i< getTamPoblacion(); i++)
    		getFitness()[i] *= -1;
    	Arrays.sort(getFitness());
    	for(int i = 0; i< getTamPoblacion(); i++)
    		getFitness()[i] *= -1;
	}
	
	private double getMeanFit() {
    	double ret = 0;
    	
    	for(int i = 0; i < this.getTamPoblacion(); i++) {
    		ret += getPoblacion()[i].getFitness(); 
    	}
    	ret /= this.getTamPoblacion();
    	return ret;
    }
	
	private void init_pob() {
	    for(int i =0; i< this.getTamPoblacion(); i++){
               getPoblacion()[i] = new IndividuoTSP(d, prec);
            }
	}

	private void ev_pob() {
		for (int i = 0; i< this.getTamPoblacion(); i++) {
			getPoblacion()[i].calculaFitness();//actualiza fitness
			getFitness()[i] = getPoblacion()[i].getFitness();
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
	    	Ruleta.ruleta(this,getTamPoblacion());
	    	break;
	    case "Torneo det.":
	    	Torneo.deterministico(this,getTamPoblacion());
	    	break;
	    case "Torneo prob.":
		    Torneo.probabilistico(this,getTamPoblacion());
		    break;
	    case "Estocástico universal":
	    	EstocasticoUniversal.estUniversal(this,getTamPoblacion());
	    	break;
	    case "Truncamiento":
		    Truncamiento.truncamiento(this,getTamPoblacion());
		    break;
	    case "Restos":
	    	Restos.restos(this,getTamPoblacion());
		    break;
	  }
		return getPoblacion();
	}
	
	public void cruce() {
		if (getPoblacion()[0].getTamTotal()>1) {
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
			Insercion.insercion();
			break;
		case "Intercambio":
			Intercambio.intercambio();
			break;
		case "Inversion":
			Inversion.inversion();
			break;
		case "heuristica":
			heuristica(funcion);
			break;
		case "MetodoPropio":
	    	metodopropioMUT(funcion);
	    	break;
		}
	}


	

	

	public void corrigeMinimizar(double max, double[] cf){
		for(int i = 0; i < this.getTamPoblacion(); i++){
			cf[i] = Math.abs((1.05 * max)) - this.getFitness()[i];
		}
	}

	public double getMaxFit(){
		double maxim = getPoblacion()[0].getFitness();
		
		for (int i = 0; i < getTamPoblacion(); i++) {

			if (maxim < getPoblacion()[i].getFitness()) {
				maxim = getPoblacion()[i].getFitness();
			}
			
		}
		return maxim;
	}
	protected int getMaxFitInd(){
		double maxim = getPoblacion()[0].getFitness();
		int ind=0;
		for (int i = 0; i < getTamPoblacion(); i++) {

			if (maxim < getPoblacion()[i].getFitness()) {
				maxim = getPoblacion()[i].getFitness();
				ind = i;
			}
			
		}
		return ind;
	}

	protected double getMinFit(){
		double minim = getPoblacion()[0].getFitness();
		
		for (int i = 0; i < getTamPoblacion(); i++) {

			if (minim > getPoblacion()[i].getFitness()) {
				minim = getPoblacion()[i].getFitness();
			}
			
		}
		return minim;
	}
	public Individuo[] getPoblacion() {
		return poblacion;
	}

	public double[] getFitness() {
		return fitness;
	}
	public int getTamPoblacion() {
		return tamPoblacion;
	}
	public double getProbCruce() {
		return probCruce;
	}
		
}
