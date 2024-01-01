package problems.boj.solved.no1244_switch_turn_on_and_off;

import java.util.*;
import java.io.*;

/**
 * 문제 이름 : 스위치 켜고 끄기
 * 링크 : https://www.acmicpc.net/problem/1244
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-01-01
 * 관련 링크 :
 */
public class Main {

    static int SWITCH_NUM;
    static boolean[] switches;
    static int STUDENT_NUM;

    static Gender[] GENDERS;
    static int[] TARGETS;

    static enum Gender {
        MALE, FEMALE
    }
    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String splited[];

        SWITCH_NUM = Integer.parseInt(br.readLine());
        switches = new boolean[SWITCH_NUM + 1];

        splited = br.readLine().split(" ");
        for(int i = 1; i <= SWITCH_NUM; i++) {
            switches[i] = splited[i-1].equals("1");
        } 

        STUDENT_NUM = Integer.parseInt(br.readLine());
        GENDERS = new Gender[STUDENT_NUM];
        TARGETS = new int[STUDENT_NUM];

        for(int i = 0; i < STUDENT_NUM; i++) {
            splited = br.readLine().split(" ");

            GENDERS[i] = splited[0].equals("1") ? Gender.MALE : Gender.FEMALE;
            TARGETS[i] = Integer.parseInt(splited[1]);
        }

        //logic
        int target;
        Gender gender;
        for(int i = 0; i < STUDENT_NUM; i++) {
            target = TARGETS[i];
            gender = GENDERS[i];

            if(gender == Gender.MALE) {
                int p = target;
                while(p <= SWITCH_NUM) {
                    switches[p] = !switches[p];
                    p += target;
                }
            } else { //FEMALE
                switches[target] = !switches[target];
                int p = 1;
                while (
                    (target - p) >= 1 && (target + p) <= SWITCH_NUM &&
                    switches[target - p] == switches[target + p]
                ) {
                    switches[target - p] = !switches[target - p];
                    switches[target + p] = !switches[target + p];
                    
                    p++;
                }
            }
        }

        //output
        for (int i = 1; i <= SWITCH_NUM ; i++) {
            System.out.printf("%d ", switches[i] ? 1 : 0);

            if(i % 20 == 0) {
                System.out.println();
            }
        }

    }
}
