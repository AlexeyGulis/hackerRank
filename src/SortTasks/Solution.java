package SortTasks;

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
                if(o1 > o2){
                    return 1;
                }
                if(o1 < o2) return -1;
                return 0;
            }
        });
        int temp = k;
        int result = 0;
        for (Integer i : prices
             ) {
            if(i <= temp){
                result++;
                temp -= i;
            }
        }
        return result;
    }

}

public class Solution {
    private static class Player{
        private String name;
        private int score;

        public Player(String name, int score){
            this.name = name;
            this.score = score;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer();
            sb.append(name);
            sb.append(" ").append(score);
            return sb.toString();
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        int n = Integer.parseInt(s);
        Player [] players = new Player[n];
        for (int i = 0; i < n; i++) {
            String[] t = bufferedReader.readLine().split(" ");
            players[i] = new Player(t[0],Integer.parseInt(t[1]));
        }
        Arrays.sort(players, new Comparator<Player>() {

            public int compare(Player o1, Player o2) {
                if(o1.getScore() > o2.getScore()) return -1;
                if(o1.getScore() < o2.getScore()) return 1;
                if(o1.getScore() == o2.getScore()){
                    return o1.getName().compareTo(o2.getName());
                }
                return 0;
            }
        });
        for (int i = 0; i < n; i++) {
            System.out.println(players[i]);
        }
        bufferedReader.close();
    }
}
