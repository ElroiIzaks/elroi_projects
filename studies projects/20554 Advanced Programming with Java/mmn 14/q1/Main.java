package q1;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

		public static void main (String [] arg) {
			
			//3 sets of integers
			Set<Integer> set1 = new Set<Integer>();
			Set<Integer> set2 = new Set<Integer>();
			Set<Integer> set3 = new Set<Integer>();
		
			//3 sets with 10 random integers for each set
			for (int i = 0; i<10; i++) {
				set1.insert((int) (Math.random()*100));
				set2.insert((int) (Math.random()*100));
				set3.insert((int) (Math.random()*100));
			}
			
			//print the sets for introductions
			System.out.println("set 1:" + set1.toString());
			System.out.println("set 2:" + set2.toString());
			System.out.println("set 3:" + set3.toString());
		
			//union sets
			set1.union(set2);
			System.out.println("set 1 after union:" + set1.toString());
		
			//intersect set3 from set1
			set1.intersect(set3);
			System.out.println("set 1 after intersect:" + set1.toString());
		
			//check if subset
			System.out.println("please enter 2 numbers:");
			try (Scanner scan = new Scanner(System.in)) {
				int a = scan.nextInt();
				int b = scan.nextInt();
				
				Set<Integer> set4 = new Set<>();
				set4.insert(a);
				set4.insert(b);
				
				System.out.println("{"+a+"," + b + "} is " +  (set1.isSubset(set4)? "":"not ") + "part of set1");
				System.out.println("{"+a+"," + b + "} is " +  (set2.isSubset(set4)? "":"not ") + "part of set2");
				System.out.println("{"+a+"," + b + "} is " +  (set3.isSubset(set4)? "":"not ") + "part of set3");

				//do some actions with a new number
				System.out.println("please enter a number:");
				a = scan.nextInt();

				System.out.println(a + " is " +  (set1.isMember(a)? "":"not ") + "belong to set1");
				System.out.println("\nbefore entering "+ a + " to set2: " + set2);
				set2.insert(a);
				System.out.println("after entering "+ a + " to set2: " + set2);
				System.out.println("\nbefore deleting "+ a + " from set3: " + set3);
				set2.delete(a);
				System.out.println("after deleting "+ a + " from set3: " + set3);
		
				//the smallest from set of people
				Person p1 = new Person ("209382514", "Elroi", "Izaks", 1997);
				Person p2 = new Person ("205224512", "David", "Cohen", 2004);
				Person p3 = new Person ("454545408", "daniel", "Sasson", 2004);
				Person p4 = new Person ("576183354", "Yinon", "Etinger", 1992);
				Person p5 = new Person ("203484015", "Moshe", "Levy", 2008);
				Set <Person> setOfPeople = new Set<>();
				setOfPeople.insert(p1);
				setOfPeople.insert(p2);
				setOfPeople.insert(p3);
				setOfPeople.insert(p4);
				setOfPeople.insert(p5);

				System.out.println("\nsmallest:");
				System.out.println(smallest(setOfPeople));
			}

		}

		/**
		 * find the smallest <E> (comparable Object) by comparing
		 * @param someset the set of E's
		 * @return E the smallest E from the someset's set
		 */
		public static <E extends Comparable<E>> E smallest (Set<E> someset) {
			Iterator<E> iterator = someset.iterator();
			E min = iterator.next();
			E temp;
			while (iterator.hasNext()) {
				temp = iterator.next();
				if (temp.compareTo(min) < 0) min = temp;
			}
			return min;
		}
}
