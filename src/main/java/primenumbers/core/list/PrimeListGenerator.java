package primenumbers.core.list;

import java.util.List;

// interface for generating a list of primes
// from 2 up to the prime less than or equal to max

@FunctionalInterface
public interface PrimeListGenerator {

	public List<Integer> getPrimes(int max);
	
}
