package elementsofprogramminginterviews.arrays;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EnumerateAllPrimesToN {
    public List<Integer> allPrimesFromOneToN(int number) {
        if (number < 2) {
            return Collections.emptyList();
        }
        List<Integer> primes = new ArrayList<>();
        primes.add(2);
        for (int i = 3; i <= number; i++) {
            boolean isPrime = true;
            for (int prime : primes) {
                if (i % prime == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                primes.add(i);
            }
        }
        return primes;
    }

    public static void main(String[] args) {
        EnumerateAllPrimesToN enumerateAllPrimesToN = new EnumerateAllPrimesToN();
        System.out.println(enumerateAllPrimesToN.allPrimesFromOneToN(29));
    }
}
