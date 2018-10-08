package ar.com.sac.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ar.com.sac.observer.Monitor;

@RestController
public class MonitorController {
	final static Logger logger = LogManager.getLogger(MonitorController.class);

	   @RequestMapping(value = "/sensor", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	   @ResponseStatus(HttpStatus.OK)
	   public void monitor(@RequestBody int value) {
		   // En teoria ya va a estar instanciada y corriendo si lo corremos por linea de comandos. La configuracion previa se hace en el "main". 
		   // Esa es la razon porque tuvimos que migrar a Singleton.
		   // TODO: podriamos llevarlo a un servicio. Por ahora lo dejamos asi...
		   logger.info("Se ha conectado un sensor mas via HTTP. nos dice que midio: [ " + value + " ]");
		   Monitor  monitor = Monitor.getInstance(100,100);
		   monitor.update(value);
	   }
}
