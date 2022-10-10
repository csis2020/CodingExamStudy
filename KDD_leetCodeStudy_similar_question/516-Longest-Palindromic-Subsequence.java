
//2022.10.09
//Limitation: if s is null, return 0  ?
//Time complexity: O(n^2) <- n is length of input string, 
//Space Complexity: O(n^2) <-- dp array
class Solution {
    public int longestPalindromeSubseq(String s) {
        
        if( s == null || s.length() == 0){
            return 0;
        }
        
        int length = s.length();
        //by default, value set zero
        int[][] subsequence = new int[length+1][length+1];
        
        //compare String s and s' reverse string. ex) s: "rest" , r(s) : "tesr" 
        for(int i = length-1; i >= 0; i--){
            for(int j = length-1; j >= 0; j--){
                // i :from String 's',   length -j - l : from reverse string (s)
                if(s.charAt(i) == s.charAt(length -j - 1)){ 
                    subsequence[i][j] = subsequence[i+1][j+1] +1;
                }else{
                    subsequence[i][j] = Math.max(subsequence[i+1][j] , subsequence[i][j+1]);
                }
                
                //System.out.println("["+i+"]["+j+"]:"+subsequence[i][j]);
            }
        }
        
        return subsequence[0][0];
    }
}