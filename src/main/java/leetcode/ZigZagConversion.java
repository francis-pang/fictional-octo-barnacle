package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ZigZagConversion {
    public String convert(String string, int numRow) {
        List<StringBuilder> grids = new ArrayList<>();
        constructInternalList(grids, numRow);
        Direction direction = Direction.DOWN;
        int rowIndex = 0;
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            StringBuilder rowStringBuilder = grids.get(rowIndex);
            rowStringBuilder.append(c);
            if (numRow == 1) {
                continue;
            }
            if (direction.equals(Direction.DOWN)) {
                if (rowIndex == (numRow - 1)) {
                    rowIndex--;
                    direction = Direction.UP;
                } else {
                    rowIndex++;
                }
            } else { // UP
                if (rowIndex == 0) {
                    rowIndex++;
                    direction = Direction.DOWN;
                } else {
                    rowIndex--;
                }
            }
        }
        return constructString(grids);
    }

    private String constructString(List<StringBuilder> grids) {
        StringBuilder sb = new StringBuilder();
        grids.forEach(stringBuilder -> sb.append(stringBuilder.toString()));
        return sb.toString();
    }

    private void constructInternalList(List<StringBuilder> grids, int row) {
        for (int i = 0; i < row; i++) {
            grids.add(new StringBuilder());
        }
    }

    public enum Direction {
        UP, DOWN;
    }
}
