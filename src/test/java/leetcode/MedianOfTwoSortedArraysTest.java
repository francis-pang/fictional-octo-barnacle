package leetcode;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedianOfTwoSortedArraysTest {
  private final MedianOfTwoSortedArrays medianOfTwoSortedArrays = new MedianOfTwoSortedArrays();
  private static final String TEST_CASE_PARAMETERIZED_NAME = "{index} - Median: {2} for [{0}] + [{1}]";

  /**
   * <b>Test case design</b>
   * Input size variation
   * 1. 0 element
   * 2. 1 element
   * 3. 2 elements
   * 4. >2 elements
   * <p>
   * The test case is designed with different combinations of the size variation
   * <p>
   * 1. 0 element
   * 1.1. 0 element with 0 element
   * - No need for A/B side
   * 1.2. 0 element and other types of sizes
   * - Valid for 1 element, 2 elements, >2 elements
   * <p>
   * 2. 1 element
   * 2.1. All same values
   * - Valid for 1 element, 2 elements, >2 elements
   * - No need for A/B side for 1 element   *
   * 2.2. 1 element array with 1 element array
   * 2.3. 1 element array is LHS of the other array
   * - Valid for 2 elements, >2 elements
   * 2.4. Element in 1 element array is the median
   * - Valid for 2 elements, >2 elements
   * 2.5. All elements of other array is on LHS of >2 element array
   * - Valid for >2 elements
   * 2.6. All elements of other array is on RHS of >2 element array
   * - Valid for >2 elements
   * <p>
   * 3. 2 elements
   * The general strategy is to repeat all the test cases in (2), but with additional of 3.1, 3.
   * <p>
   * 3.1. All elements inside other array is surrounded by elements of elements inside 2-element array
   * - Valid for 2 elements, >2 elements
   * 3.2. All elements inside other array is on LHS of elements inside 2-element array
   * - Valid for 2 elements, >2 elements
   * 3.3. All elements inside other array is on RHS of elements inside 2-element array
   * - Valid for 2 elements, >2 elements
   * 3.4. All elements of other array is on LHS of >2 element array
   * - Valid for >2 elements
   * 3.5. All elements of other array is on RHS of >2 element array
   * - Valid for >2 elements
   * 3.6. 1 element of other array is in LHS, 1 element is in RHS
   * - Valid for >2 elements
   * 3.7. Both element is the median
   * - Valid for >2 elements
   * 3.8. All values sames
   * 3.9 Different values within element, but identical arrays
   * <p>
   * 4. More than 2 elements
   * 4.1. All elements inside other array is surrounded by elements of elements inside 2-element array
   * - Valid for 2 elements, >2 elements
   * 4.2. All elements inside other array is on LHS of elements inside 2-element array
   * - Valid for 2 elements, >2 elements
   * 4.3. All elements inside other array is on RHS of elements inside 2-element array
   * - Valid for 2 elements, >2 elements
   * 4.4. All elements of other array is on LHS of >2 element array
   * - Valid for 2 elements, >2 elements
   * 4.5. All elements of other array is on RHS of >2 element array
   * - Valid for 2 elements, >2 elements
   * 4.6. 1 element of other array is in LHS, 1 element is in RHS
   * - Valid for 2 elements, >2 elements
   */

  private int[] convertStringToIntArray(String arrayString) {
    if (arrayString.length() == 0) {
      return new int[]{};
    }
    String[] arrayStrings = arrayString.split(",");
    int[] array = new int[arrayStrings.length];
    for (int i = 0; i < arrayStrings.length; i++) {
      String numberString = arrayStrings[i];
      int number = Integer.parseInt(numberString);
      array[i] = number;
    }
    return array;
  }

  @Test
  void findMedianSortedArrays_0ElementWith0Element() {
    int[] array1 = new int[]{};
    int[] array2 = new int[]{};
    assertEquals(Double.NaN, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'', '2', '2'",
      "'2', '', '2'",
  })
  void findMedianSortedArrays_0ElementWith1Element(String array1String, String array2String,
                                                   double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'', '2,3', '2.5'",
      "'2,3', '', '2.5'",
  })
  void findMedianSortedArrays_0ElementWith2Element(String array1String, String array2String,
                                                   double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'', '2,3,3', '3'",
      "'2,3,3', '', '3'",
  })
  void findMedianSortedArrays_0ElementWithMoreThan2Element(String array1String, String array2String,
                                                           double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'10', '4', '7'",
      "'4', '10', '7'",
  })
  void findMedianSortedArrays_1ElementWith1ElementDifferentValue(String array1String, String array2String,
                                                                 double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'4', '4', '4'",
      "'4', '4,4', '4'",
      "'4,4', '4', '4'",
      "'4', '4,4,4', '4'",
      "'4,4,4', '4', '4'",
      "'4', '4,4,4,4', '4'",
      "'4,4,4,4', '4', '4'"
  })
  void findMedianSortedArrays_1ElementWithOtherArraySameValue(String array1String, String array2String,
                                                              double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'3', '4,5', '4'",
      "'4,5', '3', '4'"
  })
  void findMedianSortedArrays_1ElementLeftHandSide2Element(String array1String, String array2String,
                                                           double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'3', '1,2', '2'",
      "'1,2', '3', '2'"
  })
  void findMedianSortedArrays_1ElementRightHandSide2Element(String array1String, String array2String,
                                                            double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * The element in 1-element array is entirely on LHS of the >2-element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'0', '1,2,3,4', '2'",
      "'1,2,3,4', '0', '2'"
  })
  void findMedianSortedArrays_1ElementLeftHandSideMoreThan2Element(String array1String, String array2String,
                                                                   double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * The element in 1-element array is entirely on RHS of the >2-element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'8', '1,2,3,4', '3'",
      "'1,2,3,4', '8', '3'"
  })
  void findMedianSortedArrays_1ElementRightHandSideMoreThan2Element(String array1String, String array2String,
                                                                    double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * The element in 1-element array is in between the 2-element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'2', '1,3', '2'",
      "'1,3', '2', '2'"
  })
  void findMedianSortedArrays_1ElementInBetween2ElementArray(String array1String, String array2String,
                                                             double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * The element in 1-element array is the median with the odd-number >2 element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'4', '1,2,5,6', '4'",
      "'1,2,5,6', '4', '4'"
  })
  void findMedianSortedArrays_1ElementMedianMoreThanOdd2ElementArray(String array1String, String array2String,
                                                                     double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * The element in 1-element array is the median with the even-number >2 element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'4', '1,2,5,5,6', '4.5'",
      "'1,2,5,5,6', '4', '4.5'"
  })
  void findMedianSortedArrays_1ElementMedianMoreThanEven2ElementArray(String array1String, String array2String,
                                                                      double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * The element in the 1-element array is in the LHS section (but not the boundary value) when merged with the
   * element in the >2 element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'2', '1,3,4,5,6', '3.5'",
      "'1,3,4,5,6', '2', '3.5'"
  })
  void findMedianSortedArrays_1ElementInLeftHandSideWithinMoreThan2ElementArray(String array1String,
                                                                                String array2String,
                                                                                double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * The element in the 1-element array is in the RHS section (but not the boundary value) when merged with the
   * element in the >2 element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'5', '1,2,3,4,6', '3.5'",
      "'1,2,3,4,6', '5', '3.5'",
  })
  void findMedianSortedArrays_1ElementInRightHandSideWithinMoreThan2ElementArray(String array1String,
                                                                                 String array2String,
                                                                                 double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * All element inside the other array is surrounded by the elements of the 2-element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'1,4', '2,3', '2.5'", // 2 elements, A side
      "'4,5', '2,8', '4.5'", // 2 elements, B side
      "'1,6', '1,2,3,3,4,5', '3'", // >2 elements, A side
      "'1,2,3,3,4,5', '1,6', '3'", // >2 elements, B side
  })
  void findMedianSortedArrays_2ElementCoverOtherArray(String array1String,
                                                      String array2String,
                                                      double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * All elements of 2-element array is on the LHS of the other element
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'1,2', '2,4', '2'", // 2 elements, A side
      "'2,4', '1,1', '1.5'", // 2 elements, B side
      "'1,2', '2,4,5,6', '3'", // >2 elements, even, A side
      "'2,5,6', '1,2', '2'" // >2 elements, odd, B side
  })
  void findMedianSortedArrays_2ElementsOnLeftHandSideOfOtherArray(String array1String,
                                                                  String array2String,
                                                                  double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * All elements of 2-element array is on the RHS of the other element
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      // There is no need for 2 elements because the LHS of A side is the RHS of B side
      "'5,6', '1,2,2,4', '3'",// >2 elements, even, A side
      "'1,2,2,5,6', '7,8', '5'" // >2 elements, odd, B side
  })
  void findMedianSortedArrays_2ElementsOnRightHandSideOfMoreThan2ElementsArray(String array1String,
                                                                               String array2String,
                                                                               double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * All elements of 2-element array is on LHS (but not boundary) of >2 element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'2,2', '1,3,3,3,4', '3'", // odd, A side
      "'2,3,3,3,4,4', '1,2', '3'" // , B side, one element is at boundary LHS only
  })
  void findMedianSortedArrays_2ElementsOnNonBoundaryLeftHandOfMoreThan2ElementsArray(String array1String,
                                                                                     String array2String,
                                                                                     double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * All elements of 2-element array is on RHS (but not boundary) of >2 element array
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'3,4', '1,2,2,4,6', '3'", // odd, A side
      "'1,2,2,3,3,4,', '3,6', '3'" // , B side, one element is at boundary LHS only
  })
  void findMedianSortedArrays_2ElementsOnNonBoundaryRightHandOfMoreThan2ElementsArray(String array1String,
                                                                                      String array2String,
                                                                                      double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * Both element is the median for even, one element is the median element for odd
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'2,4', '1,2,5,6,7', '4'",// Odd, A side
      "'2,4', '1,1,2,5,6,7', '3'",// Even, A side
      "'1,2,2,6,7', '4,5', '4'",// Odd, B side
      "'1,1,2,5,6,7', '2,4', '3'"// Even, B side
  })
  void findMedianSortedArrays_MedianIsIn2ElementArray(String array1String,
                                                      String array2String,
                                                      double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * Both element is the median for even, one element is the median element for odd
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      //1,1,2,3,5,6,76,87
      "'2,76', '1,1,3,4,5,6,87','4'", // Odd, A side
      "'2,76', '1,3,4,5,6,87', '4.5'", // Even, A side
      "'1,1,3,4,5,6,87','2,76', '4'", // Odd, B side
      "'1,3,4,5,6,87','2,76', '4.5'" // Even, B side

  })
  void findMedianSortedArrays_OneElementInLeftHandSideOneElementInRightHandSide(String array1String,
                                                                                String array2String,
                                                                                double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * Both element is the median for even, one element is the median element for odd
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'2,2', '2,2,2,2,2', '2'",// Odd, A side
      "'2,2', '2,2,2,2,2,2', '2'",// Even, A side
      "'9,9,9,9,9', '9,9', '9'",// Odd, B side
      "'0,0,0,0,0,0', '0,0', '0'"// Even, B side
  })
  void findMedianSortedArrays_AllValuesAreSame(String array1String,
                                               String array2String,
                                               double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }

  /**
   * Both element is the median for even, one element is the median element for odd
   */
  @ParameterizedTest(name = TEST_CASE_PARAMETERIZED_NAME)
  @CsvSource({
      "'5', '1,2,3,4,6,7,8', '4.5'"
  })
  void findMedianSortedArrays_array1CutOffBecomeNegativeAtSomePoint(String array1String,
                                                                    String array2String,
                                                                    double expectedResult) {
    int[] array1 = convertStringToIntArray(array1String);
    int[] array2 = convertStringToIntArray(array2String);
    assertEquals(expectedResult, medianOfTwoSortedArrays.findMedianSortedArrays(array1, array2));
  }
}