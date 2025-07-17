package problems.boj.solved.no_18870_point_compaction;

import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] points;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        points = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

    }
}
