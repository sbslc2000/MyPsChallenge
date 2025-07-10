package problems.boj.unsolved.no_22870_walk_large;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M, S, E;
    static List<List<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");
        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        for(int i = 0; i < N; i++) {
            graph = new LinkedList<>();
        }

        for(int i = 0; i < M; i++) {
            splited = br.readLine().split(" ");
            int A = Integer.parseInt(splited[0]);
            int B = Integer.parseInt(splited[1]);
            int C = Integer.parseInt(splited[2]);

            List<int[]> nodes = graph.get(A);
            int[] newNode = new int[]{ B, C};
            if (nodes.size() == 0) {
                nodes.add(newNode);
            } else if (nodes.get(nodes.size() - 1)[0] < B) {
                nodes.add(newNode);
            }
            else {
                for(int j = 0; j < nodes.size(); j++) {
                    if(nodes.get(j)[0] > B) {
                        continue;
                    } else {
                        nodes.add(j, newNode);
                    }
                }
            }
        }



    }    
}
