package StringManipulation;

import edu.princeton.cs.algs4.In;

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
        HashMap<Character, Integer> countLetters = new HashMap<>();
        char[] charS = s.toCharArray();
        int max = 1;
        char minLetter = '0';
        for (int i = 0; i < charS.length; i++) {
            if (countLetters.containsKey(charS[i])) {
                countLetters.put(charS[i], countLetters.get(charS[i]) + 1);
                if (max < countLetters.get(charS[i])) {
                    max = countLetters.get(charS[i]);

                }
            } else {
                countLetters.put(charS[i], 1);
            }
        }
        int countM = 0;
        boolean flag = true;
        for (Map.Entry<Character, Integer> ascii : countLetters.entrySet()
        ) {
            if (max - ascii.getValue() == 1) {
                countM++;
                minLetter = ascii.getKey();
            }
            if (max - ascii.getValue() > 1) {
                if(ascii.getValue() == 1){
                    if (flag) {
                        flag = false;
                    } else {
                        return "NO";
                    }
                }else{
                    return "NO";
                }
            }
        }
        if (countM == 0) return "YES";
        if (countM == countLetters.size() - 1) {
            return "YES";
        }
        if (countM == 1 && countLetters.get(minLetter) == 1) {
            return "YES";
        }
        return "NO";
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
