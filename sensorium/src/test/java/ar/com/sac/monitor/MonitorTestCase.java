package ar.com.sac.monitor;

import ar.com.sac.observer.Monitor;
import ar.com.sac.subject.Sensor;
import junit.framework.TestCase;

public class MonitorTestCase extends TestCase{

	private static final int M = 4;
	private static final int S = 3;
	private Monitor monitor;
	private Sensor sensor1;
	private Sensor sensor2; 
	private Sensor sensor3; 
	private Sensor sensor4; 
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		this.monitor = new Monitor(S, M);
		sensor1 = new Sensor(1, monitor);
		sensor2 = new Sensor(2, monitor);
		sensor3 = new Sensor(3, monitor);
		sensor4 = new Sensor(4, monitor);
	}
	
	// Comprobamos que el monotor nos informa que la diferencia entre valor minimo (1) y el valor maximo (10) supera a s (3)		
	public void testMaxMinDifferences() {
		int min = 1;
		int max = 10;
		sensor1.setValue(3);
		sensor2.setValue(min);
		sensor3.setValue(max);
		sensor4.setValue(2);
		assertEquals(min, monitor.getMin());
		assertEquals(max, monitor.getMax());
		assertTrue(monitor.hasMaxMinDifferences());
	}
	
	// Comprobamos que el monitor nos informa que el promedio de sus elementos (10) es mayor a un valor M (4)
	public void testAvg() {
		int avg = 10;
		sensor1.setValue(avg);
		sensor2.setValue(avg);
		sensor3.setValue(avg);
		sensor4.setValue(avg);
		assertEquals(avg, monitor.getAvg());
		assertTrue(monitor.hasMaxAvg());
	}
	
}
