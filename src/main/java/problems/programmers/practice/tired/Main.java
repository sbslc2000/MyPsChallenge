package problems.programmers.practice.tired;


import java.util.*;
class Solution {
    
    private List<Integer> selected = new LinkedList<>();
    private int max = Integer.MIN_VALUE;
    
    public int solution(int k, int[][] dungeons) {
        bt(dungeons, k, 0);
        return max;
    }
    
    void bt(int[][] dungeons, int k, int count) {
        
        if(selected.size() == dungeons.length) {
            max = Math.max(max, count);
        }
        
        for(int i = 0; i < dungeons.length; i++) {
            if(!selected.contains(i)) {
                boolean f = false;
                
                selected.add(i);
                if(dungeons[i][0] <= k) {
                    f = true;
                    k -= dungeons[i][1];
                    count++;
                }
                
                bt(dungeons, k, count);
                
                selected.remove(selected.size() - 1);
                if(f) {
                    k += dungeons[i][1];
                    count--;
                }
            }
        }
    }
}