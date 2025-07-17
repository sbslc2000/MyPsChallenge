// package problems.boj.unsolved.no_14658_falling_stars;
// import java.util.*;
// import java.io.*;
// /**
//  * 문제 이름 : 하늘에서 별똥별이 빗발친다
//  * 링크 : https://www.acmicpc.net/problem/14658
//  * 알고리즘 분류 : ?
//  * 작성 일시 : 11-28
//  * 관련 링크 : 
//  */
// public class Main {
//     static class Point {
//         int x;
//         int y;

//         static Point of(int x, int y) {
//             Point p = new Point();
//             p.x = x;
//             p.y= y;
//             return p;
//         }

//         boolean isIn(int sx, int sy, int ex, int ey) {
//             if(sx <= x && x <= ex && sy <= y && y <= ey) {
//                 return true;
//             } 

//             return false;
//         }

//         // 0 0 0 0 0
//         // 0 0 0 0 0 
//         // 0 0 0 0 0

//         List<List<Integer>> getRanges() {
//             List<List<Integer>> ranges = new ArrayList<>();

//             //좌상단
//             if(x - L - 1 > 0 && y - L - 1 > 0) {
//                 List<Integer> items = new ArrayList<>();
//                 items.add(x-L-1);
//                 items.add(y-L-1);
//                 items.add(x);
//                 items.add(y);
//                 ranges.add(items);
//             }

//             //좌상단
//             if(x - L - 1 > 0 && y + L + 1 <= M) {
//                 List<Integer> items = new ArrayList<>();
//                 items.add(x - L - 1);
//                 items.add(y);
//                 items.add(x);
//                 items.add(y + L + 1);
//                 ranges.add(items);
//             }


//             //우하단
//             if(x + L + 1 <= N && y + L+ 1 <= M) {
//                 List<Integer> items = new ArrayList<>();
//                 items.add(x);
//                 items.add(y);
//                 items.add(x+L+1);
//                 items.add(y+L+1);
//                 ranges.add(items);
//             }

//             //좌하단
//             if(x + L + 1 <= N && y - L - 1 > 0) {
//                 List<Integer> items = new ArrayList<>();
//                 items.add(x);
//                 items.add(y - L - 1);
//                 items.add(x + L + 1);
//                 items.add(y);
//                 ranges.add(items);
//             }

//             return ranges;
//         }

//         // boolean isStartValid() {
//         //     // 0 + 3 > 3 || 0 + 4 > 5
//         //     if(x + L > N || y + L > M) {
//         //         return false;
//         //     } 

//         //     return true;
//         // }

        
//     }

//     static int N;
//     static int M;
//     static int L;
//     static int K;

//     static int min = Integer.MAX_VALUE;

//     public static void main(String[] args) throws Exception {
//         //var
//         int i, j;
//         String[] splited;
//         List<Point> stars = new ArrayList<>();
//         //input
//         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//         splited = br.readLine().split(" ");
//         N = Integer.parseInt(splited[0]);
//         M = Integer.parseInt(splited[1]);
//         L = Integer.parseInt(splited[2]);
//         K = Integer.parseInt(splited[3]);

//         for(i = 0; i < K; i++) {
//             splited = br.readLine().split(" ");
//             stars.add(Point.of(
//                 Integer.parseInt(splited[0]),
//                 Integer.parseInt(splited[1])
//             ));
//         }

//         //logic
//         int sx, sy, ex, ey;
//         int fallings;
//         for(Point pi : stars) {
//             System.out.println();
//             System.out.printf("START %d %d\n",pi.x, pi.y);

//             List<List<Integer>> ranges = pi.getRanges();
//             for(List<Integer> r : ranges) {
//                 sx = r.get(0);
//                 sy = r.get(1);
//                 ex = r.get(2);
//                 ey = r.get(3);

//                 System.out.printf("RANGE : %d %d %d %d\n",sx,sy,ex,ey);

//                 fallings = 0;

//                 for(Point pj : stars) {
//                     if(!pj.isIn(sx,sy,ex,ey)) {
//                         fallings++;
//                         System.out.printf("STAR %d %d falls\n", pj.x, pj.y);
//                     } else {
//                         System.out.printf("STAR %d %d 튕!\n",pj.x, pj.y);
//                     }
//                 }

//                 System.out.printf("POINT %d %d MIN :%d\n",pi.x, pi.y, fallings);
//                 if(min > fallings) {
//                     min = fallings;
//                 }
//             }
//         } 

//         System.out.println(min);

//     //     int sx, sy, ex, ey;
//     //     int fallings;
//     //     for(i = 1; i < N - L - 1; i++) {
//     //         for(j = 1; j < M - L - 1; j++) {
//     //             sx = i;
//     //             sy = j;
//     //             ex = i + L + 1;
//     //             ey = j + L + 1;

//     //             fallings = 0;

//     //              for(Point pj : stars) {
//     //                 if(!pj.isIn(sx,sy,ex,ey)) {
//     //                     fallings++;
//     //                 } else {
//     //                 }
//     //             }

//     //             if(min > fallings) {
//     //                 min = fallings;
//     //             }

//     //         }
//     //     }

//     //     System.out.println(min);
//     // }

 
// }
