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


    public static String reverseShuffleMerge(String s) {
        // Write your code here
        StringBuilder str = new StringBuilder();
        Map<Character, Integer> totalCount = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            totalCount.put(ch, totalCount.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> used = new HashMap<>();
        Map<Character, Integer> unUsed = new HashMap<>(totalCount);

        for (int i = s.length() - 1; i >= 0; i--) {
            char letter = s.charAt(i);
            unUsed.put(letter, unUsed.getOrDefault(letter, 0) - 1);
            if (used.getOrDefault(letter, 0) == totalCount.get(letter) / 2) {
                continue;
            }
            while (str.length() > 0) {
                char prevLetter = str.charAt(str.length() - 1);
                if (letter < prevLetter &&
                        used.getOrDefault(prevLetter, 0) + unUsed.getOrDefault(prevLetter, 0) - 1 >= totalCount.get(prevLetter) / 2) {
                    str.deleteCharAt(str.length() - 1);
                    used.put(prevLetter, used.get(prevLetter) - 1);
                } else {
                    break;
                }
            }
            str.append(letter);
            used.put(letter, used.getOrDefault(letter, 0) + 1);
        }

        return str.toString();
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
