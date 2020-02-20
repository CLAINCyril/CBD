package exception;


import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Loggin {
	private static final Logger logCons =LoggerFactory.getLogger(Loggin.class);
	private static final Logger logFile =LoggerFactory.getLogger(Loggin.class);
	
	public static void display(String msg)
	{
		PropertyConfigurator.configure(Loggin.class.getClassLoader().getResource("log4j.properties"));
		logCons.error(msg);
	}
}