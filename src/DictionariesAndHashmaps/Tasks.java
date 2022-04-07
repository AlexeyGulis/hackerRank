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

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Tasks {

    public static long countTriplets(List<Long> arr, long r){
        long result = 0L;
        int i = 0;
        int j = 0;
        int k = 0;
        Collections.sort(arr);
        for (i = 0; i < arr.size(); i++) {
            j = Collections.binarySearch(arr,arr.get(i) * r);
            if(j > 0){
                k = Collections.binarySearch(arr, arr.get(j) * r);
                if(k > 0){
                    result++;
                    int temp = j;
                    while(temp + 1 < arr.size() && arr.get(++temp) == arr.get(j)){
                        result++;
                    }
                    temp = j;
                    while(temp - 1 >= 0 && arr.get(--temp) == arr.get(j)){
                        result++;
                    }
                    temp = k;
                    while(temp + 1 < arr.size() && arr.get(++temp) == arr.get(k)){
                        result++;
                    }
                    temp = k;
                    while(temp - 1 >= 0 && arr.get(--temp) == arr.get(k)){
                        result++;
                    }
                }
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

        int n = Integer.parseInt(nr[0]);

        long r = Long.parseLong(nr[1]);

        List<Long> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        long ans = countTriplets(arr, r);
        System.out.println(ans);
        bufferedReader.close();
    }
}
