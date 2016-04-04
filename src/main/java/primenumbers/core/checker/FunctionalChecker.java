package primenumbers.core.checker;

import static primenumbers.core.checker.PrimeCheckResult.*;

import java.util.OptionalLong;
import java.util.stream.LongStream;

// partially functional implementation of a prime checker

public class FunctionalChecker implements PrimeChecker {

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
		
		long max = (long) Math.sqrt(candidate);
		
		OptionalLong aFactor = LongStream.rangeClosed(2, max).parallel().filter(factor -> candidate % factor == 0).findAny();

		// if a factor was found it's not prime
		if (aFactor.isPresent())
		{
			return createFalse(aFactor.getAsLong());
		}
		else
		{
			return createTrue();
		}
	}

}
