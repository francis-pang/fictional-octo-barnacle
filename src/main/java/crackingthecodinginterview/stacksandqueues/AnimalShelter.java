package crackingthecodinginterview.stacksandqueues;

import java.util.ArrayList;
import java.util.List;

/**
 * An animal shelter, which holds only dogs and cats, operates on a strictly"first in, first out" basis. People must
 * adopt either the "oldest" (based on arrival time) of all animals at the shelter, or they can select whether they
 * would prefer a dog or a cat (and will receive the oldest animal of that type). They cannot select which specific
 * animal they would like. Create the data structures to maintain this system and implement operations such as
 * enqueue, dequeueAny, dequeueDog, and dequeueCat. You may use the built-in Linked List data structure.
 */
public class AnimalShelter {
  private List<AnimalNode> catList;
  private List<AnimalNode> dogList;
  private AnimalNode lastAddedAnimalNode;
  private AnimalNode firstAnimalNode;

  public AnimalShelter() {
    catList = new ArrayList<>();
    dogList = new ArrayList<>();
  }

  public void enqueue(Animal animal) {
    AnimalNode newAnimalNode = new AnimalNode(animal);

    if (animal.equals(Animal.CAT)) {
      catList.add(newAnimalNode);
    } else {
      dogList.add(newAnimalNode);
    }

    if (firstAnimalNode == null) {
      firstAnimalNode = newAnimalNode;
    }

    if (lastAddedAnimalNode != null) {
      newAnimalNode.previous = lastAddedAnimalNode;
      lastAddedAnimalNode.next = newAnimalNode;
    }
    lastAddedAnimalNode = newAnimalNode;
  }

  public Animal dequeueAny() {
    if (firstAnimalNode == null) {
      return null;
    }
    AnimalNode dequeueAnimalNode = firstAnimalNode;
    firstAnimalNode = firstAnimalNode.next;
    // This is guaranteed to be the first element
    if (dequeueAnimalNode.animal.equals(Animal.CAT)) {
      System.out.println("Index of this cat in the list:" + catList.indexOf(dequeueAnimalNode));
      catList.remove(dequeueAnimalNode);
    } else {
      System.out.println("Index of this dog in the list:" + dogList.indexOf(dequeueAnimalNode));
      dogList.remove(dequeueAnimalNode);
    }
    return dequeueAnimalNode.animal;
  }

  public Animal dequeueDog() {
    if (dogList.size() == 0) {
      return null;
    }
    AnimalNode dequeueAnimalNode = dogList.get(0);
    if (dequeueAnimalNode == firstAnimalNode) {
      firstAnimalNode = firstAnimalNode.next;
    }
    if (dequeueAnimalNode.next != null) {
      dequeueAnimalNode.next.previous = dequeueAnimalNode.previous;
    }
    if (dequeueAnimalNode.previous != null) {
      dequeueAnimalNode.previous.next = dequeueAnimalNode.next;
    }
    dogList.remove(dequeueAnimalNode);
    return dequeueAnimalNode.animal;
  }

  public Animal dequeueCat() {
    if (catList.size() == 0) {
      return null;
    }
    AnimalNode dequeueAnimalNode = catList.get(0);
    if (dequeueAnimalNode == firstAnimalNode) {
      firstAnimalNode = firstAnimalNode.next;
    }
    if (dequeueAnimalNode.next != null) {
      dequeueAnimalNode.next.previous = dequeueAnimalNode.previous;
    }
    if (dequeueAnimalNode.previous != null) {
      dequeueAnimalNode.previous.next = dequeueAnimalNode.next;
    }
    catList.remove(dequeueAnimalNode);
    return dequeueAnimalNode.animal;
  }

  public enum Animal {
    CAT, DOG
  }

  public class AnimalNode {
    public Animal animal;
    public AnimalNode previous;
    public AnimalNode next;

    public AnimalNode(Animal animal) {
      this.animal = animal;
    }
  }
}
