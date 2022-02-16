package easyTasks;

import edu.princeton.cs.algs4.In;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


public class Tasks {
    public static int jumpingOnClouds(List<Integer> c) {
        int size = c.size();
        int result = 0;
        int count = 0;
        while (count < size - 1) {
            if (count + 2 < size && c.get(count + 2) == 0) {
                count = count + 2;
            } else {
                count++;
            }
            result++;
        }
        return result;
    }

    public static int countingValleys(int steps, String path) {
        int result = 0;
        boolean flag = false;
        int seaLvl = 0;
        for (int i = 0; i < steps; i++) {
            if (path.charAt(i) == 'U') {
                if (seaLvl + 1 == 0) {
                    if (flag) {
                        result++;
                        flag = false;
                    }
                }
                seaLvl++;
            }
            if (path.charAt(i) == 'D') {
                if (seaLvl - 1 < 0) {
                    flag = true;
                }
                seaLvl--;
            }
        }
        return result;
    }

    public static long repeatedString(String s, long n) {
        int length = s.length();
        int countA = length - s.replaceAll("a", "").length();
        long result = countA * (n / length);
        result = result + (n % length) - s.substring(0, (int) (n % length)).replaceAll("a", "").length();
        return result;
    }

    public static int hourglassSum(List<List<Integer>> arr) {
        int size = 6;
        int resultMax = Integer.MIN_VALUE;
        int temp = 0;
        for (int i = 0; i < size; i++) {
            if (i + 2 >= size) {
                break;
            }
            for (int j = 0; j < size; j++) {
                if (j + 2 >= size) {
                    break;
                }
                temp = arr.get(i).get(j) + arr.get(i).get(j + 1) + arr.get(i).get(j + 2) +
                        arr.get(i + 1).get(j + 1) +
                        arr.get(i + 2).get(j) + arr.get(i + 2).get(j + 1) + arr.get(i + 2).get(j + 2);
                if (resultMax <= temp) resultMax = temp;
            }
        }
        return resultMax;
    }

    public static List<Integer> rotLeft(List<Integer> a, int d) {
        if (a.size() == d) return a;
        Integer[] res = new Integer[a.size()];
        for (int i = 0; i < a.size(); i++) {
            res[i - d < 0 ? a.size() + (i - d) : i - d] = a.get(i);
        }
        return new ArrayList<>(List.of(res));
    }

    public static void minimumBribes(List<Integer> q) {
        int result = 0;
        Integer[] test = new Integer[q.size()];
        for (int i = 0; i < q.size(); i++) {
            test[i] = i + 1;
        }
        for (int i = 0; i < q.size(); i++) {
            if ((int)  test[i] != (int) q.get(i)) {
                int j = i + 1;
                while ((int) q.get(i) != (int) test[j]) {
                    j++;
                    if (j - i > 2) {
                        break;
                    }
                }
                if (j - i > 2) {
                    System.out.println("Too chaotic");
                    result = -1;
                    break;
                }
                int temp = q.get(i);
                System.arraycopy(test, i, test, i + 1, j - i);
                test[i] = temp;
                result += j - i;
            }
        }
        if (result != -1) System.out.println(result);
    }


    public static void main(String[] args) throws IOException {
        //clouds
        Integer[] t1 = {0, 0, 1, 0, 0, 1, 0};
        List<Integer> c = new ArrayList<>(List.of(t1));
        System.out.println(jumpingOnClouds(c));
        //valleys
        System.out.println(countingValleys(8, "UDDDUDUU"));
        //repeated Strings
        System.out.println(repeatedString("a", 1000000000000L));
        //hourglassSum
        //rotLeft
        //minimumBribes
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> q = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                minimumBribes(q);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
