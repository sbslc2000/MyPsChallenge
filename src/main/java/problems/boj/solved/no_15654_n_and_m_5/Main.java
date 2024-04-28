package problems.boj.solved.no_15654_n_and_m_5;

import java.util.*;
import java.io.*;
/**
 * 문제 이름 : N과 M(5)
 * 링크 : https://www.acmicpc.net/problem/15654
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-22
 * 관련 링크 :
 * 
 * 메모리 제한 : 512 MB
 * 시간 제한 : 1초
 */
public class Main {

    static int N, M;
    static int[] nums;
    static List<Integer> selected = new LinkedList<>();
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] splited = br.readLine().split(" ");
        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        nums = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .sorted()
            .toArray();

        search();
        bw.flush();
    }

    private static void search() throws Exception{
        if(selected.size() == M) {
            printList();
        }

        for(int i = 0; i < N; i++) {
            if(!selected.contains(i)) {
                selected.add(i);
                search();
                selected.remove(selected.size() - 1);
            }
        }
    }

    private static void printList() throws Exception{
        for(Integer i : selected) {
            bw.write(nums[i] + " ");
        }
        bw.write('\n');
    }
    
}
