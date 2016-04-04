package primenumbers.core.list.tests;

import primenumbers.core.list.PrimeListGenerator;
import primenumbers.core.list.SieveListGenerator;

public class SieveListTest extends PrimeListTest {

	@Override
	public PrimeListGenerator createGenerator() {
		
		return new SieveListGenerator();
	}	
}
