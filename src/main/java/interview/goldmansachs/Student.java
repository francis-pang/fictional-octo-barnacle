package interview.goldmansachs;

import java.util.Objects;

public class Student {
  public int rank;
  public String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Student)) return false;
    Student student = (Student) o;
    return rank == student.rank &&
        Objects.equals(name, student.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rank);
  }
}
