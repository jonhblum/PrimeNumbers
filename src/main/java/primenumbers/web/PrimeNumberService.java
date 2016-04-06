package primenumbers.web;

import static primenumbers.core.checker.PrimeCheckResult.createError;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import primenumbers.core.checker.PrimeCheckResult;
import primenumbers.core.checker.PrimeChecker;
import primenumbers.core.list.PrimeListGenerator;

@Path("/prime-numbers")
public class PrimeNumberService {
	 
	private final Logger logger = LoggerFactory.getLogger(PrimeNumberService.class);
	
	private static final String NULL_ARGUMENT = "Null argument";
	private static final String CHECKER_NOT_INITIALISED = "Checker not initialised";
	private static final String LIST_GENERATOR_NOT_INITIALISED = "List generator not initialised";
	
	private MappedHandler<PrimeChecker> checkers;
	private MappedHandler<PrimeListGenerator> generators;
	
	public PrimeNumberService(PrimeChecker defaultChecker, PrimeListGenerator defaultGenerator) {
		
		checkers = new MappedHandler<PrimeChecker>(defaultChecker);
		generators = new MappedHandler<PrimeListGenerator>(defaultGenerator);
	}
	
	public void registerChecker(String name, PrimeChecker checker)
	{
		checkers.registerHandler(name, checker);
	}

	public void registerListGenerator(String name, PrimeListGenerator generator)
	{
		generators.registerHandler(name, generator);		
	}
	    
    @GET
    @Path("/check-prime/{candidate}")
    @Produces(MediaType.APPLICATION_JSON)
    public PrimeCheckResult checkPrime(
    		@PathParam("candidate")  String candidate, 
    		@DefaultValue(MappedHandler.DEFAULT) @QueryParam("impl") String checkername) {
    	
    	logger.info(String.format("Call to check prime, candidate: %s, impl: %s", candidate, checkername));
    	
    	PrimeChecker checker = checkers.getHandler(checkername);

    	if (checker == null)
    	{
    		return createError(CHECKER_NOT_INITIALISED);
    	}

    	if (candidate == null)
    	{
    		return createError(NULL_ARGUMENT);
    	}
    	
    	try
    	{    		
    		long candidateNumber = Long.parseLong(candidate);
    		
        	long start = System.currentTimeMillis();

        	PrimeCheckResult result = checker.checkPrime(candidateNumber);
        	
        	long end = System.currentTimeMillis();

        	logger.info(String.format("Call to check prime took %s ms", end-start));
        	
        	return result;
    	}
    	catch (NumberFormatException nfe)
    	{
    		String errorMessage = String.format("Error parsing argument: %s", nfe.getMessage());
    		
        	logger.error(errorMessage);

        	return createError(errorMessage);
    	}    	
    	catch (Exception e)
    	{
    		String errorMessage = String.format("Error in check prime: %s", e.getMessage());
    		
        	logger.error(errorMessage);

        	return createError(String.format(errorMessage));
    	}    	
    }

    @GET
    @Path("/get-primes/{maximum}")
    @Produces(MediaType.APPLICATION_JSON)
    public PrimeNumberList getPrimes(
    		@PathParam("maximum") String maximum, 
    		@DefaultValue(MappedHandler.DEFAULT) @QueryParam("impl") String listName) {

    	logger.info(String.format("Call to check prime, candidate: %s, impl: %s", maximum, listName));
    	    	
    	PrimeListGenerator generator = generators.getHandler(listName);

    	if (generator == null)
    	{
    		return new PrimeNumberList(LIST_GENERATOR_NOT_INITIALISED);
    	}

    	if (maximum == null)
    	{
    		return new PrimeNumberList(NULL_ARGUMENT);
    	}
    	
    	try
    	{
    		int maximumNumber = Integer.parseInt(maximum);

        	long start = System.currentTimeMillis();

        	List<Integer> primeList = generator.getPrimes(maximumNumber);
        	        	
        	long end = System.currentTimeMillis();

        	logger.info(String.format("Call to check prime took %s ms", end-start));

        	return new PrimeNumberList(primeList);
    	}
    	catch (NumberFormatException nfe)
    	{
    		String errorMessage = String.format("Error parsing argument: %s", nfe.getMessage());
    		
        	logger.error(errorMessage);
    		
    		return new PrimeNumberList(errorMessage);
    	}    	
    	catch (Exception e)
    	{
    		String errorMessage = String.format("Error generating primes: %s", e.getMessage());
    		
        	logger.error(errorMessage);

        	return new PrimeNumberList(errorMessage);
    	}    	
    }
}
