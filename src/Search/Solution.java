package Search;

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

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {
    public static void whatFlavors(List<Integer> cost, int money) {
        // Write your code here
        HashMap<Integer, ArrayList<Integer>> table = new HashMap<>();
        for (int i = 0; i < cost.size(); i++) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            if (table.containsKey(cost.get(i))) {
                arrayList = table.get(cost.get(i));
                arrayList.add(i + 1);
                table.put(cost.get(i), arrayList);
            } else {
                arrayList.add(i + 1);
                table.put(cost.get(i), arrayList);
            }

        }
        int f = 0;
        int s = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : table.entrySet()
        ) {
            if (entry.getKey() + entry.getKey() == money && entry.getValue().size() > 1) {
                f = entry.getValue().get(0) > entry.getValue().get(1) ? entry.getValue().get(1) : entry.getValue().get(0);
                s = entry.getValue().get(0) > entry.getValue().get(1) ? entry.getValue().get(0) : entry.getValue().get(1);
                System.out.println(f + " " + s);
                break;
            }
            if (table.containsKey(money - entry.getKey())) {
                f = entry.getValue().get(0) > table.get(money - entry.getKey()).get(0) ? table.get(money - entry.getKey()).get(0) : entry.getValue().get(0);
                s = entry.getValue().get(0) > table.get(money - entry.getKey()).get(0) ? entry.getValue().get(0) : table.get(money - entry.getKey()).get(0);
                System.out.println(f + " " + s);
                break;
            }
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int money = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> cost = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                Result.whatFlavors(cost, money);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}

