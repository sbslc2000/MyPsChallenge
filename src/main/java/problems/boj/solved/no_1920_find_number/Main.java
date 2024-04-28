package problems.boj.solved.no_1920_find_number;

import java.io.*;
import java.util.*;
 
public class Main {

    static int N, M;

    static int[] A;
    static int[] B;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        A = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        
        M = Integer.parseInt(br.readLine());

        B = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        Arrays.sort(A);

        for(int i = 0; i < M; i++) {
            int idx = binarySearch(B[i],0, N);
            if(idx == -1) {
                System.out.println(0);
            } else {
                System.out.println(1);
            }
        }
    

    }

    private static int binarySearch(int target, int left, int right) {
        if(left == right) return -1;
        int mid = (right + left) / 2;

        if(A[mid] == target) {
            return mid;
        } else if(A[mid] > target) {
            return binarySearch(target, left, mid);
        } else {
            return binarySearch(target, mid + 1, right);
        }


    }
}
