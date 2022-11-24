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
        if (arr.size() == 0) return 0;
        if (arr.size() == 1) return Math.max(0, arr.get(0));
        arr.set(0, Math.max(arr.get(0), 0));
        arr.set(1, Math.max(arr.get(1), arr.get(0)));
        for (int i = 2; i < arr.size(); i++) {
            arr.set(i, Math.max(arr.get(i - 2) + arr.get(i), arr.get(i - 1)));
        }
        return arr.get(arr.size() - 1);
    }

    public static String abbreviation(String a, String b) {
        // Write your code here
        if (b.length() > a.length()) {
            return "NO";
        }
        char[] c = a.toCharArray();
        char[] d = b.toCharArray();
        boolean[][] f = new boolean[c.length + 1][d.length + 1];
        f[0][0] = true;
        for (int i = 1; i < f.length; i++) {
            f[i][0] = f[i-1][0] && Character.isLowerCase(c[i-1]);
        }
        for (int i = 1; i < c.length + 1; i++) {
            for (int j = 1; j < d.length + 1; j++) {
                f[i][j] = (f[i-1][j-1] && Character.toUpperCase(c[i-1]) == d[j-1]) ||
                        (f[i-1][j] && Character.isLowerCase(c[i-1]));
            }
        }
        return f[a.length()][b.length()] ? "YES" : "NO";
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String a = bufferedReader.readLine();

                String b = bufferedReader.readLine();

                String result = Result.abbreviation(a, b);

                System.out.println(result);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
