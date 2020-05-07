package com.vinegrad.test;

import com.vinegrad.functionals.Comparator;
import com.vinegrad.model.Person;

public class View {

	public static void main(String[] args) {
		Person[] people = {new Person("John", "Smith", 41), new Person("Joe", "Bloggs", 34), 
				new Person("Gill", "Smith", 51), new Person("Gill", "Smith", 24),
				new Person("Amy", "Watson", 37), new Person("Nick", "Bloggs", 34)};
		Comparator<Person> cLastName = Comparator.comparing(Person::getLastName);
		Comparator<Person> cFirstName = Comparator.comparing(Person::getFirstName);
		Comparator<Person> cAge = (p1, p2) -> p1.getAge() - p2.getAge();
		Comparator<Person> c = cLastName.thenComparing(cFirstName).thenComparing(cAge);
		//or can now do it this way
		Comparator<Person> c2 = 
				Comparator.comparing(Person::getLastName)
						.thenComparing(Person::getFirstName)
						.thenComparing(Person::getAge);
		Person[] pComp = sort(people, c2);
		for(Person p : pComp) {
			System.out.println(p);
		}
	}
	
	static <T> T[] sort(T[] arr, Comparator<T> comparator) {
		int size = arr.length;
		int price = 0;
		while(size != 0) {
			int swaps = 0;
			for(int i = 0; i < size - 1; i++) {
				price++;
				if(comparator.compare(arr[i], arr[i + 1]) > 0) {
					swaps++;
					T temp = arr[i];
					arr[i] = arr[i + 1];
					arr[i + 1] = temp;
				}
			}
			if(swaps == 0) {
				break;
			}
			size--;
		}
		System.out.println("Size = " + arr.length + ", Price = " + price);
		return arr;
	}
}
