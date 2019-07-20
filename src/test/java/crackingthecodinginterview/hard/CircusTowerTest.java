package crackingthecodinginterview.hard;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CircusTowerTest {
  private static CircusTower circusTower;
  private static Instant start;

  @BeforeAll
  static void init() {
    circusTower = new CircusTower();
    start = Instant.now();
  }

  @AfterAll
  static void tearDown() {
    Instant end = Instant.now();
    System.out.println("Total time:" + Duration.between(start, end).getNano());
  }

  @Test
  void computeHighestTowerHeight_6() {
    List<CircusTower.Person> personList = new ArrayList<>();
    personList.add(new CircusTower.Person(65, 100));
    personList.add(new CircusTower.Person(70, 150));
    personList.add(new CircusTower.Person(56, 90));
    personList.add(new CircusTower.Person(75, 190));
    personList.add(new CircusTower.Person(60, 95));
    personList.add(new CircusTower.Person(68, 110));

    assertEquals(6, circusTower.computeHighestTowerHeight(personList));
  }

  @Test
  void computeHighestTowerHeight_1OutOfNorm() {
    List<CircusTower.Person> personList = new ArrayList<>();
    personList.add(new CircusTower.Person(65, 100));
    personList.add(new CircusTower.Person(70, 190));
    personList.add(new CircusTower.Person(56, 90));
    personList.add(new CircusTower.Person(75, 190));
    personList.add(new CircusTower.Person(60, 95));
    personList.add(new CircusTower.Person(68, 110));

    assertEquals(5, circusTower.computeHighestTowerHeight(personList));
  }

  @Test
  void computeHighestTowerHeight_cannotStack() {
    List<CircusTower.Person> personList = new ArrayList<>();
    personList.add(new CircusTower.Person(40, 60));
    personList.add(new CircusTower.Person(50, 50));
    personList.add(new CircusTower.Person(40, 60));

    assertEquals(1, circusTower.computeHighestTowerHeight(personList));
  }

  @Test
  void computeHighestTowerHeight_2EqualStack() {
    List<CircusTower.Person> personList = new ArrayList<>();
    personList.add(new CircusTower.Person(50, 30));
    personList.add(new CircusTower.Person(40, 20));
    personList.add(new CircusTower.Person(50, 120));
    personList.add(new CircusTower.Person(30, 10));
    personList.add(new CircusTower.Person(40, 110));
    personList.add(new CircusTower.Person(30, 100));

    assertEquals(3, circusTower.computeHighestTowerHeight(personList));
  }

  @Test
  void computeHighestTowerHeight_FirstIsNotInsideStack() {
    List<CircusTower.Person> personList = new ArrayList<>();
    personList.add(new CircusTower.Person(7, 7));
    personList.add(new CircusTower.Person(6, 6));
    personList.add(new CircusTower.Person(4, 4));
    personList.add(new CircusTower.Person(3, 3));
    personList.add(new CircusTower.Person(1, 5));

    assertEquals(4, circusTower.computeHighestTowerHeight(personList));
  }
}