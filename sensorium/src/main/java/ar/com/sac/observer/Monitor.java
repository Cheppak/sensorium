package ar.com.sac.observer;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Monitor implements Observer {

	final static Logger logger = LogManager.getLogger(Monitor.class);
	
	private int max;
	private int min;
	private ArrayList<Integer> history= new ArrayList<Integer>();
	
	private int s;
	private int m;
	
	public Monitor(int s, int m) {
		this.s = s;
		this.m = m;
	}
	
	@Override
	public void update(int value) {
		// Acciones a realizar cuando el sensor "detecta" un valor.
		logger.info("Se ingresa el valor: " + value);
		
		if(getHistory().size() > 0 && value < getMin()) {
			this.setMin(value);
		} // la primera vez que se ingresa un numero no cuento con un minimo...
		else if(getHistory().size() == 0){
			this.setMin(value);
		}
			
			
		if(value > getMax()) setMax(value);
		getHistory().add(value);
		if(hasMaxMinDifferences()){
			logger.error("Se ha detectado una que el valor S es mayor a la diferencia absoluta entre maximo y minimo");
		}
		if(hasMaxAvg()) {
			logger.error("Se ha detectado que el promedio de elementos ingresados supera a un valor M");
		}
	}
	
	/**
	 * El valor promedio sea superior a una constante "M"
	 */
	public boolean hasMaxAvg() {
		int avg = getAvg();
		return this.m < avg; 
	}

	public int getAvg() {
		int sumAvg = 0;
		for (Integer integer : getHistory()) {
			sumAvg = sumAvg + integer;
		}
		int avg = sumAvg / getHistory().size();
		return avg;
	}

	/**
	 * La diferencia entre el valor minimo y maximo recibido sea mayor a una constante "S".
	 */
	public boolean hasMaxMinDifferences() {
		return Math.abs(this.getMax() - this.getMin()) > this.s;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public ArrayList<Integer> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<Integer> history) {
		this.history = history;
	}

}
