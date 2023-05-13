package main;

import javax.swing.*;
import org.math.plot.*;

import java.awt.BorderLayout;
import java.awt.Color;

public class LinePlot {

	static private JPanel f;
	static private Plot2DPanel plot;
	
	public LinePlot() {

		// create your PlotPanel (you can use it as a JPanel)
		plot = new Plot2DPanel();
		
		// put the PlotPanel in a JFrame like a JPanel
		f = new JPanel();
		f.setLayout(new BorderLayout());
		f.add(plot);
		f.setSize(600, 600);
		f.add(plot);
	}
	
	public static void print(double[] generaciones, double[] fitness,double[] minfitness,double[] meanfitness) {
		plot.removeAllPlots();
		
		plot.addLegend("SOUTH");
		// add a line plot to the PlotPanel
		plot.addLinePlot("Mejor generacion",Color.red, generaciones, fitness);//linea roja
		plot.addLinePlot("Mejor absoluto",Color.blue, generaciones, minfitness);//linea azul
		plot.addLinePlot("Mejor absoluto",Color.blue, generaciones, minfitness);//linea azul
		plot.addLinePlot("Media",Color.green, generaciones, meanfitness);//linea verde

	}
	
	public JPanel getPanel() {
		return f;
	}
	
	
}