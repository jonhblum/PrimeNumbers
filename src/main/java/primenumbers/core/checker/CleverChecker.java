package primenumbers.core.checker;

import static primenumbers.core.checker.PrimeCheckResult.*;

// uses the fact all primes above 3 are of the form 6K+1 or 6K-1

public class CleverChecker implements PrimeChecker {

	@Override
	public PrimeCheckResult checkPrime(long candidate) {
		
		// assumption: negative numbers are not prime
		if (candidate < 2)
		{
			return createFalse(0);
		}
		
		// 2 and 3 are trivially primes
		if (candidate == 2 || candidate == 3)
		{
			return createTrue();
		}
		
		// check if divisible by 2 and 3
		if (candidate % 2 == 0)
		{
			return createFalse(2);
		}

		if (candidate % 3 == 0)
		{
			return createFalse(3);
		}
		
		// only worth testing up to square root
		long squareRoot = (long) Math.sqrt(candidate);

		// all primes are of form 6K-1 or 6K+1 except 2 and 3		
		for (long factor = 5; factor <= squareRoot; factor += 6)
		{
			if (candidate % factor == 0)
			{
				return createFalse(factor);
			}

			if (candidate % (factor + 2) == 0)
			{
				return createFalse(factor);
			}
		}
		
		return createTrue();
	}
}
