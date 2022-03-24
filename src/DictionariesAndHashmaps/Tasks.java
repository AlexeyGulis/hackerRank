package DictionariesAndHashmaps;

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

public class Tasks {
    public static void checkMagazine(List<String> magazine, List<String> note) {
        // Write your code here
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        boolean result = true;
        for (String p : magazine
        ) {
            if (hashtable.containsKey(p)) {
                hashtable.put(p, hashtable.get(p) + 1);
            } else {
                hashtable.put(p, 1);
            }
        }
        for (String p : note
        ) {
            if (hashtable.containsKey(p)) {
                if (hashtable.get(p) != 1) {
                    hashtable.put(p, hashtable.get(p) - 1);
                } else {
                    hashtable.remove(p);
                }
            } else {
                result = false;
                break;
            }
        }
        if (result) System.out.println("Yes");
        else System.out.println("No");
    }

    public static String twoStrings(String s1, String s2) {
        // Write your code here
        if (s1.length() > s2.length()) {
            return letterOfString(s2, s1);
        } else return letterOfString(s1, s2);
    }

    private static String letterOfString(String s, String p) {
        HashSet<Character> letters = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            letters.add(s.charAt(i));
        }
        for (int i = 0; i < p.length(); i++) {
            if (letters.contains(p.charAt(i))){return "YES";}
        }
        return "NO";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, q).forEach(qItr -> {
            try {
                String s1 = bufferedReader.readLine();

                String s2 = bufferedReader.readLine();

                String result = twoStrings(s1, s2);
                System.out.println(result);

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
    }
}
