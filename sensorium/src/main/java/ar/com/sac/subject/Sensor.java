package ar.com.sac.subject;

import java.util.ArrayList;
import java.util.List;

import ar.com.sac.observer.Observer;

public class Sensor implements Subject {
	
	private List<Observer> observers = new ArrayList<Observer>();
	int value;
	
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

}
