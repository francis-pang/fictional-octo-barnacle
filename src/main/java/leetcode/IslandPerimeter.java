import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class IslandPerimeter {
  private static int LS;
  public int islandPerimeter(int[][] grid) {
    Set<Cor> visited = new HashSet<>();
    LS = 0;
    for (int r = 0; r < grid.length; r++) {
      for (int c = 0; c < grid[0].length; c++) {
        if (grid[r][c] == 1) {
          totalLS(grid, r, c, visited);
        }
      }
    }
    return LS;
  }

  private void totalLS(int[][] grid, int r, int c, Set<Cor> visited) {
    Cor cor = new Cor(r, c);
    if (!visited.add(cor)) {
      return;
    }
    if (r > 0 && grid[r - 1][c] == 1) { // left
      totalLS(grid, r - 1, c, visited);
    } else {
      LS++;
    }
    if (r < grid.length - 1 && grid[r + 1][c] == 1) { // right
      totalLS(grid, r + 1, c, visited);
    } else {
      LS++;
    }
    if (c > 0 && grid[r][c - 1] == 1) { // top
      totalLS(grid, r, c - 1, visited);
    } else {
      LS++;
    }
    if (c < grid[0].length - 1 && grid[r][c + 1] == 1) {
      totalLS(grid, r, c + 1, visited);
    } else {
      LS++;
    }
  }

  class Cor {
    public int r;
    public int c;

    public Cor(int r, int c) {
      this.r = r;
      this.c = c;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Cor cor = (Cor) o;
      return r == cor.r &&
          c == cor.c;
    }

    @Override
    public int hashCode() {
      return Objects.hash(r, c);
    }
  }
}
