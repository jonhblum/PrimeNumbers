package primenumbers.core.list.tests;

import primenumbers.core.list.PrimeListGenerator;
import primenumbers.core.list.SimplePrimeListGenerator;

public class SimpleListTest extends PrimeListTest {

	@Override
	public PrimeListGenerator createGenerator() {
		
		return new SimplePrimeListGenerator();
	}	
}
