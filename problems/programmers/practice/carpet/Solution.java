package problems.programmers.practice.carpet;

/**
 * 문제 이름 : 카펫
 * 링크 : https://school.programmers.co.kr/learn/courses/30/lessons/42842
 * 알고리즘 분류 : 수학 완전탐색
 * 작성 일시 : 2024-03-05
 * 관련 링크 :
 * 
 * 
 * width와 height간의 관계를 파악하고 전부 조사하여 풀었다.
 * width와 height의 합 = brown / 2 + 2
 * 위 값을 기준으로 width와 height가 될 수 있는 조합을 가지고 yellow와 호환되는지 확인
 */
class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer;
        
        int n = brown / 2 + 2;
  
        int i = 3;
        int width, height;
        while(true) {
            width =  n - i;
            height = i;
            // System.out.printf("width: %d height: %d", width, height);
            
            if((i - 2)*(width - 2) == yellow) {
                answer = new int[]{width, height};
                break;
            }
                
            i++;
        }
        
        return answer;
    }
}