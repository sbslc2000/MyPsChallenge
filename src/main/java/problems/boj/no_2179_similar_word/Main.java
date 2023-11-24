package problems.boj.no_2179_similar_word;

import java.io.*;
import java.util.*;

/**
 * 문제 이름 : 비슷한 단어
 * 링크 : https://www.acmicpc.net/problem/2179
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-24
 * 관련 링크 : 
 * 
 * 메모리 제한 :  128 MB
 * 시간 제한 : 2초
 * 
 */
public class Main {

    static int INFI = 20001;
    
    public static class Word implements Comparable<Word> {
        int idx;
        String content;
        int length;

        static Word of(int idx, String content) {
            Word word = new Word();
            word.idx = idx;
            word.content = content;
            word.length = content.length();
            return word;
        }

        @Override
        public int compareTo(Word w) {
            return content.compareTo(w.content);
        }
    }

    static int N;
    static Word[] words;
    static String similar = "";
    static Word r1 = Word.of(INFI, "");
    static Word r2 = Word.of(INFI, "");
    static int maxLength = -1; //지금까지 발견한 비슷단어의 접두어 길이

    public static void main(String[] args) throws Exception{
        //var
        int i, j;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     
        //input
        N = Integer.parseInt(br.readLine());
        words = new Word[N];

        for(i = 0; i < N; i++) {
            words[i] = Word.of(i, br.readLine());
        }

        //logic
        Arrays.sort(words);

        print();

        for(i = 0; i < N - 1; i++) {
            // printOne(i); printOne(i+1);
            //둘 중 하나라도 maxLength보다 작다면 검색 대상이 아님
            //maxLength 초기값은 -1 
            if(words[i].length < maxLength && words[i+1].length < maxLength) {
                System.out.println("이 경우는 고려하지 않음");
                continue;
            }

            //새로운 대상이 되려면 적어도 maxLength 위치에는 문자가 같아야댐
            //0부터 검사해도 되지만, 최적화를 위해서는 maxLength쪽 한번 검사하는게 낫지 않나
            //Numerical 치환 비교법?
            //일단 for로 가자 시간초과뜨면 그때 생각

            int count = 0;
            int min = words[i].length < words[i+1].length ? words[i].length : words[i+1].length;
            for(j = 0; j < min; j++) {
                if(words[i].content.charAt(j) != words[i+1].content.charAt(j)) {
                    break;
                }
                count++;
            }

            System.out.printf("count: %d min: %d\n", count, min);


            //만약 count가 similar의 접두어 길이보다 작다면 out
            //이게 계속 반복되는 것이 가능한가? 불가능할듯
            //maxLength는 초기값이 -1이라서, 최초 케이스에서 여기가 실행되는 것은 불가능
            //최초 케이스는 else 문으로 넘어감
            if(count < maxLength) {
                System.out.println("count < maxLength, continue");
                continue;
            } else if (count == maxLength) { //만약 count와 maxLength 동일하다면 
                System.out.println("count == maxLength");
                //만약 

                // similarWord와 동일한지 아닌지 판단해야 하는가?
                // similarWord와 동일성을 판단 안하는 경우 ->
                    // count 와 maxLength만을 비교
                    //  같다면, 기존 셋과 현재 셋 중 선택?
                    // 이 경우 aaa aab aac 의 경우 aaa와 aab가 유지되어야 함
                    // 무슨 근거로? aab와 aac의 similar이 aa와 동일할 때의 로직을 따로 구성해야하지 않나? 

                //similarWord와 동일한 경우
                //이 경우 전자는 r1, r2에 이미 포함되있음
                //r2보다 idx가 늦다면 현상 유지
                //아니라면 r2를 퇴출하고 r1과 r2를 재배치
                if(words[i+1].content.substring(0,count).equals(similar)) {
                    System.out.println("Same with similar");
            
                    if(words[i+1].idx < r2.idx) {  //r2보다 새로운 단어의 idx가 더 작다면
                        if(words[i+1].idx < r1.idx) { //심지어는 그게 r1보다 작다면
                            r2 = r1;  //r2에 r1을 넣고
                            r1 = words[i+1]; //r1에 새로운 단어 넣기
                        } else {
                            r2 = words[i+1]; //r1보다는 크다면 r2에만 새로운 단어 넣기
                        }

                        //이 경우 similar을 업데이트 해줘야하나? 아님
                    }

                 } else { //similar 문자와 다르다면 . . .
                        System.out.println("Different with similar");       
                        int minIdx, maxIdx;

                        if(words[i].idx < words[i+1].idx) { //이번 비교대상인 words 중 idx가 더 작은 words를 찾자
                            minIdx = i; maxIdx = i+1;
                        } else {
                            minIdx = i+1; maxIdx = i;
                        }
                
                        //이거 타당한 비교인가??
                        if(words[minIdx].idx < r1.idx) { //현재 similar이 먼저 출현했냐, 아님 지금 words가 먼저 출현했냐
                            //지금 비교중인 words가 더 일찍 출현한다면
                            r1 = words[minIdx];
                            r2 = words[maxIdx];

                            //이건 i든 i+1이든 상관없다.

                            //r이 업데이트되니 similar을 바꿔준다.
                            //maxLength는 똑같으니 설정해줄 필요 업다
                            similar = words[i+1].content.substring(0,count);
                        } //else 현상유지

                    }
            } else { //count가 similar보다 크다면 words[i]와 words[i+1]을 재배치
                 System.out.println("count > maxLength, new similar");
                if(words[i].idx < words[i+1].idx) {
                    r1 = words[i];
                    r2 = words[i+1];
                } else {
                    r1 = words[i+1];
                    r2 = words[i];
                }

                similar = words[i+1].content.substring(0,count);
                maxLength = similar.length();
            }
        }

        //output
        System.out.println("====result====");
        System.out.println(r1.content);
        System.out.println(r2.content);
    }

    static void print() {
        for(int i = 0; i < N; i++) {
            System.out.println(words[i].content + " " + words[i].idx);
        }
    }

    static void printOne(int i) {
        System.out.println(words[i].content + " " + words[i].idx);
    }
}
