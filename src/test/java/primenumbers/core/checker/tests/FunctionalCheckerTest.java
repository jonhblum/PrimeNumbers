package primenumbers.core.checker.tests;

import primenumbers.core.checker.FunctionalChecker;
import primenumbers.core.checker.PrimeChecker;

public class FunctionalCheckerTest extends PrimeCheckerTest {

	@Override
	public PrimeChecker createChecker() {
		
		return new FunctionalChecker();
	}
	
}
