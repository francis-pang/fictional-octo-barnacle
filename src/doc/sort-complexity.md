Another useful reference to read: [Sorting Algorithm Cheat Sheet](https://www.interviewcake.com/sorting-algorithm-cheat-sheet)

| sort                                  | average time complexity | space complexity | in-place | stable |
|:--------------------------------------|:------------------------|:-----------------|:---------|:-------|
| insertion sort                        | O(n<sup>2</sup>)        | O(1)             | Y        | Y      |
| selection sort                        | O(n<sup>2</sup>)        | O(1)             | Y        | N      |
| bubble sort                           | O(n<sup>2</sup>)        | O(1)             | Y        | Y      |
| quick sort                            | O(n log(n))             | O(log(n))        | Y        | N      |
| merge sort                            | O(n log(n))             | O(n)             | N        | Y      |
| heap sort                             | O(n log(n))             | O(1)             | Y        | N      |
| radix sort (integer only)             | O(kn)                   | O(n+k)           | N        | Y      |
| counting sort (positive integer only) | O(n+k)                  | O(n+k)           | N        | Y      |


**Note**:
1. Word case of quick sort: O(n<sup>2</sup>)
2. k in radix sort is the number of passes