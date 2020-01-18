package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class AsteroidCollision {
  public int[] asteroidCollision(int[] array) {
    Stack<Asteroid> positiveStack = new Stack<>();
    Stack<Asteroid> negativeStack = new Stack<>();
    for (int i = 0; i < array.length; i++) {
      int arrayValue = array[i];
      Asteroid asteroid = new Asteroid(i, arrayValue);
      if (arrayValue > 0) {
        positiveStack.push(asteroid);
      } else {
        boolean destroy = true;
        while (!positiveStack.isEmpty() && destroy) {
          Asteroid pE = positiveStack.peek();
          if (pE.position < i) {
            if ((arrayValue + pE.value) == 0) {
              destroy = false;
              positiveStack.pop();
            } else if ((arrayValue + pE.value) > 0) {
              destroy = false;
            } else {
              positiveStack.pop();
            }
          }
        }
        if (destroy) {
          negativeStack.push(asteroid);
        }
      }
    }
    List<Integer> remainingAsteroids = new ArrayList<>();
    while (!positiveStack.isEmpty() && !negativeStack.isEmpty()) {
      Asteroid positiveAsteroid = positiveStack.peek();
      Asteroid negativeAsteroid = negativeStack.peek();
      if (negativeAsteroid.position > positiveAsteroid.position) {
        remainingAsteroids.add(negativeAsteroid.value);
        negativeStack.pop();
      } else {
        remainingAsteroids.add(positiveAsteroid.value);
        positiveStack.pop();
      }
    }
    while (!positiveStack.isEmpty()) {
      remainingAsteroids.add(positiveStack.pop().value);
    }
    while (!negativeStack.isEmpty()) {
      remainingAsteroids.add(negativeStack.pop().value);
    }
    Collections.reverse(remainingAsteroids);
    int[] ansArray = new int[remainingAsteroids.size()];
    for (int i = 0; i < remainingAsteroids.size(); i++) {
      ansArray[i] = remainingAsteroids.get(i);
    }
    return ansArray;
  }

  public class Asteroid {
    public int position;
    public int value;

    public Asteroid(int position, int value) {
      this.position = position;
      this.value = value;
    }
  }

  public static void main(String[] args) {
    AsteroidCollision asteroidCollision = new AsteroidCollision();
    System.out.println(asteroidCollision.asteroidCollision(new int[]{8, -8}));
  }
}
