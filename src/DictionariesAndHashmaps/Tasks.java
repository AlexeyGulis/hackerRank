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

    public static List<Integer> freqQuery(List<int[]> queries) {
        HashMap<Integer, Integer> q = new HashMap<>();
        HashMap<Integer, HashSet<Integer>> freqNumb = new HashMap<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            switch (queries.get(i)[0]) {
                case 1: {
                    if (q.containsKey(queries.get(i)[1])) {
                        freqNumb.get(q.get(queries.get(i)[1])).remove(queries.get(i)[1]);
                        q.put(queries.get(i)[1], q.get(queries.get(i)[1]) + 1);
                        if (!freqNumb.containsKey(q.get(queries.get(i)[1]))) {
                            freqNumb.put(q.get(queries.get(i)[1]), new HashSet<>());
                        }
                        freqNumb.get(q.get(queries.get(i)[1])).add(queries.get(i)[1]);
                    } else {
                        q.put(queries.get(i)[1], 1);
                        if (!freqNumb.containsKey(1)) {
                            freqNumb.put(1, new HashSet<>());
                        }
                        freqNumb.get(1).add(queries.get(i)[1]);
                    }
                    break;
                }
                case 2: {
                    if (q.containsKey(queries.get(i)[1])) {
                        freqNumb.get(q.get(queries.get(i)[1])).remove(queries.get(i)[1]);
                        q.put(queries.get(i)[1], q.get(queries.get(i)[1]) - 1);
                        if (!freqNumb.containsKey(q.get(queries.get(i)[1]))) {
                            freqNumb.put(q.get(queries.get(i)[1]), new HashSet<>());
                        }
                        freqNumb.get(q.get(queries.get(i)[1])).add(queries.get(i)[1]);
                        if (q.get(queries.get(i)[1]) == 0) {
                            q.remove(queries.get(i)[1]);
                        }
                    }
                    break;
                }
                case 3: {
                    if (freqNumb.containsKey(queries.get(i)[1]) && freqNumb.get(queries.get(i)[1]).size() != 0) {
                        System.out.println("1");
                        ans.add(1);
                    } else {
                        System.out.println("0");
                        ans.add(0);
                    }
                    break;
                }
            }
        }
        return ans;
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
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery(queries);
            System.out.println(ans);
        }
    }
}
