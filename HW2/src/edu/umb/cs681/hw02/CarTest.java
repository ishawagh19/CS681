package edu.umb.cs681.hw02;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;



public class CarTest 
{
	public static void main(String[] args) throws InterruptedException 
	{
		ArrayList<Car> cars1 = new ArrayList<Car>();
		cars1.add(new Car("Jeep",2012,55000,9000));
		cars1.add(new Car("Benz",2004,54000,5000));
		cars1.add(new Car("BMW   ",2011,35000,10000));
		cars1.add(new Car("Dzire",2007,10000,7000));
		cars1.add(new Car("Audi  ",2016,50000,13000));
		System.out.println("Brand    Year    Mileage   Price");
		System.out.println("________________________________");
		for(Car c: cars1){
			System.out.println(c+"     "+c.get_Mileage()+"       "+c.get_Year()+"   "+c.get_Price());
		}
//3.1 min(), max() and count() with reduce()
		System.out.println("\n \n HW2- implementation of min(), max() and count() with reduce() \n");
		Integer price1 = cars1.stream().map((Car car)-> car.get_Price())
				.min( Comparator.comparing(( price)-> price) ).get();
		
		Integer price2 = cars1.stream().map((Car car)-> car.get_Price())
				.max( Comparator.comparing(( price)-> price) ).get();
	
		
		Optional<Integer> price = cars1.stream().map((Car car)-> car.get_Price())
				.reduce((result, carPrice)->{
				if(result.equals(0)) return carPrice;
				else if(carPrice.compareTo(result) == -1) return carPrice;
				else return result;} );
		
		//max
		Optional<Car> car_max_brand = 
				cars1.stream()
	            .collect(Collectors.maxBy(Comparator.comparing(Car::get_Price)));
	    //min
		Optional<Car> car_min_brand = 
				cars1.stream()
	            .collect(Collectors.minBy(Comparator.comparing(Car::get_Price)));
	   
	    //Count
		
		Integer Count = cars1.stream().map( (Car car)-> car.get_Price() )
					. reduce(0, (result, carPrice)->{return ++result;} );
	
	    //Printing the values of min, max and count:
		System.out.println("\nMin price of car:---> $" + price1+" "+car_min_brand.get());
	    System.out.println("\nMax price of car:---> $" + price2+" "+car_max_brand.get());
		System.out.println("\nTotal number of brands are:---> " +Count);
		
	
//3.2 average with 3rd version of reduce :
		System.out.println("\n \n HW2- Coumpute the average with the 3rd version of reduce() \n");
		int[] avg = cars1.stream().map((Car car)-> car.get_Price() )
				. reduce(new int[3],
						(arr, prices)->{arr[0] = 1+arr[0];
										arr[1]=prices+ arr[1];
										if(arr[0]!=0)
											arr[2] = arr[1]/arr[0];
							return arr; },
							(arr, intermediateArr)->{ return arr; });
		//Printing the values of average:
		System.out.println("\n Average car price is:---> " +avg[2]);
	    
	   
	
	}

}