package ar.com.sac.monitor;

import ar.com.sac.observer.Monitor;
import ar.com.sac.subject.Sensor;
import junit.framework.TestCase;

public class MonitorTestCase extends TestCase{

	public void testShowMe() {
		Monitor monitor = new Monitor(3,4);
		Sensor sensor1 = new Sensor();
		sensor1.registerObserver(monitor);
		Sensor sensor2 = new Sensor();
		sensor2.registerObserver(monitor);
		Sensor sensor3 = new Sensor();
		sensor3.registerObserver(monitor);
		Sensor sensor4 = new Sensor();
		sensor4.registerObserver(monitor);
	}
	
}
