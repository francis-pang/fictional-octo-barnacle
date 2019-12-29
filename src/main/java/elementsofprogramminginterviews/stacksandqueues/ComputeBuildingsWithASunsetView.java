package elementsofprogramminginterviews.stacksandqueues;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringJoiner;

public class ComputeBuildingsWithASunsetView {
  public List<Building> viewSunset(List<Building> buildings) {
    Stack<Building> stack = new Stack<>();
    for (Building building : buildings) {
      boolean canSeeSunSet = false;
      while (!stack.isEmpty() && !canSeeSunSet) {
        Building poppedBuilding = stack.peek();
        if (poppedBuilding.height <= building.height) {
          stack.pop();
        } else {
          canSeeSunSet = true;
        }
      }
      stack.push(building);
    }
    return new ArrayList<>(stack);
  }

  public static class Building {
    public int height;

    public Building(int height) {
      this.height = height;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Building.class.getSimpleName() + "[", "]")
          .add("height=" + height)
          .toString();
    }
  }

  public void printList(List<Building> buildings) {
    buildings.forEach(building -> System.out.print(building.toString() + ", "));
    System.out.println();
  }

  public static void main(String[] args) {
    List<Building> buildings = new ArrayList<>();
    Building b9 = new Building(9);
    Building b2 = new Building(2);
    Building b4 = new Building(4);
    Building b7 = new Building(7);
    Building b72 = new Building(7);
    Building b5 = new Building(5);
    buildings.add(b9);
    buildings.add(b2);
    buildings.add(b4);
    buildings.add(b7);
    buildings.add(b72);
    buildings.add(b5);
    ComputeBuildingsWithASunsetView computeBuildingsWithASunsetView = new ComputeBuildingsWithASunsetView();
    List<Building> sunsetView = computeBuildingsWithASunsetView.viewSunset(buildings);
    computeBuildingsWithASunsetView.printList(sunsetView);
  }
}
