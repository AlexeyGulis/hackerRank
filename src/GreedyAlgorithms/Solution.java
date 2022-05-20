package GreedyAlgorithms;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'minimumAbsoluteDifference' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static int getMinimumCost(int k, int[] c) {

        return 0;
    }

    public static int luckBalance(int k, List<List<Integer>> contests) {
        // Write your code here
        int luck = 0;
        Collections.sort(contests, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if(o1.get(0) > o2.get(0)) return -1;
                if(o1.get(0) < o2.get(0)) return 1;
                return 0;
            }
        });
        int count = 0;
        for (int i = 0; i < contests.size(); i++) {
            if (contests.get(i).get(1) == 1) {
                if (count++ < k) luck += contests.get(i).get(0);
                else luck -= contests.get(i).get(0);
            } else {
                luck += contests.get(i).get(0);
            }

        }
        return luck;
    }


    public static int minimumAbsoluteDifference(List<Integer> arr) {

        Integer[] arrs = new Integer[arr.size()];
        arr.toArray(arrs);
        Arrays.sort(arrs);
        int result = Math.abs(arrs[0] - arrs[1]);
        // Write your code here
        for (int i = 1; i < arrs.length; i++) {
            if (Math.abs(arrs[i - 1] - arrs[i]) < result) result = Math.abs(arrs[i - 1] - arrs[i]);
        }
        return result;
    }

}

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] c = new int[n];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int cItem = Integer.parseInt(cItems[i]);
            c[i] = cItem;
        }

        int minimumCost = Result.getMinimumCost(k, c);

        System.out.println(minimumCost);

        bufferedReader.close();
        scanner.close();
    }
}
