//idea : sliding window + int[26] array 

//2023-09-29
//Time Complexity: O(N)
//Space Complexity: O(1) , size 가 26으로 fix 된 배열 사용 
class Solution {
    public int partitionString(String s) {
        if(s == null || s.length() <= 0){
            return 0;
        }
        
        int[] charPosition = new int[26];
        Arrays.fill(charPosition, -1);
        int startSubstring = -1;
        int numOfSubstrings = 0;
        //Input: s = "a->b, a->c, a->b, a"
        //charPosition: [a:5][b:5][c:3]
        //startSubstring:5
        //numOfSubstrings:4
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(charPosition[ch-'a'] >= startSubstring){
                startSubstring = i;
                numOfSubstrings++;
            }
            charPosition[ch-'a'] = i;            
        }
        return numOfSubstrings;
    }
}

//leetcode 답 
/*
class Solution {
    public int partitionString(String s) {
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);
        int count = 1, substringStart = 0;

        for (int i = 0; i < s.length(); i++) {
            if (lastSeen[s.charAt(i) - 'a'] >= substringStart) {
                count++;
                substringStart = i;
            }
            lastSeen[s.charAt(i) - 'a'] = i;
        }

        return count;
    }
}
*/
