package StringManipulation;

import javax.swing.plaf.basic.BasicColorChooserUI;
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
    public static String isValid(String s) {
        char[] ascii = new char[255];
        char[] charS = s.toCharArray();
        for (int i = 0; i < charS.length; i++) {
            ascii[charS[i]]++;
        }
        char temp = 0;
        int count = 0;
        for (int i = 0; i < 255; i++) {
            if(count > 1){
                return "NO";
            }
            if (ascii[i] != 0) {
                if (temp == 0) {
                    temp = ascii[i];

                } else {
                    int r = Math.abs(temp - ascii[i]);
                    if(r == 1){
                        count++;
                    }
                    if(r > 1){
                        return "NO";
                    }
                    temp = ascii[i];
                }
            }
        }
        return "YES";
    }

    public static int alternatingCharacters(String s) {
        // Write your code here
        int result = 0;
        char[] charString = s.toCharArray();
        for (int i = 1; i < charString.length; i++) {
            if (charString[i] == charString[i - 1]) {
                result++;
            }
        }
        return result;
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

        String s = bufferedReader.readLine();

        String result = Result.isValid(s);

        System.out.println(result);

        bufferedReader.close();

    }
}
