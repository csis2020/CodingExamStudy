//2022-11-13
//Time Complexity: O(N)
//Space Complexty: O(1) <-- array size is fixed as 26
class Solution {
    public boolean canPermutePalindrome(String s) {
        if(s == null || s.length() == 0){
            return false;
        }

        int[] charKeys = new int[26];
        //if one of permutation of the string coulld form a palindrome, 
        // it means that the number of every character of s should be even number or only one odd number is allowed. 
        // aab : a-2, b-1
        //carerac : a-2, c-2, r-2, e-1
        //aabcccbaa : a-4, b-2, c-3

        for(int i = 0; i < s.length(); i++){
            charKeys[s.charAt(i) - 'a']++;
        }

        int count = 0;
        for(int key : charKeys){
            count +=  key % 2;
        }

        if(count <= 1){ // the number of every character is even, or there is only one odd number
            return true;
        }

        return false;
    }
}