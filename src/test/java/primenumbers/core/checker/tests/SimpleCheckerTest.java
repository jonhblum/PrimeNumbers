package primenumbers.core.checker.tests;

import primenumbers.core.checker.PrimeChecker;
import primenumbers.core.checker.SimpleChecker;

public class SimpleCheckerTest extends PrimeCheckerTest {

	@Override
	public PrimeChecker createChecker() {
		
		return new SimpleChecker();
	}
	
}
