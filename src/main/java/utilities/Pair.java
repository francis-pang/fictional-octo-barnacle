package utilities;

import java.util.Objects;
import java.util.StringJoiner;

public class Pair<T> {
  public T first;
  public T second;

  public Pair() {
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Pair)) return false;
    Pair that = (Pair) o;
    return this.first.equals(that.first) && this.second.equals(that.second);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first, second);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", Pair.class.getSimpleName() + "[", "]")
        .add("first=" + first)
        .add("second=" + second)
        .toString();
  }
}