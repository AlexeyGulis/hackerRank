package SortTasks;

import edu.princeton.cs.algs4.In;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.*;

class Result {

    /*
     * Complete the 'countSwaps' function below.
     *
     * The function accepts INTEGER_ARRAY a as parameter.
     */

    public static void countSwaps(List<Integer> a) {
        int count = 0;
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < a.size() - 1; j++) {
                // Swap adjacent elements if they are in decreasing order
                if (a.get(j) > a.get(j + 1)) {
                    int temp = a.get(j);
                    a.set(j, a.get(j + 1));
                    a.set(j + 1, temp);
                    count++;
                }
            }
        }
        System.out.println("Array is sorted in " + count + " swaps.");
        System.out.println("First Element: " + a.get(0));
        System.out.println("Last Element: " + a.get(a.size() - 1));
    }

    /*
     * Complete the 'maximumToys' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY prices
     *  2. INTEGER k
     */

    public static int maximumToys(List<Integer> prices, int k) {
        prices.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) {
                    return 1;
                }
                if (o1 < o2) return -1;
                return 0;
            }
        });
        int temp = k;
        int result = 0;
        for (Integer i : prices
        ) {
            if (i <= temp) {
                result++;
                temp -= i;
            }
        }
        return result;
    }

    public static int activityNotifications(List<Integer> expenditure, int d) {
        int result = 0;
        int median = 0;
        int[] copyCountSort = new int[201];
        int[] countSort = new int[201];
        for (int i = 0; i < d; i++) {
            countSort[expenditure.get(i)]++;
        }
        for (int i = 1; i < 201; i++) {
            countSort[i] += countSort[i - 1];
        }

        if (d % 2 == 0) {
            median = 0;
            for (int i = 0; i < 201; i++) {
                if ((d / 2) + 1 <= countSort[i]) {
                    median += i;
                    break;
                }
            }
            for (int i = 0; i < 201; i++) {
                if ((d / 2) <= countSort[i]) {
                    median += i;
                    break;
                }
            }
        } else {
            for (int i = 0; i < 201; i++) {
                if ((d / 2) + 1 <= countSort[i]) {
                    median = 2 * i;
                    break;
                }
            }
        }
        if (median <= expenditure.get(d)) {
            result++;
        }
        for (int b = 0; b < expenditure.size() - d; b++) {
            for (int i = expenditure.get(b); i < 201; i++) {
                countSort[i]--;
            }
            for (int i = expenditure.get(d + b); i < 201; i++) {
                countSort[i]++;
            }
            if (d % 2 == 0) {
                median = 0;
                for (int i = 0; i < 201; i++) {
                    if ((d / 2) + 1 <= countSort[i]) {
                        median += i;
                        break;
                    }
                }
                for (int i = 0; i < 201; i++) {
                    if ((d / 2) <= countSort[i]) {
                        median += i;
                        break;
                    }
                }
            } else {
                for (int i = 0; i < 201; i++) {
                    if ((d / 2) + 1 <= countSort[i]) {
                        median = 2 * i;
                        break;
                    }
                }
            }
            if (d + b + 1 < expenditure.size() && median <= expenditure.get(d + b + 1)) {
                result++;
            }
        }
        return result;
    }

    public static long countInversions(List<Integer> arr) {
        // Write your code here
        int result = 0;
        for (int i = 0; i < arr.size(); i++) {
            int j = i - 1;
            while (j >= 0 && arr.get(j) > arr.get(j + 1)) {
                result++;
                int temp = arr.get(j + 1);
                arr.set(j + 1, arr.get(j));
                arr.set(j, temp);
                j--;
            }
        }
        return result;
    }

}

public class Solution {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                long result = Result.countInversions(arr);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
