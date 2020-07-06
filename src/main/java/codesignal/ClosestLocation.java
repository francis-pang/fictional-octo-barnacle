package codesignal;

public class ClosestLocation {
  String closestLocation(String address, int[][] objects, String[] names) {
    String shortestString = "";
    double shortestDistance = Double.MAX_VALUE;
    for (int s = 0; s < names.length; s++) {
      String name = names[s];
      boolean similar = false;
      // prefix
      if (name.startsWith(address)) {
        similar = true;
      }

      // Differ by 1
      if (!similar && address.length() == name.length()) {
        int diffCount = 0;
        for (int i = 0; i < name.length(); i++) {
          if (address.charAt(i) != name.charAt(i)) {
            diffCount++;
          }
        }
        if (diffCount == 1) {
          similar = true;
        }
      }

      // 1 extra
      if (!similar && address.length() - name.length() == 1) {
        for (int i = 0; i < address.length(); i++) {
          String amendedName = name.substring(0, i) + address.charAt(i) + name.substring(i + 1);
          if (amendedName.equals(name)) {
            similar = true;
            break;
          }
        }
      }

      // 1 missing
      if (!similar && name.length() - address.length() == 1) {
        for (int i = 0; i < name.length(); i++) {
          String amendedName = address.substring(0, i) + name.charAt(i) + address.substring(i + 1);
          if (amendedName.equals(address)) {
            similar = true;
            break;
          }
        }
      }
      if (similar) {
        int[] object = objects[s];
        double distance1 = Math.sqrt(Math.pow(object[0], 2) + Math.pow(object[1], 2));
        if (distance1 < shortestDistance) {
          shortestDistance = distance1;
          shortestString = name;
        } else if (object.length == 4) {
          double distance2 = Math.sqrt(Math.pow(object[2], 2) + Math.pow(object[3], 2));
          if (distance2 < shortestDistance) {
            shortestDistance = distance2;
            shortestString = name;
          }
        }
      }
    }
    return shortestString;
  }

  public static void main(String[] args) {
    ClosestLocation closestLocation = new ClosestLocation();
    closestLocation.closestLocation(
        "Cat",
        new int[][]{{-3, 0}, {1, 3}, {2, 1, 2, 4}, {-4, -3, 6, -3}},
        new String[]{"Bat building", "Cats exhibition", "At street", "Cat avenue"}
    );
  }
}
