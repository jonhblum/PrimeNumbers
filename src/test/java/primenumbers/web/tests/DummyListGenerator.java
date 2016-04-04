package primenumbers.web.tests;

import java.util.Arrays;
import java.util.List;

import primenumbers.core.list.PrimeListGenerator;

public class DummyListGenerator implements PrimeListGenerator {

	public List<Integer> getPrimes(int max) {
		
		return Arrays.asList(2, 3, 5, 7);
	}
	
}
