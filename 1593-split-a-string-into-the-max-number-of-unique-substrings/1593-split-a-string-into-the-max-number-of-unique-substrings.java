
//2023-11-08
//Backtracking with HashSet <-- 모든경우 다 track 해봐야 함.  (s= "wwwzfvedwfvhsww" 와 같은경우 위해)
//example: s= "ababccc", output:5
//      s= "wwwzfvedwfvhsww", output:11  의 경우에 w/ww 로 나누기 시작하면 10(wrong answer)나옴
//          www/z/ .. 이런식으로 나누어야 11이 나옴. 
//      s="aa", output:1
//Time Complexity: O(N) ???
//Space Complexity: O(N) , the worst case 는 string length 만큼 recursive call stack 
class Solution {
    
    public int maxUniqueSplit(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        
        HashSet<String> uniqueSet = new HashSet<>();
        
        return backTracking(s, 0,uniqueSet);
    }
    
    // s= wwwzfvedwfvhsww  (이경우는 [www,z,f,v,e,d,w,fv,h,s,ww] =11개로 max)
    //bt(0,[])->b(1,[w]) ->bt(3,[w,ww])->bt(4,[w,ww,z])->bt(5,[w,ww,z,f])->bt(6,[w,ww,z,f,v])...
    //end:1      end:2->3     end:4,       end:5             end:6
    //uniqueSet:[w,] <-- 이건 10개로 max 의 경우가 아님 
    int backTracking(String s, int start, HashSet uniqueSet){
        
        if(start >= s.length()){ //String 끝을 넘어감
            return 0;
        }
        
        int maxCount = 0;
        for(int end = start+1; end <= s.length(); end++){
            String subString = s.substring(start, end);
            if(!uniqueSet.contains(subString)){
                
                uniqueSet.add(subString);
                int count = backTracking(s, end, uniqueSet) + 1;
                maxCount = Math.max(maxCount, count);
                uniqueSet.remove(subString);
            }
        }
        return maxCount;
    }

}