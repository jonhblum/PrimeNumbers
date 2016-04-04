package primenumbers.web;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import primenumbers.core.checker.CleverChecker;
import primenumbers.core.checker.FunctionalChecker;
import primenumbers.core.checker.PrimeChecker;
import primenumbers.core.checker.SimpleChecker;
import primenumbers.core.list.FunctionalListGenerator;
import primenumbers.core.list.PrimeListGenerator;
import primenumbers.core.list.SieveListGenerator;
import primenumbers.core.list.SimplePrimeListGenerator;
import primenumbers.core.list.ThreadedSieveListGenerator;
 
// application class to run in embedded jetty instance
// runs on port 7070 by default

public class App {
 
	public static void main(String[] args) throws Exception {
		
    	ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        
        // default to port 7070
        int port = 7070;
        
        if (args.length > 0)
        {
        	try
        	{
        		port = Integer.parseInt(args[0]);
        	}
        	catch (NumberFormatException nfe)
        	{
        		// log error and carry on
        	}
        }
 
        Server jettyServer = new Server(port);
        jettyServer.setHandler(context);
        
        context.addServlet(new ServletHolder(new ServletContainer(resourceConfig())), "/*");
  
        try 
        {
            jettyServer.start();
            jettyServer.join();
        } 
        finally 
        {
            jettyServer.destroy();
        }
    }
    
	// setup the service instance
    private static ResourceConfig resourceConfig() {
    	
    	PrimeChecker cleverChecker = new CleverChecker();
    	PrimeChecker simpleChecker = new SimpleChecker();
    	PrimeChecker functionalChecker = new FunctionalChecker();
    	
    	PrimeListGenerator simpleListGenerator = new SimplePrimeListGenerator();
    	PrimeListGenerator sieveListGenerator = new SieveListGenerator();
    	PrimeListGenerator concurrentSieveListGenerator = new ThreadedSieveListGenerator();
    	PrimeListGenerator functionalListGenerator = new FunctionalListGenerator();
    	
    	PrimeNumberService service = new PrimeNumberService(cleverChecker, sieveListGenerator);

    	service.registerChecker("simple", simpleChecker);
    	service.registerChecker("clever", cleverChecker);
    	service.registerChecker("functional", functionalChecker);
    	
    	service.registerListGenerator("simple", simpleListGenerator);
    	service.registerListGenerator("sieve", sieveListGenerator);
    	service.registerListGenerator("concurrent", concurrentSieveListGenerator);
    	service.registerListGenerator("functional", functionalListGenerator);
    	
    	return new ResourceConfig().register(service);
    }
}