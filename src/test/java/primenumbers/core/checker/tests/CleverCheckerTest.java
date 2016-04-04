package primenumbers.core.checker.tests;

import primenumbers.core.checker.CleverChecker;
import primenumbers.core.checker.PrimeChecker;

public class CleverCheckerTest extends PrimeCheckerTest {

	@Override
	public PrimeChecker createChecker() {
		
		return new CleverChecker();
	}
	
}
