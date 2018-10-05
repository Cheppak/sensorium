package ar.com.sac.observer;

import java.util.ArrayList;

/**
 * @author Cheppak
 *
 */
public class Monitor implements Observer {

	public int max;
	public int min;
	public ArrayList<Integer> history;
	
	private int s;
	private int m;
	
	public Monitor(int s, int m) {
		this.s = s;
		this.m = m;
	}
	
	@Override
	public void update(int value) {
		// Acciones a realizar cuando el sensor "detecta" un valor.
		history.add(value);
		if(value < min) min = value;
		if(value > max) max = value;
		checkDifferences();
		checkAvg();
	}

	
	/**
	 * El valor promedio sea superior a una constante "M"
	 */
	private boolean checkAvg() {
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
	private boolean checkDifferences() {
		return Math.abs(this.max - this.min) > this.s;
		
	}

}
