package problems.programmers.practice.long_jump;

/**
 * 문제 이름 : 멀리 뛰기
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12914?language=java#
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-03-19
 * 관련 링크 : 
 */
class Solution {
    
    int[] memo = new int[2001];
    public long solution(int n) {
        
        memo[1] = 1;
        memo[2] = 2;
        
        for(int i = 3; i <= n; i++) {
            memo[i] = memo[i-2] + memo[i-1]; 
            memo[i] %= 1234567;
        }
        
        return memo[n];
    }
}

