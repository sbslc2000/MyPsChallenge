package problems.boj.unsolved.no_2784_x_y_puzzle;

import java.io.*;
import java.util.*;
import java.util.stream.*;

/**
 * 문제 이름 : 가로 세로 퍼즐
 * 링크 : https://www.acmicpc.net/problem/2784
 * 알고리즘 분류 : ?
 * 작성 일시 : 2024-04-12
 * 관련 링크 :
 * 시간 제한 : 1초
 * 메모리 제한 : 128M
 */
public class Main {

    static int N = 6;
    static String[] words = new String[N];
    static List<Integer> selected = new LinkedList<>();
    static boolean found = false;
    static List<String> result;


    public static void main(String[] args) throws Exception {
        //input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        //logic
        select();

        //output
        // System.out.println("Output: ");
        if(!found) {
            System.out.println(0);
        } else {
            for(String r : result) {
                System.out.println(r);
            }
        }
    }

        private static void select() {

            // System.out.println("selected : "+ selected);
            if(selected.size() == 3) {
                validate();
                return;
            }

            for(int i = 0; i < N; i++) {

                if(!selected.contains(i)) {
                    selected.add(i);
                    select();
                    selected.remove(selected.size() - 1);
                } else {
                    continue;
                }
            }
        }

    private static void validate() {
        List<String> selected = getSelectedWords();
        // System.out.println("selected   : " + selected);
        Set<String> unselected = findUnselectedWords();
        // System.out.println("unselected : " + unselected);
        Set<String> derived = getDerivedWords(selected);
        // System.out.println("derived    : " + derived);
        
        if(!unselected.equals(derived)) {
            return;
        }
        // for(String s : unselected) {
        //     if()
        //     if(!derived.contains(s)) {
        //         derived.remove(s);
        //         return;
        //     }
        // }

        if(result == null) {
            result = selected;
        } else {
            String joinedResult = "";
            for(String s : result) {
                joinedResult += s;
            }

            String joinedSelected = "";
            for(String s : selected) {
                joinedSelected += s;
            }

            if(joinedSelected.compareTo(joinedResult) < 0) {
                result = selected;
            }
        }
        
        found = true;
    }

    private static List<String> getSelectedWords() {
        List<String> res = new LinkedList<>();
        for(Integer i : selected) {
            res.add(words[i]);
        }
        return res;
    }

    private static Set<String> findUnselectedWords() {        
        Set<String> res = new HashSet<>();

        for(int i = 0; i < N; i++) {
            if(!selected.contains(i)) {
                res.add(words[i]);
            }
        }

        return res;
    }

    private static Set<String> getDerivedWords(List<String> selected) {
        Set<String> res = new HashSet<>();
        for(int i = 0; i < 3; i++) {
            String tmp = "";
            for(String s : selected) {
                tmp += s.charAt(i);
            }

            res.add(tmp);
        }

        return res;
    }    
    
}
