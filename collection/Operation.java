package ir.freeland.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import ir.freeland.collection.model.Address;
import ir.freeland.collection.model.Person2;

public class Operation {
	public static void main(String[] args) {
		
		Address add1 = new Address("zanjan","iran");
		Address add2 = new Address("tehran","iran");
		Address add3 = new Address("toronto","canada");
		Address add4 = new Address("melborn","australia");
		
		Person2 bahareh = new Person2("bahareh","bagheri", 28 , add1 ); 
		Person2 nikan = new Person2("nikan","nikkhah", 3 , add2);
		Person2 mostafa = new Person2("mostafa","nikkhah", 37 , add2);
		Person2 parsa = new Person2("parsa","bagheri", 19 , add3);
		Person2 nastaran = new Person2("nastaran","bagheri", 26 , add4); 
		Person2 roniya = new Person2("roniya","mohammadi", 13 , add4);
		
		List<Person2> persons = new ArrayList<>();
		persons.add(bahareh);
		persons.add(nikan);
		persons.add(mostafa);
		persons.add(parsa);
		persons.add(nastaran);
		persons.add(roniya);
		
		System.out.println("persons before sorting by city name: \n" + persons);
		
		//sorting
		Collections.sort(persons, new Comparator<Person2>() {

			@Override
			public int compare(Person2 o1, Person2 o2) {
			
				return o1.getAddress().getCity().compareTo(o2.getAddress().getCity());
			}
	    });
		
		System.out.println("Persons after sorting by city name: \n" + persons);
		
		//partitioning
		 Map<Boolean, List<Person2>> partitioned = persons.stream()
		            .collect(Collectors.partitioningBy(person -> person.getAge() < 18));

		        List<Person2> under18 = partitioned.get(true);
		        List<Person2> over18 = partitioned.get(false);

		        System.out.println("Persons under 18: \n" + under18);
		        System.out.println("Persons 18 and over: \n" + over18);
		        
		        //grouping by city
		        Map<String, List<Person2>> groupedByCity = persons.stream()
		                .collect(Collectors.groupingBy(person -> person.getAddress().getCity()));

		           
		            groupedByCity.forEach((city, people) -> {
		                System.out.println("City: " + city);
		                people.forEach(System.out::println);
		            });
		            
		            //age average
		            double averageAge = persons.stream()
		                    .mapToInt(Person2::getAge) // Convert to IntStream of ages
		                    .average() // Calculate average
		                    .orElse(0.0); // Default value if the stream is empty

		                System.out.println("Average age: " + averageAge);
}
}
