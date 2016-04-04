PrimeNumbers - Jonathan Blum

This application is written in Java and requires Maven to build and Java 1.8 compiler and JVM.

It runs an embedded Jetty server, providing a Jersey based REST Service.

The results are provided as simple JSON objects.

- To build the code, test and package use Maven, i.e. mvn package

- To run the service on Windows:
	start a command window
	cd to the root of this project
	use the command runService.bat
	
Note: the default port is 7070. To use an alternative specify it as the first argument to runService.bat.

- To run on an alternative operating system just run the full command line explicitly:

java -classpath target\primes-0.0.1-SNAPSHOT-jar-with-dependencies.jar primenumbers.web.App <optional port>

- To test the service

Open up a browser window (e.g. Chrome).

Go to the addresses (or alternative port if set):

To check if a number 19 is a prime:

	http://localhost:7070/prime-numbers/check-prime/19

To get a list of the primes equal to or below 21:	
	
	http://localhost:7070/prime-numbers/get-primes/21
	
You can also choose the algorithm used. To specify this add the query paramter "impl". The choices are:

	check-prime:
		simple
		clever
		functional
		
	get-primes:
		simple
		sieve
		concurrent
		functional
		
Note: the defaults are clever and sieve respectively.

e.g.

	http://localhost:7070/prime-numbers/get-primes/21?impl=concurrent
	

	