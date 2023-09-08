
//2023.September.7
//Time Complexity: O(m*n) , m is text1.length, n is text2.length
//Space Complexity: O(m*n)

class Solution{
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text1.length() <= 0 || text2 == null || text2.length() <= 0){
            return 0;
        }

        int[][] common = new int[text1.length()+1][text2.length()+1]; //default is '0'
        //  a  b  c
        //a 1  1  1  
        //b 1  2  2
        //c 1  2  3  
        for(int i = 0; i < text1.length(); i++){
            for(int j = 0; j < text2.length(); j++){
                if(text1.charAt(i) == text2.charAt(j)){
                    common[i+1][j+1] = common[i][j] +1;
                }else{
                    common[i+1][j+1] = Math.max(common[i][j+1], common[i+1][j]);
                }
            }
        }

        return common[text1.length()][text2.length()];
    }
}




























//limitation : if text1 or text2 is null, return 0
//Time complexity: O( m*n) <- m is text1.length(), n is text2.length()
//Space Complexit : O( m*n)  <-- checkMatrix size
//2022-12-03
/*
class Solution{
    public int longestCommonSubsequence(String text1, String text2) {
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
            return 0;
        }
        int length1 = text1.length();
        int length2 = text2.length();

        //default value is 0
        int[][] checkMatrix = new int[length1 + 1][length2 + 1];

        for(int i = length1 -1 ; i >= 0; i--){
            for(int j = length2 -1; j >= 0; j--){
                if(text1.charAt(i) == text2.charAt(j)){
                    checkMatrix[i][j] = checkMatrix[i+1][j+1] +1;
                }else{ //differect character
                    checkMatrix[i][j] = Math.max(checkMatrix[i+1][j], checkMatrix[i][j+1]);
                }
            }
        }
        return checkMatrix[0][0];
    }
}
*/
//2022.10.07
//Time complexity: O( m*n) <- m is text1.length(), n is text2.length()
//Space Complexit : O( m*n)  <-- checkMatrix size
/*
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
     
        if(text1 == null || text1.length() == 0 || text2 == null || text2.length() == 0){
            return 0;
        }
        
        int length1 = text1.length();
        int length2 = text2.length();
            
        int[][] checkMatrix = new int[length1 + 1][length2 + 1];
        
        for(int i = length1-1 ; i >= 0; i--){
            for(int j = length2-1 ; j >= 0; j--){
                if(text1.charAt(i) == text2.charAt(j)){ 
                    checkMatrix[i][j] = checkMatrix[i+1][j+1] + 1;
                }else{
                    checkMatrix[i][j] = Math.max(checkMatrix[i+1][j], checkMatrix[i][j+1]);
                }
            }
        }
          
        return checkMatrix[0][0];
    }
}
*/

//leetcode solution 
//Time complexity: O( m*n) <- m is text1.length(), n is text2.length()
//Space Complexit : O( m*n)  <-- checkMatrix size
/*
class Solution {
    
  public int longestCommonSubsequence(String text1, String text2) {    
    
    // Make a grid of 0's with text2.length() + 1 columns 
    // and text1.length() + 1 rows.
    int[][] dpGrid = new int[text1.length() + 1][text2.length() + 1];
        
    // Iterate up each column, starting from the last one.
    for (int col = text2.length() - 1; col >= 0; col--) {
      for (int row = text1.length() - 1; row >= 0; row--) {
        // If the corresponding characters for this cell are the same...
        if (text1.charAt(row) == text2.charAt(col)) {
          dpGrid[row][col] = 1 + dpGrid[row + 1][col + 1];
        // Otherwise they must be different...
        } else {
          dpGrid[row][col] = Math.max(dpGrid[row + 1][col], dpGrid[row][col + 1]);
        }
      }
    }
        
    // The original problem's answer is in dp_grid[0][0]. Return it.
    return dpGrid[0][0];
  }
}
*/