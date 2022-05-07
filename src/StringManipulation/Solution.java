package StringManipulation;

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
     * Complete the 'makeAnagram' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts following parameters:
     *  1. STRING a
     *  2. STRING b
     */
    public static int alternatingCharacters(String s) {
        // Write your code here
        return 0;
    }

    public static int makeAnagram(String a, String b) {
        // Write your code here
        int result = 0;
        Map<Character, Integer> str = new HashMap<>();
        char[] charArray = a.toCharArray();
        for (int i = 0; i < a.length(); i++) {
            if (str.containsKey(charArray[i])) {
                str.put(charArray[i], str.get(charArray[i]) + 1);
            } else {
                str.put(charArray[i], 1);
            }
        }
        charArray = b.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (str.containsKey(charArray[i])) {
                if (str.get(charArray[i]) > 0) {
                    str.put(charArray[i], str.get(charArray[i]) - 1);
                } else {
                    result++;
                }
            } else {
                result++;
            }
        }
        for (Map.Entry<Character, Integer> entry : str.entrySet()
        ) {
            if (entry.getValue() > 0) {
                result += entry.getValue();
            }
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s = bufferedReader.readLine();

                int result = Result.alternatingCharacters(s);
                System.out.println(result);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });


        bufferedReader.close();

    }
}
