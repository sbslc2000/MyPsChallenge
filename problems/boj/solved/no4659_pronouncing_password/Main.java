package problems.boj.solved.no4659_pronouncing_password;


import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 문제 이름 : 비밀번호 발음하기
 * 링크 : https://www.acmicpc.net/problem/4659
 * 알고리즘 분류 : ?
 * 작성 일시 : 2023-11-22
 * 관련 링크 :
 * 
 * 메모리 제한 :  128 MB
 * 시간 제한 : 1초
 * 
 * 문자열을 한 번 스캔하면서 품질을 평가할 수 있을 것 같다.
 * 
 */
public class Main {

    private static String password;
    private static char[] vowels = new char[]{'a','e','i','o','u'};


    public static void main(String[] args) throws Exception {
        //var
        int i;
        char cur;

        boolean hasVowel = false;
        int beforeVowelCount = 0;
        int beforeConsonantCount = 0;
        char beforeChar = '0';

        boolean acceptableFlag = true;
 
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        //logic
        while(true) {
            password = br.readLine();
            
            if(password.equals("end")) {
                break;
            }

            for(i = 0; i<password.length(); i++) {
                cur = password.charAt(i);

                //연속한 두 문자
                if((beforeChar == cur) && (cur != 'e') && (cur != 'o')) {
                    acceptableFlag = false;
                    break;
                }

                //모음 자음 개수 새기
                if(isVowel(cur)) {
                    hasVowel = true;
                    beforeVowelCount++;
                    beforeConsonantCount = 0;
                } else {
                    beforeConsonantCount++;
                    beforeVowelCount = 0;
                }

                if(beforeVowelCount == 3 || beforeConsonantCount == 3) {
                    acceptableFlag = false;
                    break;
                }

                beforeChar = cur;
            }

            if(!hasVowel) {
                acceptableFlag = false;
            }

            
            System.out.printf("<%s> is %sacceptable.\n",
            password, acceptableFlag ? "" : "not ");

            //초기화
            hasVowel = false;
            beforeVowelCount = 0;
            beforeConsonantCount = 0;
            beforeChar = '0';
            acceptableFlag = true;
        
        }// end of while
    }

    private static boolean isVowel(char c) {
        for(int i = 0; i< 5; i++) {
            if(vowels[i] == c)
                return true;
        }

        return false;
    }
}