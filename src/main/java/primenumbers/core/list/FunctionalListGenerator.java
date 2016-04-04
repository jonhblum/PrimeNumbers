package primenumbers.core.list;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// functional implementation of simple list generator
// note: uses parallel stream

public class FunctionalListGenerator implements PrimeListGenerator {

	@Override
	public List<Integer> getPrimes(int max) {
		
		return IntStream.rangeClosed(2, max)
			.parallel()
			.filter(candidate -> IntStream.rangeClosed(2, (int) Math.sqrt(candidate))
					.noneMatch(factor -> candidate % factor == 0)
					).sorted().boxed().collect(Collectors.toList());
	}

}
