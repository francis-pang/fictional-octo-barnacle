package interview.shopify;

import java.util.HashMap;
import java.util.Map;

// It's time to count inventory in our store so we can account for the unsold products of the past month. Products in our store are made up of parts. Each product part has a unique identifier (part ID). The part ID is always a single alpha-numeric character. Some products are only made up of one part, while others require multiple parts to be considered complete.

// Parts are never shared between products. They can only belong to at most 1 product.

// Shelf:
// Each unit is considered complete
// Full unit: part ID = "a"

// Stool:
// For a stool to be complete, it requires 1 top and 3 legs
// Top: part ID = "b"
// Legs: part ID = "c"

// Table:
// For a table to be complete, it requires 1 top and 4 legs
// Top: part ID = "d"
// Legs: part ID = "e"

// Behaviour
// Your mission: create a function that maps the quantity of each complete product.

// The input should be a string of part IDs (no commas or any other separator characters. Eg: "abcde" The output should show a list of quantities for each complete product. It's OK to have left over parts if they cannot form a complete product. Unrecognized parts (not belonging to a product) are also OK to ignore.

// Your solution should work for any length and combination of part IDs.

// Input/Output Expectations
// Here is a list of input/output examples:

// "abccc" => {"Shelf" : 1, "Stool": 1, "Table": 0}
// "beceadee" => {"Shelf" : 1, "Stool": 0, "Table": 1}
// "eebeedebaceeceedeceacee" => {"Shelf" : 2, "Stool": 1, "Table": 2}
// "zabc" => {"Shelf" : 1, "Stool" : 0, "Table" : 0}
// "deeedeee" => {"Shelf" : 0, "Stool" : 0, "Table" : 1}

/**
 * -> Furniture can share
 * Shelf = 1 * a + 1 * b
 * Stool: 1 * b + 3 * c
 * Table: 1 * d + 4 * e
 * <p>
 * Question:
 * Stool comes first
 * Input: abccc
 * Output: Stool
 * <p>
 * Order of precedure
 * Time: O(n) + O(n)
 * <p>
 * ProductPart (id, qty)
 * <p>
 * Furniture (productPart[])
 * -> Shelf
 * -> Stool
 * -> Table
 */

/**
 * Scope: Shelf, Stool, Table
 * Input -> Unsorted
 *       -> Unidentified =invalid
 * Ouput: No. for each is fine
 *
 * Shelf = 1 * a
 * Stool: 1 * b + 3 * c
 * Table: 1 * d + 4 * e
 *
 * Brute Force:
 * 1. Extract 1 shelf from the string
 * 2. Repeat until no more shelf
 * 3. Repeat step 1 & 2 for stool and table
 *
 * Keep a counter
 * Time: O(n^2)
 * Space: O(1)
 *
 * Optim:
 * 1. Keep a HashMap ('part ID', count)
 * 2. Read through the string,
 * 3. At every char, increment the hashmap of count of the part. (Ignore not abcde)
 * 4. Check if it fulfilled any existing shelf, stool, table
 * 5a. If it does, deduct the count, and then add 1 to count of product
 * 5b. Else, continue
 * 6. Repeat until end of string
 *
 * Time: O(n)
 * Space: O(k), k = 5 (abcde) = O(1)
 */

public class ProductAssembly {
  public static void main(String[] args) {
    String input = "deeedeee";
    // Assume all valid input, blank okay

    Map<Character, Integer> pCounts = new HashMap<>();
    int shelfCount = 0;
    int stoolCount = 0;
    int tableCount = 0;

    for (int i = 0; i < input.length(); i++) {
      char c = input.charAt(i);
      if (c != 'a' && c != 'b' && c != 'c' && c != 'd' && c != 'e') {
        continue;
      }
      pCounts.compute(c, (k, v) -> (v == null) ? 1 : v + 1);

      // Shelf
      int oldCount = 0;
      if (pCounts.getOrDefault('a', 0) >= 1) {
        shelfCount++;
        oldCount = pCounts.get('a');
        pCounts.put('a', oldCount - 1);
      }
      // Stool
      if (pCounts.getOrDefault('b', 0) >= 1 && pCounts.getOrDefault('c', 0) >= 3) {
        stoolCount++;
        oldCount = pCounts.get('b');
        pCounts.put('b', oldCount - 1);
        oldCount = pCounts.get('c');
        pCounts.put('c', oldCount - 3);
      }

      // Table
      if (pCounts.getOrDefault('d', 0) >= 1 && pCounts.getOrDefault('e', 0) >= 4) {
        tableCount++;
        oldCount = pCounts.get('d');
        pCounts.put('d', oldCount - 1);
        oldCount = pCounts.get('e');
        pCounts.put('e', oldCount - 4);
      }
    }
    System.out.println("shelf:" + shelfCount + ". stool:" + stoolCount + ". table:" + tableCount);
  }
}
