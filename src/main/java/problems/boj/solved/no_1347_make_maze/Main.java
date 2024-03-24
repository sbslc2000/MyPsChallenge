package problems.boj.solved.no_1347_make_maze;

import java.util.*;
import java.io.*;
import java.awt.Point;

/**
 * 문제 이름 : 미로 만들기
 * 링크 : https://www.acmicpc.net/problem/1347
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-03-23
 * 관련 링크 : 
 */
public class Main {

    static int N;

    static enum Direction {
        SOUTH(0,-1), // left -> EAST, right -> WEST
        WEST(-1, 0), // left -> SOUTH, right -> NORTH
        NORTH(0, 1), // left -> WEST, right -> EAST
        EAST(1, 0); // left -> NORTH, right -> WEST

        int dx;
        int dy;

        Direction(int dx, int dy) {
            this.dx = dx;
            this.dy = dy;
        }

        public Direction left() {
            return Direction.values()[(this.ordinal() + 3) % 4];
        }

        public Direction right() {
            return Direction.values()[(this.ordinal() + 1) % 4];
        }
    }

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String note = br.readLine();

        //logic 
        Set<Point> visited = new HashSet<>();
        visited.add(new Point(0,0));

        int x = 0, y = 0;
        Direction d = Direction.SOUTH;

        for(int i = 0; i < note.length(); i++) {
            switch(note.charAt(i)) {
                case 'R':
                    d = d.right();
                    break;
                case 'L':
                    d = d.left();
                    break;
                case 'F':
                    x += d.dx;
                    y += d.dy;
                    break;
            }

            // System.out.printf("%d %d\n", x, y);
            // System.out.println(d);
            visited.add(new Point(x, y));
        }
      
        // for(Point p : visited) {
        //     System.out.println(p);
        // }

        int left = findLeftBound(visited);
        int right = findRightBound(visited);
        int upper = findUpperBound(visited);
        int lower = findLowerBound(visited);

        // System.out.printf("left : %d, right : %d\n", left, right);
        // System.out.printf("upper: %d, lower: %d\n", upper, lower);

        for(int i = upper; i >= lower; i--) {
            for(int j = left; j <= right; j++) {
                if(visited.contains(new Point(j,i))) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }

    }

    static int findLeftBound(Set<Point> s) {
        return s.stream().sorted((p1, p2) -> p1.x - p2.x)
            .findFirst().get().x;
    }

    static int findRightBound(Set<Point> s) {
        return s.stream().sorted((p1, p2) -> p2.x - p1.x) 
            .findFirst().get().x;
    }

    static int findUpperBound(Set<Point> s) {
        return s.stream().sorted((p1, p2) -> p2.y - p1.y)
            .findFirst().get().y;
    }

    static int findLowerBound(Set<Point> s) {
        return s.stream().sorted((p1, p2) -> p1.y - p2.y)
            .findFirst().get().y;
    }
}
