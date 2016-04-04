package primenumbers.core.list;

import java.util.ArrayList;
import java.util.List;

// simple implementation

public class SimplePrimeListGenerator implements PrimeListGenerator {

	@Override
	public List<Integer> getPrimes(int max) {
		
		if (max < 2)
		{
			return new ArrayList<>();
		}
		
		List<Integer> primes = new ArrayList<>();
		primes.add(2);
		
		for (int candidate = 3; candidate <= max; candidate += 2)
		{
			boolean isPrime = true;
			
			for (Integer prime : primes)
			{
				if (candidate % prime == 0)
				{
					isPrime = false;
					break;
				}
			}
			
			if (isPrime)
			{
				primes.add(candidate);
			}
		}
		
		return primes;
	}
}
