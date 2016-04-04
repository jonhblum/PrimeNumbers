package primenumbers.core.list;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicIntegerArray;

// multi-threaded sieve implementation

public class ThreadedSieveListGenerator implements PrimeListGenerator {
	
	@Override
	public List<Integer> getPrimes(int max) {
		
		if (max < 2)
		{
			return new ArrayList<>();
		}
		
		//Helper helper = new Helper(max);
		// 0 = prime
		// 1 = not prime
		// using integers as no library has no boolean version
		AtomicIntegerArray array = new AtomicIntegerArray(max+1);
		array.set(0, 1);
		array.set(1, 1);
		
		int limit = (int) Math.sqrt(max);
		
		// use 8 threads
		ExecutorService executor = Executors.newFixedThreadPool(8);
		
		// brake to stop overflow of threadpool queue, set at 16
		Semaphore brake = new Semaphore(16);
		
		for (int i = 2; i < limit; i++)
		{
			if (array.get(i) == 0)
			{
				final int factor = i;
				
				try {
					brake.acquire();
				} catch (InterruptedException e) {
					// this is only a brake so let this go
				}
				
				// execute sieve of one number factor on a thread
				executor.submit(
						() ->
						{
							for (int j = 2; factor * j <= max; j++)
							{
								array.set(factor*j, 1);
							}
							
							// let another thread start
							brake.release();
						});
			}
		}
		
		// shutdown executor
		boolean success = false;

		executor.shutdown();
		
		while (!success)
		{
			try
			{
				// wait up to a minute
				success = executor.awaitTermination(60,TimeUnit.SECONDS);
			}
			catch(InterruptedException ie)
			{
				// re-check in loop
			}
		}
		
		// gather up results
		List<Integer> results = new ArrayList<>();
		
		for (int i = 0; i <= max; i++)
		{
			if (array.get(i) == 0)
			{
				results.add(i);				
			}
		}
		
		return results;
	}
}
