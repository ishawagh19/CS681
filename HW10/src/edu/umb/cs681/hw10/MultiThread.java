package edu.umb.cs681.hw10;

public class MultiThread implements Runnable{
 
	public void run() {
		Aircraft aircraft=new Aircraft(new Position(42.5136,65.359,25.369));
		System.out.println("Aircraft's original position \t\t" +aircraft.getposition());
		aircraft.setPosition(aircraft.getposition().change(35.6598, 65.987, 30.00));
		System.out.println("Aircraft's original position changed to \t" +aircraft.getposition());
		aircraft.setPosition(new Position(40.2569,80.259,41.259));
		System.out.println("Aircraft's new position is set to:" +aircraft.getposition());
	}
	 
	
	
	
	
	public static void main(String[] args) {
	    	
	    	Thread T1 = new Thread(new MultiThread());
			Thread T2 = new Thread(new MultiThread());
			Thread T3 = new Thread(new MultiThread());
			Thread T4 = new Thread(new MultiThread());
			
			T1.start();
			T2.start();
			T3.start();
			T4.start();
			
			try {
				T1.join();
				T2.join();
				T3.join();
				T4.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}        
	    }
}
