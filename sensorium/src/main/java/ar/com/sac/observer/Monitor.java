package ar.com.sac.observer;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Les llegan los valores que captan los sensores y actua en consecuencia
 * @author Cheppak
 *
 */
public class Monitor extends Thread implements Observer {

	final static Logger logger = LogManager.getLogger(Monitor.class);
	
	private static Monitor monitor;
	
	private int max;
	private int min;
	private ConcurrentLinkedQueue<Integer> history = new ConcurrentLinkedQueue<Integer>();
	
	private int s;
	private int m;
	
	private Monitor(int s, int m) {
		this.s = s;
		this.m = m;
	}
	
	public static Monitor getInstance(int s, int m) {
		if(monitor == null) {
			monitor = new Monitor(s, m);
		}
		return monitor;
	}
	
	@Override
	public void update(int value) {
		// Acciones a realizar cuando el sensor "detecta" un valor.
		logger.info("Se ingresa el valor [ " + value + " ]");
		if(getHistory().size() > 0 && value < getMin()) {
			this.setMin(value);
		} // la primera vez que se ingresa un numero lo cuento con el minimo (caso base)
		else if(getHistory().size() == 0){
			this.setMin(value);
		}
		if(value > getMax()) {
			setMax(value);
		}
		getHistory().add(value);
	}
	
	@Override
	public void run() { 
		logger.info("Arrancando monitor");
		while(true) {
			if(!getHistory().isEmpty()) {
				printQueue();
				Integer removeValue = getHistory().remove();
				logger.info("se ha procesado el valor [ " + removeValue + " ]");
				if(hasMaxMinDifferences()){
					logger.error("Se ha detectado que el valor S ( " + this.s +" ) es menor a la diferencia absoluta entre maximo y minimo ");
				}
				if(hasMaxAvg()) {
					logger.error("Se ha detectado que el promedio de elementos ingresados supera a un valor M " + "( " + this.m +" )");
				}
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				logger.error("Error de programa (sleep)");
			}
		}
	}

	private void printQueue() {
		logger.info("Contenido del historico a procesar");
		for (Integer integer : history) {
			System.out.print(" | " +  integer );
		}
		System.out.println("");
	}
	
	/**
	 * El valor promedio sea superior a una constante "M"
	 */
	public boolean hasMaxAvg() {
		int avg = getAvg();
		logger.info("Valor promedio calculado: " + avg);
		return this.m < avg; 
	}

	public int getAvg() {
		int sumAvg = 0;
		for (Integer integer : getHistory()) {
			sumAvg = sumAvg + integer;
		}
		if(!getHistory().isEmpty()) {
			return sumAvg / getHistory().size();
		}else{
			return sumAvg;
		}
	}

	/**
	 * La diferencia entre el valor minimo y maximo recibido sea mayor a una constante "S".
	 */
	public boolean hasMaxMinDifferences() {
		int difference = this.getMax() - this.getMin();
		logger.info("Diferencia entre maximo y minimo: "+ this.getMax() + " - "+ this.getMin( )+ " = "  + difference);
		return Math.abs(difference) > this.s;
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

	public ConcurrentLinkedQueue<Integer> getHistory() {
		return history;
	}

	public void setHistory(ConcurrentLinkedQueue<Integer> history) {
		this.history = history;
	}

}
