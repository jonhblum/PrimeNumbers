package primenumbers.core.list.tests;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import primenumbers.core.list.PrimeListGenerator;

// general tests for instance of PrimeListGenerator

public abstract class PrimeListTest {

	// override to test different implementations
	public abstract PrimeListGenerator createGenerator();
	
	@Before
	public void setup()
	{
		primeListGenerator = createGenerator();
	}
	
	protected PrimeListGenerator primeListGenerator;
	
	private Integer[] PRIMES_UNDER_TWENTY = {2,3,5,7,11,13,17,19};
	
	@Test
	public void edgeCasesTest()
	{
		List<Integer> primes = primeListGenerator.getPrimes(-5);
		
		assertThat(primes).isEmpty();
		
		primes = primeListGenerator.getPrimes(0);
		
		assertThat(primes).isEmpty();		

		primes = primeListGenerator.getPrimes(1);
		
		assertThat(primes).isEmpty();
		
		primes = primeListGenerator.getPrimes(2);
		
		assertThat(primes).isEqualTo(Arrays.asList(2));	
	}
	
	@Test
	public void listPrimesTest()
	{
		// should be inclusive list, so test under 19
		List<Integer> primes = primeListGenerator.getPrimes(19);
		
		assertThat(primes).isEqualTo(Arrays.asList(PRIMES_UNDER_TWENTY));
	}
}
