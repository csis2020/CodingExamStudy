
//2023-09-26
//Idea : sliding window - HashMap 


//brute force
class Solution {
    public int longestSubstring(String s, int k) {
        
        if(s == null || s.length() <= 0){
            return 0;
        }
        
        int longest = 0;

        //Input: s = "aaabb", k = 3

        //end:a, a, a
        //lognest:3
        for(int start = 0; start < s.length(); start++){
            int[] repeat = new int[26];
            for(int end = start; end < s.length(); end++){
                char ch = s.charAt(end);
                repeat[ch - 'a']++;
                if(kRepeating(repeat, k)){
                    longest = Math.max(longest, end - start +1);
                }
            }
        }
        return longest;
    }
    
    boolean kRepeating(int[] repeating, int k){
        for(int i = 0; i < repeating.length; i++){
            if(repeating[i] > 0 && repeating[i] < k){
                return false;
            }
        }
        return true;
    }
}