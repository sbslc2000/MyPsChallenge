package problems.boj.solved.no_1966_printer_queue;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 프린터 큐
 * 링크 : https://www.acmicpc.net/problem/1966
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-27
 * 관련 링크 : 
 * 메모리 제한 : 128MB
 * 시간 제한 : 2초
 */
public class Main {

    static class MyPriorityQueue {

        private static class Node {
            int idx;
            int priority;

            public Node(int idx, int priority) {
                this.idx = idx;
                this.priority = priority;
            }
        }
        
        private Queue<Node> q = new LinkedList<>();
        private int[] priorities = new int[10];
        private int p;

        private void refresh() {
            for(int i = 9; i > 0; i--) {
                if(priorities[i] != 0) {
                    p = i;
                    break;
                }
            }
        }

        public void add(int idx, int priority) {
            q.add(new Node(idx, priority));
            priorities[priority]++;
            refresh();
        }

        public int poll() {
            while(true) {
                Node n = q.poll();
                if(n.priority == p) {
                    priorities[p]--;
                    refresh();
                    return n.idx;
                } else {
                    q.add(n);
                }
            }
        }

    }

    static int T, N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for(int i = 0; i < T; i++) {
            String[] splited = br.readLine().split(" ");
            N = Integer.parseInt(splited[0]);
            M = Integer.parseInt(splited[1]);

            MyPriorityQueue q = new MyPriorityQueue();

            int[] priorities = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            for(int j = 0; j < priorities.length; j++) {
                q.add(j, priorities[j]);
            }

            int count = 0;
            while(true) {
                count++;

                int idx = q.poll();

                if(idx == M) {
                    break;
                } 
            }

            System.out.println(count);
        }


    }
}
