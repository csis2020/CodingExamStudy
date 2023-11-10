


class Solution {    
    
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if(digits == null || digits.length() <= 0){
            return combinations;
        }
        
        String[] phone = new String[10];
        phone[2] = "abc";
        phone[3] = "def";
        phone[4] = "ghi";
        phone[5] = "jkl";
        phone[6] = "mno";
        phone[7] = "pqrs";
        phone[8] = "tuv";
        phone[9] = "wxyz";
        
        StringBuilder sb = new StringBuilder();
        backTracking(digits, 0, phone, sb, combinations);
        
        return combinations;
        
    }
    
    void backTracking(String digits, int index, String[] phone, StringBuilder letters, List<String> combinations){
        if(index >= digits.length()){
            combinations.add(letters.toString());
            return;
        }
        
        char digit = digits.charAt(index);
        String button = phone[digit - '0'];

        for(int i = 0; i < button.length(); i++){
            char ch = button.charAt(i);
            letters.append(ch);
            backTracking(digits, index+1, phone, letters, combinations);
            letters.deleteCharAt(letters.length()-1);
        }

    }
}



//2022-09-25
//limitation : digits is 1 or 0, return [], digits is null, return [] ??

// Input: digits = "2" -> Output: ["a","b","c"]
// Input: digits = "23" -> Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
// Input: digits = "" -> Output: []

// 아래는 내생각임. 
// Time Complexity: O(4^n) <- n is the length of input String
// Space Complexity : O(2n) => O(n) <- length of char[] is n, recursive call max stack is n 
// leetcode 는 char[] 가 아니라 stringbuilder 를 사용했고, TimeComplexity 가 O(4^n * n)
// Space complexity 는 똑같이 O(n)
/*
class Solution {
    
    List<String> allCombi = new ArrayList<String>();
    
    public List<String> letterCombinations(String digits) {
        
        if(digits == null || digits.length() == 0){
            return allCombi; 
        }
        
        String[] phonePad = createPhonePad();
        
        char[] combination = new char[digits.length()];
        
        findCombination(phonePad, digits, 0, combination);
        
        return allCombi;
    }
    
    private String[] createPhonePad(){
        String[] phonePad =  new String[10];
        
        phonePad[2] = "abc";
        phonePad[3] = "def";
        phonePad[4] = "ghi";
        phonePad[5] = "jkl";
        phonePad[6] = "mno";
        phonePad[7] = "pqrs";
        phonePad[8] = "tuv";
        phonePad[9] = "wxyz";
        
        return phonePad;
    }
    
    private void findCombination(String[] phonePad, String digits, int position, char[] combination){
            
        if(position >= digits.length()){
            String result = new String(combination);
            allCombi.add(result);
            return;
        }
        
        char ch = digits.charAt(position);
        if(!Character.isDigit(ch) || ch == '0' || ch == '1'){
            //there is no letter.
            return;
        }
        
        //get the letters for each number
        String letters = phonePad[ch-'0'];
        
        //find possible leter combination recursively
        for(int i = 0; i < letters.length(); i++){
            combination[position] = letters.charAt(i);
            findCombination(phonePad, digits, position+1, combination);
        }
        
        
    }
}
*/