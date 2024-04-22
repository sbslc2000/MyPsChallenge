package problems.boj.solved.no_1253_good;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * 문제 이름 : 좋다
 * 링크 : https://www.acmicpc.net/problem/1253
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-21
 * 관련 링크 : 
 * 
 * 메모리 제한 : 256 MB
 * 시간 제한 : 2초
 * 
 * 정렬 후 양측에서 sum을 찾기 위해 좁혀나가되
 * 수가 중복 선택되는 경우만 제거하자
 */
public class Main {

    static int N;

    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] nums = Arrays.stream(br.readLine().split(" "))
            .mapToInt(Integer::parseInt)
            .toArray();

        //logic
        Arrays.sort(nums);

        int left,right,count = 0;
        long sum;
        for(int i = 0; i < N; i++) {
            left = 0;
            right = N - 1;

            while(left < right) {
                if(left == i) {
                    left++;
                    continue;
                }
                
                if(right == i) {
                    right--;
                    continue;
                }

                sum = nums[left] + nums[right];
                if(sum == nums[i]) {

                    count++;
                    break;
                } else if(sum < nums[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        System.out.println(count);
    }
}
