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
    private static String reversStirng(String s) {
        StringBuilder str = new StringBuilder();
        str.append(s);
        str.reverse();
        return str.toString();
    }


    private static boolean check(String s, String t) {
        int j = 0;
        for (int i = 0; i < s.length(); i++) {
            if (t.charAt(j) == s.charAt(i)) {
                j++;
                if (j == t.length()) {
                    return true;
                }

            }
        }
        return false;
    }

    public static String reverseShuffleMerge(String s) {
        // Write your code here
        String result = null;
        StringBuilder strM = new StringBuilder();
        int[] ascii = new int[255];
        for (int i = 0; i < s.length(); i++) {
            ascii[s.charAt(i)]++;
        }
        for (int i = 0; i < 255; i++) {
            if (ascii[i] != 0) {
                for (int j = 0; j < ascii[i] / 2; j++) {
                    strM.append((char) i);
                }
            }
        }
        String main = strM.toString();
        for (int i = 0; i < main.length(); i++) {
            String temp = main.substring(i) + main.substring(0, i);
            if (result == null) {
                if (check(s, reversStirng(temp))) {
                    result = temp;
                }
                if (check(s, temp)) {
                    if (result == null) {
                        result = reversStirng(temp);
                    } else {
                        if (result.compareTo(reversStirng(temp)) > 1) {
                            result = reversStirng(temp);
                        }
                    }
                }
            } else {
                if (check(s, reversStirng(temp))) {
                    if (result.compareTo(temp) > 1) {
                        result = temp;
                    }
                }
                if (check(s, temp)) {
                    if (result.compareTo(reversStirng(temp)) > 1) {
                        result = reversStirng(temp);
                    }
                }
            }

        }
        return result;
    }

    public static int maxMin(int k, List<Integer> arr) {
        // Write your code here
        int unfairness = Integer.MAX_VALUE;
        Collections.sort(arr);
        for (int i = 0; i < arr.size(); i++) {
            if (i + k - 1 < arr.size()) {
                if (unfairness > arr.get(i + k - 1) - arr.get(i)) unfairness = arr.get(i + k - 1) - arr.get(i);
            }
        }
        return unfairness;
    }

    public static int getMinimumCost(int k, int[] c) {
        int result = 0;
        Arrays.sort(c);
        int[] m = new int[k];
        int indexF = k - 1;
        for (int i = c.length - 1; i >= 0; i--) {
            if (indexF == 0) {
                result += (m[indexF]++ + 1) * c[i];
                indexF = k - 1;
            } else if (indexF == k - 1) {
                result += (m[indexF]++ + 1) * c[i];
                indexF--;
            } else {
                result += (m[indexF]++ + 1) * c[i];
                indexF--;
            }
        }
        return result;
    }

    public static int luckBalance(int k, List<List<Integer>> contests) {
        // Write your code here
        int luck = 0;
        Collections.sort(contests, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if (o1.get(0) > o2.get(0)) return -1;
                if (o1.get(0) < o2.get(0)) return 1;
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

    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();

        String result = Result.reverseShuffleMerge(s);

        System.out.println(result);

        bufferedReader.close();
    }
}
