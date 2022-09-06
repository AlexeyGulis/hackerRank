package Search;

import com.sun.source.tree.BinaryTree;

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

    public static long minimumPasses(long m, long w, long p, long n) {
        // Write your code here
        long candy = 0L;
        long countDays = 0L;
        long production = 0L;
        long canBuy = 0L;
        if (n / m <= w) return 1;
        long daysRemaining = n % (m * w) == 0 ? n / (m * w) : (n / (m * w)) + 1;
        while (countDays <= daysRemaining) {
            if (Long.MAX_VALUE / m <= w) return countDays + 1;
            production = m * w;
            if (candy + production < p) {
                countDays = countDays + ((p - candy) / production) - 1;
                candy += (m * w) * ((p - candy) / production) - (m * w) ;
            }
            canBuy = (production + candy) / p;
            if (production + candy < n && canBuy > 0) {
                long v = Math.abs(m - w);
                if (canBuy > v) {
                    m = Math.max(m, w);
                    m = ((canBuy - v) % 2) == 1
                            ? m + ((canBuy - v) / 2) + 1
                            : m + ((canBuy - v) / 2);
                    w = ((canBuy - v) % 2) == 1
                            ? m - 1
                            : m;
                } else {
                    if (m > w) {
                        w += canBuy;
                    } else {
                        m += canBuy;
                    }
                }
                production -= canBuy * p;
            }
            candy += production;
            countDays++;
            daysRemaining = Math.min(daysRemaining,
                    countDays + (((n - candy) % (m * w)) == 0
                            ? ((n - candy) / (m * w))
                            : ((n - candy) / (m * w)) + 1L));
        }
        return Math.min(countDays, daysRemaining);
    }

    private static int indexSearch(List<Long> a, long m) {
        int low = 0;
        int high = a.size() - 1;
        while (low + 1 < high) {
            int mid = low + ((high - low) >>> 1);
            Long key = a.get(mid);
            if (key.compareTo(m) < 0) {
                low = mid;
            } else if (key.compareTo(m) >= 0) {
                high = mid;
            }
        }
        return low;
    }

    public static long maximumSum(List<Long> a, long m) {
        // Write your code here
        long result = Long.MAX_VALUE;
        Collections.sort(a);
        int index = indexSearch(a, m);
        System.out.println(index);
        return 0L;
    }

    private static long checkResult(long time, long[] machines) {
        long result = 0L;
        for (int i = 0; i < machines.length; i++) {
            result += time / machines[i];
        }
        return result;
    }

    private static long binarySearchTime(long min, long max, long[] machines, long goal) {
        long mid = min + (max - min) / 2;
        if (max - min == 1 && checkResult(min, machines) < goal && checkResult(max, machines) >= goal) {
            return max;
        } else {
            if (checkResult(min, machines) < goal) {
                if (checkResult(mid, machines) >= goal) {
                    return binarySearchTime(min, mid, machines, goal);
                } else {
                    return binarySearchTime(mid, max, machines, goal);
                }
            } else {
                return 0;
            }
        }
    }

    public static long minTime(long[] machines, long goal) {
        long maxValue = Arrays.stream(machines).max().getAsLong();
        long min = 0L;
        long max = goal <= machines.length ? 1 * maxValue : (goal / (long) machines.length) * maxValue;
        return binarySearchTime(min, max, machines, goal);
    }

    private static int binarySearchIndex(Integer[] a, int item) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (item == a[mid])
                return mid + 1;
            else if (item > a[mid])
                low = mid + 1;
            else
                high = mid - 1;
        }
        return low;
    }

    static long triplets(int[] a, int[] b, int[] c) {
        long result = 0L;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < a.length; i++) {
            set.add(a[i]);
        }
        Integer[] a1 = set.toArray(new Integer[set.size()]);
        set.clear();
        for (int i = 0; i < b.length; i++) {
            set.add(b[i]);
        }
        Integer[] b1 = set.toArray(new Integer[set.size()]);
        set.clear();
        for (int i = 0; i < c.length; i++) {
            set.add(c[i]);
        }
        Integer[] c1 = set.toArray(new Integer[set.size()]);
        Arrays.sort(a1);
        Arrays.sort(b1);
        Arrays.sort(c1);
        for (int i = 0; i < b1.length; i++) {
            int k = binarySearchIndex(a1, b1[i]);
            int l = binarySearchIndex(c1, b1[i]);
            result += (long) k * (long) l;
        }
        return result;
    }

    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        int result = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.size(); i++) {
            set.add(arr.get(i));
        }
        for (int i = 0; i < arr.size(); i++) {
            if (set.contains(arr.get(i) - k)) result++;
        }
        return result;
    }

    static class Node {
        public Node left;
        public Node right;
        public int value;
        public int depth;

        public Node(Node left, Node right, int value, int depth) {
            this.depth = depth;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public void swap() {
            Node temp = left;
            left = right;
            right = temp;
        }
    }

    public static void inorder_print(Node root, int K) {
        if (root != null) {
            if (root.depth % K == 0) {
                root.swap();
            }
            inorder_print(root.left, K);
            System.out.print(root.value + " ");
            inorder_print(root.right, K);
        }
    }

    public static void print_nodes(Node root, int K) {
        inorder_print(root, K);
        System.out.println();
    }

    public static List<List<Integer>> swapNodes(List<List<Integer>> indexes, List<Integer> queries) {
        // Write your code here
        Queue<Node> binaryQueue = new LinkedList<>();

        Node head = new Node(null, null, 1, 1);
        binaryQueue.add(head);
        for (int i = 0; i < indexes.size(); i++) {
            Node temp = binaryQueue.poll();
            if (temp != null) {
                temp.left = (indexes.get(i).get(0) == -1) ? null : new Node(null, null, indexes.get(i).get(0), temp.depth + 1);
                temp.right = (indexes.get(i).get(1) == -1) ? null : new Node(null, null, indexes.get(i).get(1), temp.depth + 1);
                binaryQueue.add(temp.left);
                binaryQueue.add(temp.right);
            } else {
                i--;
            }
        }
        for (int i = 0; i < queries.size(); i++) {
            print_nodes(head, queries.get(i));
        }
        return null;
    }

    public static void whatFlavors(List<Integer> cost, int money) {
        // Write your code here
        HashMap<Integer, Integer> tM = new HashMap<>();
        for (int i = 0; i < cost.size(); i++) {
            if (tM.containsKey(cost.get(i))) {
                System.out.println(tM.get(cost.get(i)) + " " + (i + 1));
            } else {
                tM.put(money - cost.get(i), i + 1);
            }
        }
    }
}

public class Solution {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        long m = Long.parseLong(firstMultipleInput[0]);

        long w = Long.parseLong(firstMultipleInput[1]);

        long p = Long.parseLong(firstMultipleInput[2]);

        long n = Long.parseLong(firstMultipleInput[3]);

        long result = Result.minimumPasses(m, w, p, n);

        System.out.println(result);

        bufferedReader.close();
    }
}

