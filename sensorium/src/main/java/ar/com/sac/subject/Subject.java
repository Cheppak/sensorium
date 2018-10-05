package ar.com.sac.subject;

import ar.com.sac.observer.Observer;

public interface Subject {

	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObservers();
}
