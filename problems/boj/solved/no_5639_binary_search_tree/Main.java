package problems.boj.solved.no_5639_binary_search_tree;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 이진 검색 트리
 * 링크 : https://www.acmicpc.net/problem/5639
 * 알고리즘 분류 : 
 * 작성 일시 : 2024-09-25
 * 관련 링크 : 
 */
public class Main {

    static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int data = Integer.parseInt(br.readLine());
        Node root = new Node(data);
        
        while(true) {
            String input = br.readLine();

            if (input == null) {
                break;
            }

            save(root, Integer.parseInt(input));
        }

        print(root);
        bw.flush();
    }

    private static void save(Node root, int d) {
        Node p = root;

        Node newNode = new Node(d);

        while(true) {
            if (p.data < d) {
                if (p.right == null) {
                    p.right = newNode;
                    break;
                } else {
                    p = p.right;
                }
            } else {
                if (p.left == null) {
                    p.left = newNode;
                    break;
                }
                p = p.left;
            }
        }
    }

    private static void print(Node n) throws Exception {
        if(n == null) {
            return;
        }

        print(n.left);
        print(n.right);

        bw.write(String.valueOf(n.data));
        bw.write('\n');
        
    }
}
