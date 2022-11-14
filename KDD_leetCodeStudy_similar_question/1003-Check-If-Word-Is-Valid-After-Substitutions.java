//2022-11-13
//use stack ( similar question of parenthesis)
//Time Complexity: O(N) <-- N is the length of s
//Space Complexity: O(N)
class Solution{
    public boolean isValid(String s){
        if(s == null || s.length() == 0){
            return false;
        }

        if(s.length() % 3 != 0){
            return false;
        }

        Stack<Character> charStack = new Stack<Character>();
        charStack.push(s.charAt(0));
        for(int i = 1; i < s.length();i++){
            char ch = s.charAt(i);
            if(ch == 'c'){
                if(charStack.isEmpty() || charStack.pop() != 'b' ){
                    return false;
                }
                if(charStack.isEmpty() || charStack.pop() != 'a'){
                    return false;
                }              
            }else{
                charStack.push(ch);
            }
        }

        if(charStack.isEmpty()){
            return true;
        }
        return false;
    }
}
//2022-11-13
//brute-force : keep removing "abc" substring
// Input : aabcbc <--a[abc]bc <-- [abc] : true
// Input : abcabcababcc <-- [abc]abcababcc <-- [abc]ababcc <--ab[abc]c <-- [abc]

//Time Complexity: O(N^2) <- N은 string 길이
//Space Complexity: O(N) <- N은 string 길이
// Time Complexity: 매번 abc 를 찾으려면 (string size - 3) 개수만큼 찾아야함 
//  = O(N -3) + O(N-6) + O(N-(N-9)) + O(N-(N-6)) + O(N-(N-3)) = O(N^2)
//  => (1 + 2 + 3 + ... + (N-3) + (N-2) + (N-1) +N) = (N+1)*N/2
//  => (3 +6 + 9 + ... + (N-9) + (N-3)) = 대충 (N+1)*N/2 /3 = (N+1)*N /6 
/*
class Solution {
    public boolean isValid(String s) {

        if(s == null || s.length() == 0){
            return false;
        }

        if(s.length() % 3 != 0){
            return false;
        }

        boolean isExisted = false;

        while(s.length() >= 3){
            isExisted = false;
            for(int i = 0; i <= (s.length()-3); i++){
                String subStr = s.substring(i, i+3);
                if(subStr.equals("abc")){
                    String preStr = s.substring(0, i);
                    String postStr = "";
                    if(i+3 < s.length()){
                        postStr = s.substring(i+3);
                    }
                    s = preStr + postStr;
                    isExisted = true;
                    break;
                }
            }
            if(!isExisted){
                return false;
            }
        }
        if(s.length() > 0){
            return false;
        }

        return true;
    }
}
*/