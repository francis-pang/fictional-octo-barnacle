package crackingthecodinginterview.stacksandqueues;

import crackingthecodinginterview.stacksandqueues.AnimalShelter.Animal;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class AnimalShelterTest {
  /**
   * Test cases:
   * 1. 1 cat 1 dog
   * 2. 1 cat
   * 3. 1 dog
   * 4. 4 cat
   * 5. 3 dog
   * 6. 4 cat, 5 dog; sequence: cat, cat, dog, dog, cat, dog, cat, dog, dog
   * 7. 3 cat, 3 dog; sequence: cat, cat, cat, dog, dog, dog
   */

  private AnimalShelter build1Cat1Dog() {
    AnimalShelter animalShelter = new AnimalShelter();
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.DOG);
    return animalShelter;
  }

  private AnimalShelter build1Cat() {
    AnimalShelter animalShelter = new AnimalShelter();
    animalShelter.enqueue(Animal.CAT);
    return animalShelter;
  }

  private AnimalShelter build1Dog() {
    AnimalShelter animalShelter = new AnimalShelter();
    animalShelter.enqueue(Animal.DOG);
    return animalShelter;
  }

  private AnimalShelter build4Cats() {
    AnimalShelter animalShelter = new AnimalShelter();
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.CAT);
    return animalShelter;
  }

  //4 cat, 5 dog; sequence: cat, cat, dog, dog, cat, dog, cat, dog, dog
  private AnimalShelter build4Cats5Dogs() {
    AnimalShelter animalShelter = new AnimalShelter();
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.DOG);
    animalShelter.enqueue(Animal.DOG);
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.DOG);
    animalShelter.enqueue(Animal.CAT);
    animalShelter.enqueue(Animal.DOG);
    animalShelter.enqueue(Animal.DOG);
    return animalShelter;
  }

  @Test
  void test1Cat1Dog() {
    AnimalShelter animalShelter = build1Cat1Dog();
    assertEquals(Animal.CAT, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueCat());
    assertEquals(Animal.DOG, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueCat());
    assertNull(animalShelter.dequeueDog());
    assertNull(animalShelter.dequeueAny());
  }

  @Test
  void test1Cat() {
    AnimalShelter animalShelter = build1Cat();
    assertNull(animalShelter.dequeueDog());
    assertEquals(Animal.CAT, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueCat());
    assertNull(animalShelter.dequeueDog());
    assertNull(animalShelter.dequeueAny());
  }

  @Test
  void test1Dog() {
    AnimalShelter animalShelter = build1Dog();
    assertNull(animalShelter.dequeueCat());
    assertEquals(Animal.DOG, animalShelter.dequeueDog());
    assertNull(animalShelter.dequeueCat());
    assertNull(animalShelter.dequeueDog());
    assertNull(animalShelter.dequeueAny());
  }

  @Test
  void test4Cat() {
    AnimalShelter animalShelter = build4Cats();
    assertNull(animalShelter.dequeueDog());
    assertEquals(Animal.CAT, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueDog());
    assertEquals(Animal.CAT, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueDog());
    assertEquals(Animal.CAT, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueDog());
    assertEquals(Animal.CAT, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueDog());
    assertNull(animalShelter.dequeueCat());
  }

  @Test
  void testMultipleAnimals() {
    //4 cat, 5 dog; sequence: cat, cat, dog, dog, cat, dog, cat, dog, dog
    AnimalShelter animalShelter = build4Cats5Dogs();
    assertEquals(Animal.CAT, animalShelter.dequeueCat());
    assertEquals(Animal.CAT, animalShelter.dequeueCat());
    assertEquals(Animal.CAT, animalShelter.dequeueCat());
    assertEquals(Animal.DOG, animalShelter.dequeueAny());
    assertEquals(Animal.DOG, animalShelter.dequeueAny());
    assertEquals(Animal.DOG, animalShelter.dequeueAny());
    assertEquals(Animal.CAT, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueCat());
    assertEquals(Animal.DOG, animalShelter.dequeueAny());
    assertNull(animalShelter.dequeueCat());
    assertEquals(Animal.DOG, animalShelter.dequeueDog());
    assertNull(animalShelter.dequeueCat());
    assertNull(animalShelter.dequeueDog());
    assertNull(animalShelter.dequeueAny());
  }
}