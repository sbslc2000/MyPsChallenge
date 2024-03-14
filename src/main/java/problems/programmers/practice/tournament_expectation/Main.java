package problems.programmers.practice.tournament_expectation;

/**
 * 문제 이름 : 예상 대진표
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/12985#
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-03-14
 * 관련 링크 :
 */
class Solution
{
    public int solution(int n, int a, int b) {
        
        //계산 복잡도를 줄이기 위한 조정
        a--;
        b--;
        
        int count = 1;
    
        while(true) {
            
            a /= 2;
            b /= 2;
            
            if(a == b) {
                break;
            }
            
            count++;
        }

        return count;
    }
}