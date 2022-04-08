package DictionariesAndHashmaps;

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

import static java.util.stream.Collectors.*;

public class Tasks {

    public static List<Integer> freqQuery(List<List<Integer>> queries) {
        return null;
    }

    public static long countTriplets(List<Long> arr, long r) {
        long result = 0L;
        HashMap<Long, Long> rightMap = new HashMap<>();
        HashMap<Long, Long> leftMap = new HashMap<>();
        for (Long t : arr
        ) {
            if (rightMap.containsKey(t)) {
                rightMap.put(t, rightMap.get(t) + 1L);
            } else {
                rightMap.put(t, 1L);
            }
        }
        for (Long t : arr
        ) {
            long leftCount = 0;
            long rightCount = 0;
            long rElem = t * r;
            long lElem = 0;
            if (t % r == 0) {
                lElem = t / r;
            }

            rightMap.put(t, rightMap.get(t) - 1L);

            if (rightMap.containsKey(rElem)) {
                rightCount = rightMap.get(rElem);
            }
            if (leftMap.containsKey(lElem)) {
                leftCount = leftMap.get(lElem);
            }
            result += rightCount * leftCount;

            if (leftMap.containsKey(t)) {
                leftMap.put(t, leftMap.get(t) + 1L);
            } else {
                leftMap.put(t, 1L);
            }


        }
        return result;
    }

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
            if (letters.contains(p.charAt(i))) {
                return "YES";
            }
        }
        return "NO";
    }

    public static int sherlockAndAnagrams(String s) {
        // Write your code here
        int result = 0;
        for (int i = 1; i < s.length(); i++) {
            for (int j = 0; j <= s.length() - i; j++) {
                for (int k = j + 1; k <= s.length() - i; k++) {
                    result += anagrams(s.substring(j, j + i), s.substring(k, k + i));
                }
            }
        }
        return result;
    }

    private static int anagrams(String s, String p) {
        HashMap<Character, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (hashMap.containsKey(s.charAt(i))) {
                hashMap.put(s.charAt(i), hashMap.get(s.charAt(i)) + 1);
            } else {
                hashMap.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < p.length(); i++) {
            if (hashMap.containsKey(p.charAt(i))) {
                if (hashMap.get(p.charAt(i)) == 0) {
                    return 0;
                } else {
                    hashMap.put(p.charAt(i), hashMap.get(p.charAt(i)) - 1);
                }
            } else {
                return 0;
            }
        }
        return 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] nr = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> queries = new ArrayList<>();

        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        List<Integer> ans = freqQuery(queries);
        System.out.println(ans);
        bufferedReader.close();
    }
}
