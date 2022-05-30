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

    public static void inorder_print(Node root, int K){
        if (root != null){
            if(root.depth % K == 0){
                root.swap();
            }
            inorder_print(root.left, K);
            System.out.print(root.value+" ");
            inorder_print(root.right, K);
        }
    }

    public static void print_nodes(Node root, int K){
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
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> indexes = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                indexes.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<List<Integer>> result = Result.swapNodes(indexes, queries);

        bufferedReader.close();
    }
}

