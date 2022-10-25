package DynamicProgramming;

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
     * Complete the 'maxSubsetSum' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */
    private static int max = 0;

    public static int maxSubsetSum(List<Integer> arr) {
        for (int i = 0; i < arr.size(); i++) {
            recurs(i + 2, arr.get(i), arr);
        }
        return max < 0 ? 0 : max;
    }

    private static void recurs(int i, int temp, List<Integer> arr) {
        if (i >= arr.size()) {
            if (temp > max) {
                max = temp;
            }
            return;
        }
        temp += arr.get(i);
        if (temp > max) {
            max = temp;
        }
        recurs(i + 2, temp, arr);
        for (int j = i + 1; j < arr.size(); j++) {
            recurs(j, temp - arr.get(i), arr);
        }
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        int res = Result.maxSubsetSum(arr);

        System.out.println(res);

        bufferedReader.close();
    }
}
