package primenumbers.core.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// use the sieve of Eratosthenes

public class SieveListGenerator implements PrimeListGenerator {

	@Override
	public List<Integer> getPrimes(int max) {
		
		// 2 is lowest prime
		if (max < 2)
		{
			return new ArrayList<>();
		}
		
		// true = prime
		boolean[] flags = new boolean[max + 1];
		
		Arrays.fill(flags, true);
		
		int limit = (int) Math.sqrt(max);
		
		// trivial non-primes
		flags[0] = false;
		flags[1] = false;
				
		for (int i = 2; i < limit; i++)
		{
			if (flags[i])
			{
				// if prime, then remove all multiples greater than *1
				for (int j = 2; i * j <= max; j++)
				{
					flags[i*j] = false;
				}				
			}
		}
		
		// collect up results
		List<Integer> results = new ArrayList<>();
		
		for (int i = 0; i <= max; i++)
		{
			if (flags[i] == true)
			{
				results.add(i);				
			}
		}
		
		return results;
	}
}
