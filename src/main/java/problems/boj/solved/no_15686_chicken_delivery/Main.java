package problems.boj.solved.no_15686_chicken_delivery;

import java.awt.Point;
import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 치킨 배달
 * 링크 : https://www.acmicpc.net/problem/15686
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-09
 * 관련 링크 :
 * 
 * 메모리 제한 : 512 MB
 * 시간 제한 : 1초
 */
public class Main {

    static int N;
    static int M;
    static int min = Integer.MAX_VALUE;

    static List<Point> homes = new ArrayList<>();
    static List<Point> bbq = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] splited;

        splited = br.readLine().split(" ");
        N = Integer.parseInt(splited[0]);
        M = Integer.parseInt(splited[1]);

        for(int i = 1; i <= N; i++) {
            splited = br.readLine().split(" ");
            for(int j = 1; j <= N; j++) {
                switch(splited[j - 1]) {
                    case "1":
                        homes.add(new Point(i, j));
                        break;
                    case "2":
                        bbq.add(new Point(i, j));
                        break;
                    default:
                        break;
                }
            }
        }

        //logic
        bt();

        //output
        System.out.println(min);
    }

    private static List<Integer> selected = new LinkedList<>();

    private static void bt() {
        if(selected.size() == M) {
            min = Math.min(calcChickenDistanceOfCity(), min);
            return;
        }

        int start = selected.size() == 0 ? -1 : selected.get(selected.size() - 1);

        for(int i = start + 1; i < bbq.size(); i++) {
            selected.add(i);
            bt();
            selected.remove(selected.size() - 1);
        }

    }

    private static int calcChickenDistanceOfCity() {
        return homes.stream()
            .mapToInt(Main::calcChickenDistance)
            .sum();
    }

    private static int calcChickenDistance(Point home) {
        int min = Integer.MAX_VALUE;
        for(Integer i : selected) {
            min = Math.min(min, calcDistance(home, bbq.get(i)));
        }
        return min;
    } 

    private static int calcDistance(Point a, Point b) {
        return Math.abs(b.x - a.x) + Math.abs(b.y - a.y);
    }
    
}
