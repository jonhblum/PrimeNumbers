package primenumbers.core.checker;

import static primenumbers.core.checker.PrimeCheckResult.*;

// basic checker

public class SimpleChecker implements PrimeChecker {

	@Override
	public PrimeCheckResult checkPrime(long candidate) {
		
		// assumption: negative numbers are not prime
		if (candidate < 2)
		{
			return createFalse(0);
		}
		
		if (candidate == 2)
		{
			return createTrue();
		}
		
		// check if divisible by 2 as it's the only even prime
		if (candidate % 2 == 0)
		{
			return createFalse(2);
		}
				
		// only worth testing up to square root
		long squareRoot = (long) Math.sqrt(candidate);

		// skip even numbers as they are trivially non-prime
		for (long factor = 3; factor <= squareRoot; factor += 2)
		{
			if (candidate % factor == 0)
			{
				return createFalse(factor);
			}
		}
		
		return createTrue();
	}
}
