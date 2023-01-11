package q1;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		Owner pensionOwner = new Owner("elroi", "054-541-7528");
		ArrayList<Animal> pension = new ArrayList<Animal>();

		// give properties to some animals
		Animal newAnimal = new Cat("mitzy", 3, "black", pensionOwner);
		pension.add(newAnimal);
		newAnimal = new Chicken("paka-paka", 1, "red", pensionOwner);
		pension.add(newAnimal);
		newAnimal = new Chicken("karbolet", 4, "white", pensionOwner);
		pension.add(newAnimal);
		newAnimal = new Snake("massster", 3, "black");
		pension.add(newAnimal);
		newAnimal = new Dog("raphy", 12, "gold", pensionOwner);
		pension.add(newAnimal);
		newAnimal = new Turtle("slowy", 1, "green", pensionOwner);
		pension.add(newAnimal);
		newAnimal = new Eagle("hoaky", 8, "gold");
		pension.add(newAnimal);
		newAnimal = new Cat("hatul", 7, "blue", pensionOwner);
		pension.add(newAnimal);

		// active functions with the animals
		for (Animal animal : pension) {
			System.out.println(animal);
			animal.eat();
			animal.sleep();
			if (animal instanceof Bird) {
				((Bird) animal).fly();
			}
			if (animal instanceof Reptile) {
				((Reptile) animal).crawl();
			}
			System.out.println();
		}

		// check if the owner is not the same as object when cloning
		Owner elroi = new Owner("me", "054-545-4545");
		Cat miau = new Cat("miau", 3, "black", elroi);
		Cat miauYau;
		miauYau = miau.clone();
		System.out.println(miau);
		System.out.println(miauYau);
		System.out.println();
		elroi.setOwnerName("izaks");
		elroi.setOwnerPhoneNumber("045-454-5454");
		System.out.println(miau);
		System.out.println(miauYau);

	}
}
