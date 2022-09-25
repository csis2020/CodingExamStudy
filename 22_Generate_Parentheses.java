//2022.09.25
//limitation: if n is 0, return null??
//Input: n = 3  -> Output: ["((()))","(()())","(())()","()(())","()()()"]
//Input: n = 1  -> Output: ["()"]
//아래는 내가 생각한 time complexty / Space complexity 임. 
// Time Complexity: O(2^(2n-1)), space Complexity:O(4n) <-- call stack depth + size of char[] 
// leetcode 설명은 다름. (복잡함. @.@)
// TimeComplexity: Time Complexity : O(4^n / sqrt(n)) , Space Complexity: O(4^n / sqrt(n))
class Solution {
    
    List<String> allCombinations = new ArrayList<String>();
    
    public List<String> generateParenthesis(int n) {
        if(n <= 0){
            return allCombinations;
        }
        
        int open = n;
        int close = n;
        
        char[] parenthesis = new char[n + n];
        int position = 0;
        recursiveCheck(open, close, parenthesis, position);
        
        return allCombinations;
        
    }
    
    private void recursiveCheck(int open, int close, char[] parenthesis, int position){
        
        if((open + close) <= 0 || position >= parenthesis.length){
            
            String str = new String(parenthesis);
            allCombinations.add(str);
            return;
        }
        if(open < close){ 
            //It is possible to start with both '('and ')'
            if(open > 0){
                parenthesis[position] = '(';
                recursiveCheck(open-1, close, parenthesis, position+1);            
            }
            
            parenthesis[position] = ')';
            recursiveCheck(open, close-1, parenthesis, position+1);

        }else{//In this case, should start with '('
            parenthesis[position] = '(';
            recursiveCheck(open-1, close, parenthesis, position+1);
        }
    }
}