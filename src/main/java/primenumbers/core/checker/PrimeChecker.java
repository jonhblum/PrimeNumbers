package primenumbers.core.checker;

// interface to check if a number is prime

@FunctionalInterface
public interface PrimeChecker {
	
	PrimeCheckResult checkPrime(long candidate);	
}
