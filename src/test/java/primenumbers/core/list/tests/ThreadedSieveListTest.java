package primenumbers.core.list.tests;

import primenumbers.core.list.PrimeListGenerator;
import primenumbers.core.list.ThreadedSieveListGenerator;

public class ThreadedSieveListTest extends PrimeListTest {

	@Override
	public PrimeListGenerator createGenerator() {
		
		return new ThreadedSieveListGenerator();
	}	
}
