package primenumbers.core.checker.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import primenumbers.core.checker.PrimeCheckResult;
import primenumbers.core.checker.PrimeChecker;

// general tests for instance of PrimeChecker

public abstract class PrimeCheckerTest {

	// override to test different implementations
	public abstract PrimeChecker createChecker();
	
	private PrimeChecker checker;
	
	private long[] examplePrimes = { 2, 3, 5, 7, 593, 1889, 3467, 29147 };
	private long[] exampleNonPrimes = { 0, 1, 9, 15, 35, 594, 4215, 10167, 38541 };

	@Before
	public void setup()
	{
		checker = createChecker();
	}
		
	@Test
	public void simpleCasesIsPrimeTest()
	{
		// assume negative numbers are not primes
		PrimeCheckResult res = checker.checkPrime(-1);
		assertThat(res.isPrime()).isFalse();
		assertThat(res.getFoundFactor()).isEqualTo(0);

		// 0 is not prime
		res = checker.checkPrime(0);
		assertThat(res.isPrime()).isFalse();
		assertThat(res.getFoundFactor()).isEqualTo(0);
		
		// 1 is not primes
		res = checker.checkPrime(1);
		assertThat(res.isPrime()).isFalse();
		assertThat(res.getFoundFactor()).isEqualTo(0);
		
		// 2 is the lowest prime
		res = checker.checkPrime(2);
		assertThat(res.isPrime()).isTrue();
	}
	
	@Test
	public void checkPrimePositiveTests()
	{
		for (long prime : examplePrimes)
		{
			PrimeCheckResult res = checker.checkPrime(prime);
			assertThat(res.isPrime()).isTrue();
			assertThat(res.isInError()).isFalse();
		}
	}
	
	@Test
	public void checkPrimeNegativeTests()
	{
		for (long prime : exampleNonPrimes)
		{
			PrimeCheckResult res = checker.checkPrime(prime);
			assertThat(res.isPrime()).isFalse();
			assertThat(res.isInError()).isFalse();
		}		
	}
}
