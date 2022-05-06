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

    public static int makeAnagram(String a, String b) {
        // Write your code here
        int result = 0;
        Map<Character, Integer> str = new HashMap<>();
        char[] charArray = b.length() < a.length() ? a.toCharArray() : b.toCharArray();
        for (int i = 0; i < (b.length() < a.length() ? a.length() : b.length()); i++) {
            if (str.containsKey(charArray[i])) {
                str.put(charArray[i], str.get(charArray[i]) + 1);
            } else {
                str.put(charArray[i], 1);
            }
        }
        charArray = b.length() > a.length() ? a.toCharArray() : b.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if(str.containsKey(charArray[i])){
                if(str.get(charArray[i]) > 0){
                    str.put(charArray[i], str.get(charArray[i]) - 1);
                }else{
                    result++;
                }
            }else{
                result++;
            }
        }
        return result;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String a = bufferedReader.readLine();

        String b = bufferedReader.readLine();

        int res = Result.makeAnagram(a, b);

        System.out.println(res);

        bufferedReader.close();

    }
}
