package main;

import javax.swing.*;
import org.math.plot.*;

import java.awt.BorderLayout;
import java.awt.Color;

public class LinePlot {
	static private double[] generaciones;
	static private double[] fitness;
	static private double[] meanfitness;
	static private double[] maxfitness;
	static private JPanel f;
	static private Plot2DPanel plot;
	
	public LinePlot(int tamPob, int numgen) {

		// create your PlotPanel (you can use it as a JPanel)
		plot = new Plot2DPanel();
		
		generaciones = new double[numgen];
		fitness= new double[numgen];
		meanfitness= new double[numgen];
		maxfitness= new double[numgen];
		// put the PlotPanel in a JFrame like a JPanel
		f = new JPanel();
		f.setLayout(new BorderLayout());
		f.add(plot);
		f.setSize(600, 600);
		f.add(plot);
	}
	
	public static void print() {
		plot.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		plot.addLinePlot("Mejor generacion",Color.red, generaciones, fitness);//linea roja
		plot.addLinePlot("Mejor absoluto",Color.blue, generaciones, maxfitness);//linea azul
		plot.addLinePlot("Media",Color.green, generaciones, meanfitness);//linea verde

	}
	
	public JPanel getPanel() {
		return f;
	}
	
	public static void setCoords(int i, double fitness) {
		generaciones[i] = i;
		LinePlot.fitness[i] = fitness;
	}
	public static void setBestCoords(int i, double fitness) {

		LinePlot.maxfitness[i] = fitness;
	}
	public static void setMeanCoords(int i, double fitness) {

		LinePlot.meanfitness[i] = fitness;
	}
}