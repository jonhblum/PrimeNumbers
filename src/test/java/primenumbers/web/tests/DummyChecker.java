package primenumbers.web.tests;

import static primenumbers.core.checker.PrimeCheckResult.*;

import primenumbers.core.checker.PrimeCheckResult;
import primenumbers.core.checker.PrimeChecker;

// dummy prime checker for unit testing web service
// always returns true
public class DummyChecker implements PrimeChecker {

	public PrimeCheckResult checkPrime(long candidate) {
		
		return createTrue();
	}
	
}
