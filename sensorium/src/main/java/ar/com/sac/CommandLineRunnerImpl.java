package ar.com.sac;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import ar.com.sac.observer.Monitor;
import ar.com.sac.subject.Sensor;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner{

	final static Logger logger = LogManager.getLogger(CommandLineRunnerImpl.class);

	
	@Override
	public void run(String... args) throws Exception {
	
		argsValidations(args);
		
		int s = Integer.parseInt(args[1]);
		int m = Integer.parseInt(args[0]);
		Monitor monitor = Monitor.getInstance(s, m);
		Sensor s1 = new Sensor(1, monitor);
		Sensor s2 = new Sensor(2, monitor);
		Sensor s3 = new Sensor(3, monitor);
		Sensor s4 = new Sensor(4, monitor);
		s1.start();
		s2.start();
		s3.start();
		s4.start();
		monitor.start();
	}
	
	
	/**
	 * @param args M y S deben ser numeros enteros positivos
	 */
	private void argsValidations(String... args) {
		int m = 0;
		int s = 0;
		
		if(args.length != 2) {
			logger.error("Ha ingresado una cantidad incorrecta de argumentos. Recuerde que debe establecer los parámetros M y S");
			System.exit(0);
		}
		try {
			m = Integer.parseInt(args[0]);
			s = Integer.parseInt(args[1]);
		}catch(NumberFormatException e) {
			logger.error("Ha ingresado un parametro con formato inválido. M y S deben ser enteros");
			System.exit(0);
		}
		
		if(m < 0 || s < 0) {
			logger.error("M y S deben ser positivos");
			System.exit(0);
		}
	}
	
}
