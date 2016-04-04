package primenumbers.core.list.tests;

import primenumbers.core.list.FunctionalListGenerator;
import primenumbers.core.list.PrimeListGenerator;

public class FunctionalListTest extends PrimeListTest {

	@Override
	public PrimeListGenerator createGenerator() {
		
		return new FunctionalListGenerator();
	}

}
