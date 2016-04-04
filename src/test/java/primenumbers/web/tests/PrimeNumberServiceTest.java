package primenumbers.web.tests;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import primenumbers.core.checker.PrimeCheckResult;
import primenumbers.web.MappedHandler;
import primenumbers.web.PrimeNumberList;
import primenumbers.web.PrimeNumberService;

// tests for service class and mapped handler

public class PrimeNumberServiceTest {

	private static final String NULL_ARGUMENT = "Null argument";
	private static final String CHECKER_NOT_INITIALISED = "Checker not initialised";
	private static final String GENERATOR_NOT_INITIALISED = "List generator not initialised";
	
	private PrimeNumberService service;
	
	private static final String CHECKER_NAME = MappedHandler.DEFAULT;
	
	@Before
	public void setup()
	{
		service = new PrimeNumberService(new DummyChecker(), new DummyListGenerator());
	}

	@Test
	public void HelpersAreNullTest() {
		
		final PrimeNumberService nullChecker = new PrimeNumberService(null, null);
		
		PrimeCheckResult checkResult = nullChecker.checkPrime("", CHECKER_NAME);
		
		assertThat(checkResult.getErrorMessage()).isEqualTo(CHECKER_NOT_INITIALISED);
		assertThat(checkResult.isInError()).isTrue();
		
		PrimeNumberList listResult = nullChecker.getPrimes("", CHECKER_NAME);

		assertThat(listResult.getErrorMessage()).isEqualTo(GENERATOR_NOT_INITIALISED);
		assertThat(checkResult.isInError()).isTrue();
	}
	
	@Test
	public void ArgumentsAreNullTest() {
				
		PrimeCheckResult checkResult = service.checkPrime(null, CHECKER_NAME);
		
		assertThat(checkResult.getErrorMessage()).isEqualTo(NULL_ARGUMENT);
		assertThat(checkResult.isInError()).isTrue();
		
		PrimeNumberList listResult = service.getPrimes(null, CHECKER_NAME);

		assertThat(listResult.getErrorMessage()).isEqualTo(NULL_ARGUMENT);
		assertThat(checkResult.isInError()).isTrue();
	}

	@Test
	public void ArgumentsAreNotNumbersTest() {
				
		// check empty arguments
		PrimeCheckResult checkResultEmpty = service.checkPrime("", CHECKER_NAME);
		PrimeNumberList listResultEmpty = service.getPrimes("", CHECKER_NAME);
		
		assertThat(checkResultEmpty.isInError()).isTrue();
		assertThat(listResultEmpty.isInError()).isTrue();

		// check letter arguments
		PrimeCheckResult checkResultText = service.checkPrime("Fred", CHECKER_NAME);
		PrimeNumberList listResultText = service.getPrimes("Bert", CHECKER_NAME);
		
		assertThat(checkResultText.isInError()).isTrue();
		assertThat(listResultText.isInError()).isTrue();

		// check mixed letter and number arguments
		PrimeCheckResult checkResultMixed = service.checkPrime("Fred43", CHECKER_NAME);
		PrimeNumberList listResultMixed = service.getPrimes("Bert72", CHECKER_NAME);
		
		assertThat(checkResultMixed.isInError()).isTrue();
		assertThat(listResultMixed.isInError()).isTrue();
	}
}
