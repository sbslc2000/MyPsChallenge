package problems.programmers.practice.process;

import java.util.*;

class Solution {
    
    private static class Process {
        int pid;
        int priority;
        
        public Process(int pid, int priority) {
            this.pid = pid;
            this.priority = priority;
        }
    }
    
    private static class MyPriorityQueue {
        
        private Queue<Process> q = new LinkedList<>();
        private int[] remains = new int[10];
        private int p;
        
        public MyPriorityQueue() {}
        
        public void add(Process p) {
            q.add(p);
            remains[p.priority]++;
            setCurrentHighestPriority();
        }
        
        private void setCurrentHighestPriority() {
            for(int i = 9; i > 0; i--) {
                if(remains[i] > 0) {
                    p = i;
                    break;
                }
            }
        }
        
        private int getCurrentHighestPriority() {
            return p;
        }
        
        public Process poll() {
            while(true) {
                Process process = q.poll();
                if(process.priority < getCurrentHighestPriority()) {
                    q.add(process);
                } else {
                    remains[process.priority]--;
                    setCurrentHighestPriority();
                    return process;
                }
            }
        }
    }
    
    public int solution(int[] priorities, int location) {
        
        MyPriorityQueue q = new MyPriorityQueue();

        for(int i = 0; i < priorities.length; i++) {
            q.add(new Process(i, priorities[i]));
        }
        
        int count = 0;
        while(true) {
            count++;
            Process p = q.poll();
            if(p.pid == location) {
                break;
            }
        }
        
        return count;
    }
}