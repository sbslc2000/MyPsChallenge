import java.awt.Point;
import java.util.*;
import java.io.*;
/**
 * 문제 이름 : 킹
 * 링크 : https://www.acmicpc.net/problem/1063
 * 알고리즘 분류 :
 * 작성 일시 : 2024-03-15
 * 관련 링크 :
 */
public class Main {

    static class Pair {

        final int a;
        final int b;

        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

    private static final Map<String, Pair> map = Map.of(
        "R", new Pair(1, 0),
        "L", new Pair(-1, 0),
        "B", new Pair(0, -1),
        "T", new Pair(0, 1),
        "RT", new Pair(1, 1),
        "LT", new Pair(-1, 1),
        "RB", new Pair(1, -1),
        "LB", new Pair(-1, -1)
    );

    private static class BoundedPoint extends Point{

        private final int bound;

        public BoundedPoint(int x, int y, int bound) {
            super(x, y);
            this.bound = bound;
        }

        boolean canTranslate(int dx, int dy) {
            return x + dx >= 0 && x + dx < bound && y + dy >= 0 && y + dy < bound;
        }
    }
    
    static int N;

    static BoundedPoint king;
    static BoundedPoint stone;



    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] splited = br.readLine().split(" ");

        N = Integer.parseInt(splited[2]);
        king = new BoundedPoint(splited[0].charAt(0) - 65, splited[0].charAt(1) - '0' - 1, 8);
        stone = new BoundedPoint(splited[1].charAt(0) - 65, splited[1].charAt(1) - '0' - 1, 8);

        System.out.println(king);
        System.out.println(stone);
        
        Pair p;
        for(int i = 0; i < N; i++) {
            p = map.get(br.readLine());
            System.out.printf("p : %d %d\n", p.a, p.b);
            
            if(king.canTranslate(p.a, p.b)) {
                king.translate(p.a, p.b);
            } 
            
            if(stone.canTranslate(p.a, p.b)) {
                System.out.println("translate");
                stone.translate(p.a, p.b);
            }

            System.out.println(king);
            System.out.println(stone);

        }
    }

}
