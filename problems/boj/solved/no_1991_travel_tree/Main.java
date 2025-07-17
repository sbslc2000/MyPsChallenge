package problems.boj.solved.no_1991_travel_tree;

import java.util.*;

/**
 * 문제 이름 : 트리 순회
 * 링크 : https://www.acmicpc.net/problem/1991
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-09-25
 * 관련 링크 : 
 * 메모리 제한 : 128MB
 * 시간 제한 : 2초
 */
public class Main {
    
    private static Map<String, List<String>> tree = new HashMap<>();

    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            String _data = sc.next();
            String _left = sc.next();
            String _right = sc.next();
            List<String> l = new ArrayList<>();
            l.add(_left);
            l.add(_right);
            tree.put(_data, l);
        }


        printPreorder("A");
        System.out.println();
        printInorder("A");
        System.out.println();
        printPostorder("A");
        System.out.println();

        sc.close();
    }

    private static void printPreorder(String n) {
        if(n.equals(".")) return;
        List<String> children = tree.get(n);
        System.out.print(n);
        printPreorder(children.get(0));
        printPreorder(children.get(1));
    }

    private static void printInorder(String n) {
        if(n.equals(".")) return;
        List<String> children = tree.get(n);
        printInorder(children.get(0));
        System.out.print(n);
        printInorder(children.get(1));
    }

    private static void printPostorder(String n) {
        if(n.equals(".")) return;
        List<String> children = tree.get(n);
        printPostorder(children.get(0));
        printPostorder(children.get(1));
        System.out.print(n);
    }
}
