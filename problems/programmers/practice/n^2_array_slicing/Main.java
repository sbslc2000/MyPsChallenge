import java.util.stream.*;

/**
 * 문제 이름 : 멀리 뛰기
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/87390
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-02
 * 관련 링크 : 
 */
class Solution {
    public int[] solution(int n, long left, long right) {
        return LongStream.rangeClosed(left,right)
            .mapToInt(i -> (int)Math.max(i / n, i % n) + 1)
            .toArray();
    }
}