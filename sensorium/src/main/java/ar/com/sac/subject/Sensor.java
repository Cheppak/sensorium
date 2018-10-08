package ar.com.sac.subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ar.com.sac.observer.Monitor;
import ar.com.sac.observer.Observer;

/**
 * Registra los datos generados y notifica al monitor
 * @author Cheppak
 *
 */
public class Sensor extends Thread implements Subject {
	
	final static Logger logger = LogManager.getLogger(Sensor.class);

	private List<Observer> observers = new ArrayList<Observer>();
	int value;
	private long id;
	
	public Sensor(int id, Monitor monitor) {
		this.id = id;
		this.registerObserver(monitor);
	}
	
	@Override
	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		this.observers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for(Observer o : observers){
			o.update(this.value);
		}
	}
	
	public void setValue(int value) {
		this.value = value;
		this.notifyObservers();
	}

	/* (non-Javadoc)
	 * El sensor medira valores (genera numeros aleatoreos), que seran informados al monitor... Es capaz de generar 2 mediciones por segundo.
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		
		logger.info("Iniciando el sensor " + id);
		while(true) {
			int randomInt = new Random().nextInt(100);
			logger.info("El sensor " + id + " ha detectado el valor " + randomInt);
			setValue(randomInt);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				logger.error("Error en thread sleep");
			}
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
