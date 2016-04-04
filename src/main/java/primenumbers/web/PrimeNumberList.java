package primenumbers.web;

import java.util.List;

// add error information to list of primes result

public class PrimeNumberList {

	private List<Integer> primes;
	private boolean inError;
	private String errorMessage;
	
	public PrimeNumberList(List<Integer> primes) {
		this.primes = primes;
		this.inError = false;
		this.errorMessage = null;
	}

	public PrimeNumberList(String errorMessage) {
		this.primes = null;
		this.inError = true;
		this.errorMessage = errorMessage;
	}

	public List<Integer> getPrimes() {
		return primes;
	}

	public boolean isInError() {
		return inError;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}
