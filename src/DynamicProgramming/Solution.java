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

    public static int maxSubsetSum(List<Integer> arr) {
        if(arr.size() == 0) return 0;
        if(arr.size() == 1) return Math.max(0,arr.get(0));
        arr.set(0, Math.max(arr.get(0), 0));
        arr.set(1, Math.max(arr.get(1), arr.get(0)));
        for (int i = 2; i < arr.size(); i++) {
            arr.set(i,Math.max(arr.get(i-2) + arr.get(i), arr.get(i-1)));
        }
        return arr.get(arr.size() - 1);
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
