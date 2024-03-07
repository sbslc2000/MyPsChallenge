
import java.util.*;

public class Solution {
    
    int calc(int n) {
        if(n == 1 || n == 2) {
            return 1;
        } else if (n == 3) {
            return 2;
        }
        
        return calc(n / 2) + (n % (n / 2));
    }
    
    public int solution(int n) {
        return calc(n);
    }
}