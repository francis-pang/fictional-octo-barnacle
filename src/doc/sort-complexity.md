Another useful reference to read: [Sorting Algorithm Cheat Sheet](https://www.interviewcake.com/sorting-algorithm-cheat-sheet)

| sort                                                | average time complexity | space complexity | in-place | stable | online |
|:----------------------------------------------------|:------------------------|:-----------------|:---------|:-------|:-------|
| insertion sort                                      | O(n<sup>2</sup>)        | O(1)             | Y        | Y      | N      |
| selection sort                                      | O(n<sup>2</sup>)        | O(1)             | Y        | N      | N      |
| bubble sort                                         | O(n<sup>2</sup>)        | O(1)             | Y        | Y      | N      |
| quick sort                                          | O(n log(n))             | O(log(n))        | Y        | N      | Y      |
| merge sort                                          | O(n log(n))             | O(n)             | N        | Y      | Y      |
| heap sort (based on selection sort)                 | O(n log(n))             | O(1)             | Y        | N      | Y      |
| radix sort (sorting key with Lexicographical order) | O(kc)                   | O(n+c)           | N        | Y      | Y      |
| counting sort (positive integer only)               | O(n+k)                  | O(n+k)           | N        | Y      | Y      |


**Note**:
1. Word case of quick sort: O(n<sup>2</sup>)
2. c in radix sort is the number of digits represent by the biggest possible sorting key, because that is the number of round of sorting on each digit.
3. k in counting sort is the maximum value of the integer based sorting key

### Use Case
Taken from [When is each sorting algorithm used](https://stackoverflow.com/a/1934004/1522867),
**Merge sort**: Only option for fast stable sorting. However, it use O(n) auxiliary space and has a slightly larger constant than quick sort.

**Quick Sort**: Use this if you don't need stable sort, and average case performance matters more than worst case performance. Use O(n<sup>2</sup>) in the form of call stack space for recursion.

**Heap Sort**: Use this if you don't need stable sort, and worst case performance matters more than average case performance. Use O(1) auxiliary space, so you won't run out of space on large input.

**Introsort**: A quick sort that switches t heap sort after certain recursion depth to get around quick sort's O(n<sup>2</sup>) worse case.

**Insertion sort**: Use for small input.

**Counting sort**: For integers with limited range

**Radix sort**: For log(n) is significantly larger than c, the number of digits