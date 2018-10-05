package ar.com.sac.observer;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Monitor implements Observer {

	final static Logger logger = LogManager.getLogger(Monitor.class);
	
	public int max;
	public int min;
	public ArrayList<Integer> history= new ArrayList<Integer>();
	
	private int s;
	private int m;
	
	public Monitor(int s, int m) {
		this.s = s;
		this.m = m;
	}
	
	@Override
	public void update(int value) {
		// Acciones a realizar cuando el sensor "detecta" un valor.
		logger.warn(value);
		history.add(value);
		if(value < min) min = value;
		if(value > max) max = value;
		if(hasMaxMinDifferences()){
			logger.error("");
		}
		if(hasMaxAvg()) {
			
		}
	}
	
	/**
	 * El valor promedio sea superior a una constante "M"
	 */
	public boolean hasMaxAvg() {
		int sumAvg = 0;
		for (Integer integer : history) {
			sumAvg = sumAvg + integer;
		}
		int avg = sumAvg / history.size();
		return this.m > avg; 
	}

	/**
	 * La diferencia entre el valor minimo y maximo recibido sea mayor a una constante "S".
	 */
	public boolean hasMaxMinDifferences() {
		return Math.abs(this.max - this.min) > this.s;
	}

}
