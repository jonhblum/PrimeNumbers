package primenumbers.web;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import primenumbers.core.checker.PrimeCheckResult;
import primenumbers.core.checker.PrimeChecker;
import primenumbers.core.list.PrimeListGenerator;

import static primenumbers.core.checker.PrimeCheckResult.createError;

@Path("/prime-numbers")
public class PrimeNumberService {
	 
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
    		
        	return checker.checkPrime(candidateNumber);
    	}
    	catch (NumberFormatException nfe)
    	{
    		return createError(String.format("Error parsing argument: %s", nfe.getMessage()));
    	}    	
    	catch (Exception e)
    	{
    		return createError(String.format("Error in check prime: %s", e.getMessage()));
    	}    	
    }

    @GET
    @Path("/get-primes/{maximum}")
    @Produces(MediaType.APPLICATION_JSON)
    public PrimeNumberList getPrimes(
    		@PathParam("maximum") String maximum, 
    		@DefaultValue(MappedHandler.DEFAULT) @QueryParam("impl") String checkername) {

    	PrimeListGenerator generator = generators.getHandler(checkername);

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
    	    		
        	List<Integer> primeList = generator.getPrimes(maximumNumber);
        	
    		return new PrimeNumberList(primeList);
    	}
    	catch (NumberFormatException nfe)
    	{
    		return new PrimeNumberList(String.format("Error parsing argument: %s", nfe.getMessage()));
    	}    	
    	catch (Exception e)
    	{
    		return new PrimeNumberList(String.format("Error generating primes: %s", e.getMessage()));
    	}    	
    }
}
