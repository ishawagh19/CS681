package edu.umb.cs681.hw18;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.umb.cs681.hw18.PrimeFactorizer;

public class RunnablePrimeFactorizer extends PrimeFactorizer implements Runnable {

	public RunnablePrimeFactorizer(long dividend, long from, long to) {
		super(dividend);
		if (from >= 2 && to >= from) {
			this.from = from;
			this.to = to;
		} else {
			throw new RuntimeException("'From' should be >= 2, 'To' should be >= 'From'. From = " + from + " to = " + to);
		}
	}

	protected boolean numberIsEven(long number) {
		// TODO Auto-generated method stub
		if(number%2 == 0){ 
			return true; 
		}
		else{ 
			return false; 
		}
	}

	public void generatePrimeFactors() {
		
		long divisor = from;
		while (dividend != 1 && divisor <= to) {
			
			if (divisor > 2 && numberIsEven(divisor)) {
				divisor++;
				continue;
			}
			if (dividend % divisor == 0) {
				factors.add(divisor);
				dividend /= divisor;
			} 
			else if (divisor == 2) { divisor++; } 
			else { divisor += 2; }
		}
	}

	public void run() {
		generatePrimeFactors();
		System.out.println("Thread #" + Thread.currentThread().getId() + " found factors	" + factors 
				+ "	in the range [" + getFrom() + "," + getTo() + "]");
	}
	
	public static void main(String arg[]) {
		int i=2468;
		int j=1200;
		int k=900;
		System.out.println("The prime factors after the factorization of " + i + " using two fixed threads pools");
        ExecutorService executor = Executors.newFixedThreadPool(1);
        executor.execute(new RunnablePrimeFactorizer(i, 2, (long) Math.sqrt(i) / 2));
        executor.execute(new RunnablePrimeFactorizer(j, 2, (long) Math.sqrt(j) / 2));
        executor.execute(new RunnablePrimeFactorizer(k, 2, (long) Math.sqrt(k) / 2));

        executor.shutdown();
	}
	}