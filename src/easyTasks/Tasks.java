package easyTasks;

import java.util.ArrayList;
import java.util.List;

public class Tasks {
    public static int jumpingOnClouds(List<Integer> c) {
        int size = c.size();
        int result = 0;
        int count = 0;
        while (count < size - 1) {
            if (count + 2 < size && c.get(count + 2) == 0) {
                count = count + 2;
            } else {
                count++;
            }
            result++;
        }
        return result;
    }
    public static int countingValleys(int steps, String path) {
        int result = 0;
        boolean flag = false;
        int seaLvl = 0;
        for (int i = 0; i < steps; i++) {
            if (path.charAt(i) == 'U') {
                if (seaLvl + 1 == 0) {
                    if (flag) {
                        result++;
                        flag = false;
                    }
                }
                seaLvl++;
            }
            if (path.charAt(i) == 'D') {
                if (seaLvl - 1 < 0) {
                    flag = true;
                }
                seaLvl--;
            }
        }
        return result;
    }
    public static long repeatedString(String s, long n){

        return 0;
    }

    public static void main(String[] args) {
        //clouds
        Integer[] t = {0, 0, 1, 0, 0, 1, 0};
        List<Integer> c = new ArrayList<>(List.of(t));
        System.out.println(jumpingOnClouds(c));
        //valleys
        System.out.println(countingValleys(8, "UDDDUDUU"));
        //
    }
}
