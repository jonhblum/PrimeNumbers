package primenumbers.core.checker;

// holds the result of a check for prime
// including the counter-example factor if the result is negative
// note: this class is immutable

public class PrimeCheckResult {

	private boolean isPrime;
	private long foundFactor;
	private String errorMessage;
	private boolean inError;
	
	// hold a static true as immutable
	private static PrimeCheckResult trueInstance = new PrimeCheckResult(true, 0);
	
	public static PrimeCheckResult createTrue()
	{
		return trueInstance;
	}

	public static PrimeCheckResult createFalse(long factor)
	{
		return new PrimeCheckResult(false, factor);
	}

	public static PrimeCheckResult createError(String errorMessage)
	{
		return new PrimeCheckResult(errorMessage);
	}
	
	private PrimeCheckResult(boolean isPrime, long foundFactor) {

		this.isPrime = isPrime;
		this.foundFactor = foundFactor;
		this.inError = false;
	}

	private PrimeCheckResult(String errorMessage) {

		this.isPrime = false;
		this.foundFactor = 0;
		this.errorMessage = errorMessage;
		this.inError = true;
	}
	
	public boolean isPrime() {
		return isPrime;
	}

	public long getFoundFactor() {
		return foundFactor;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isInError() {
		return inError;
	}
}
