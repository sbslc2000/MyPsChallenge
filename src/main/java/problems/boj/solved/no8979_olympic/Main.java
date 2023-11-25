package problems.boj.solved.no8979_olympic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 문제 이름 : 올림픽
 * 링크 : https://www.acmicpc.net/problem/8979
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-21
 * 관련 링크 : https://codechacha.com/ko/java-sorting-array/
 * 
 * 메모리 제한 :  128 MB
 * 시간 제한 : 1초
 * 
 * 입력 국가의 수를 봤을 때 시간 복잡도를 크게 신경써야 하지는 않아 보인다
 * 
 */
public class Main {

    static int N;
    static int K;
    static int[][] medals;
    public static void main(String[] args) throws IOException {
        //var
        String s;
        String[] splited;
        int i, j;

        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        s = br.readLine();
        splited = s.split(" ");

        N = Integer.parseInt(splited[0]);
        K = Integer.parseInt(splited[1]);

        medals = new int[N][4];
        for(i = 0; i < N; i++) {
            s = br.readLine();
            splited = s.split(" ");
            for (j = 0; j < 4; j++) {
                medals[i][j] = Integer.parseInt(splited[j]);
            }
        }

        // print();

        //logic
        //일단 정렬? 정렬 후에는?
        //다시 스캔해서 등수 결정 할 수도 
        //이 때 다시 스캔하는 비용은 상수 취급됨 N < N log N
        //한번의 스캔으로 등수 결정할 수 있겠지? -> 있다!
        //정렬이 여러번 수행되지만 결국 O(N log N)으로 해결 가능
        //N^2 정렬로도 가능할 것 같긴 하다.
        //자바의 기능을 활용할까? 직접 구현할까?
        //객체는 Comparator 인터페이스 구현을 통해 정렬 가능한데 원시타입은?
        //메달 개수를 객체화 하는 풀이 -> 객체 생성에 드는 비용은?
        // 사실 이렇게 해도 저렇게 해도 풀 수 있지만,,
        // 고냥 연습도 해볼겸 일단 MergeSort 직접 구현하되
        //(안정성 있어서 메달 별 정렬해도 순서 유지 가능)
        //이 안정성을 제공하는 부분은 결국 merge에서 값이 동일할 때
        //왼쪽 배열에서 가져올지 오른쪽에서 가져올지 잘 결정되어야 한다!
        // 여러 col을 기준으로 정렬하게 만들어야 겠다.

        mergeSort(0, N-1, 3);
        mergeSort(0, N-1, 2);
        mergeSort(0, N-1, 1);
     
        // print();

        int rank = getRanking();
        

        //output
        System.out.println(rank);
    }

    private static int getRanking() {
        int i = 0;
        int beforeRank = 1;

        while(true) {
            
            if(medals[i][0] == K) {

                if(i == 0) {
                    return 1;
                } else if (isSameWith(medals[i], medals[i-1])) {
                    return beforeRank;
                } else {
                    return i+1;
                }
                
            } else {

                if(i == 0 || !isSameWith(medals[i], medals[i-1])) {
                    beforeRank = i+1;
                } 

                i++;
            }
        }
    }


    private static void mergeSort(int l, int r, int c) {
        // System.out.printf("l, r, c : %d, %d, %d\n",l,r,c);

        if(l < r) {
            int p = (l + r) / 2;
            mergeSort(l, p, c);
            mergeSort(p+1, r, c);
            merge(l, p, r, c);
        } 
    }

    private static void merge(int l, int p, int r, int c) {
        int lp = l, rp = p+1;
        int tmpP = 0;
        int i;

        int[][] tmp = new int[r-l+1][4];
        while(lp <= p && rp <= r) {
            if(medals[lp][c] < medals[rp][c]) { 
                // 이 부분에서 동등한 값 비교시 
                //stable 해질 수 있게 결정할 수 있네
                moveTo(medals[rp++], tmp[tmpP++]);
            } else {
                moveTo(medals[lp++], tmp[tmpP++]);
            }
        }

        while(lp <= p) {
            moveTo(medals[lp++], tmp[tmpP++]);
        }

        while(rp <= r) {
            moveTo(medals[rp++], tmp[tmpP++]);
        }

        lp = l;
        for(i = 0; i < r-l+1; i++) {
            moveTo(tmp[i], medals[lp++]);
        }
    }

    private static void moveTo(int[] from, int[] to) {
        to[0] = from[0];
        to[1] = from[1];
        to[2] = from[2];
        to[3] = from[3];
    }

    private static boolean isSameWith(int[] a, int[] b) {
        return a[1] == b[1] && a[2] == b[2] && a[3] == b[3];
    }

    // private static void print() {
    //     int i;
    //     System.out.println("-=-=-=-=-=-=-=-=-");
    //     for(i = 0; i < N; i++) {
    //         System.out.printf("%d %d %d %d\n",
    //         medals[i][0], medals[i][1], medals[i][2], medals[i][3]);
    //     }
    // }
}
