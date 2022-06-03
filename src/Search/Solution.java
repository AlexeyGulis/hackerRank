package Search;

import com.sun.source.tree.BinaryTree;
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

class Result {

    static long triplets(int[] a, int[] b, int[] c) {

        return 0L;
    }

    public static int pairs(int k, List<Integer> arr) {
        // Write your code here
        int result = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < arr.size(); i++) {
            set.add(arr.get(i));
        }
        for (int i = 0; i < arr.size(); i++) {
            if(set.contains(arr.get(i) - k)) result++;
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

        String[] lenaLenbLenc = scanner.nextLine().split(" ");

        int lena = Integer.parseInt(lenaLenbLenc[0]);

        int lenb = Integer.parseInt(lenaLenbLenc[1]);

        int lenc = Integer.parseInt(lenaLenbLenc[2]);

        int[] arra = new int[lena];

        String[] arraItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lena; i++) {
            int arraItem = Integer.parseInt(arraItems[i]);
            arra[i] = arraItem;
        }

        int[] arrb = new int[lenb];

        String[] arrbItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenb; i++) {
            int arrbItem = Integer.parseInt(arrbItems[i]);
            arrb[i] = arrbItem;
        }

        int[] arrc = new int[lenc];

        String[] arrcItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < lenc; i++) {
            int arrcItem = Integer.parseInt(arrcItems[i]);
            arrc[i] = arrcItem;
        }

        long ans = Result.triplets(arra, arrb, arrc);

        System.out.println(ans);

        bufferedReader.close();
    }
}

