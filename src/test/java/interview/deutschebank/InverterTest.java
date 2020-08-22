package interview.deutschebank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InverterTest {

  // Parameters string

  // empty string
  // null string
  // single character
  // long length
  // string with empty space
  // string with upper and lower case
  // string with multiple special characters in the middle
  // string with special characters at the start/end

  @Test
  public void nullString() {
    String inverted = Inverter.invert(null);
    Assertions.assertNull(inverted);
  }

  @Test
  public void emptyString() {
    Assertions.assertEquals("", Inverter.invert(""));
  }

  @Test
  public void singleCharacter() {
    Assertions.assertEquals("a", Inverter.invert("a"));
  }

  @Test
  public void multipleCharacter() {
    Assertions.assertEquals("redcba", Inverter.invert("abcder"));
  }

  @Test
  public void upperAndLowerCase() {
    Assertions.assertEquals("rEdeba", Inverter.invert("abedEr"));
  }

  @Test
  public void specialCharacterInMiddleOfString() {
    Assertions.assertEquals("dc;ba", Inverter.invert("ab;cd"));
  }

  @Test
  public void specialCharacterAtStartAndEndofString() {
    Assertions.assertEquals("\"dcba\\", Inverter.invert("\\abcd\""));
  }
}
