
//Dynamic programming
// s = "leetcode", wordDict = ["leet", "code"]
// boolean array: [f, f, f, T, f, f, f, f]
// l -> le -> lee -> leet -> leetc -> leetco -> leetcod -> leetcode
// Time Complexity: O( n*m*k) : n is size of string, m is size of wordDict, k is size of substring 
//Space Complexity: O(n)

//2023-10-08
class Solution{
    public boolean wordBreak(String s, List<String> wordDict){
        if(s == null || s.length() <= 0 || wordDict == null || wordDict.size() <= 0){
            return false;
        }
        
        boolean[] segmented = new boolean[s.length()]; // if the string can be segemeted before 'i' index, segmented[i] is true
        //segmented[0] = true; //it means that the word can be segemeted before '0' index;
        
        for(int i = 0; i < s.length(); i++){
            for(int j = 0; j < wordDict.size(); j++){
                String word = wordDict.get(j);
                int startPos = i - word.length() + 1;
                if(startPos < 0){
                    continue;
                }
                
                if(startPos == 0 || segmented[startPos -1]){ //if substring's start position is 0 or segmented 
                    String subStr = s.substring(startPos,i+1);
                    if(subStr.equals(word)){
                        segmented[i] = true;
                        break;
                    }
                }
            }
        }
        
        return segmented[s.length()-1];
    }
}

//2023-September-07
// Time Complexity: O( n*m*k) : n is size of string, m is size of wordDict, k is size of substring 
//Space Complexity: O(n)
/*
class Solution{
    public boolean wordBreak(String s, List<String> wordDict){
        if(s == null || s.length() <= 0 || wordDict == null || wordDict.size() <= 0){
            return false;
        }

        boolean[] segmented = new boolean[s.length()]; //default value is false

        //"applepenapple", wordDict = ["apple","pen"]
        //a->ap->app->appl->apple->applep->applepe->applepen->applepena->applepenap->applepenapp->applepenappl->applepenapple
        //segmented:f,f,f,f,T,f,f,T,f,f,f,f,T
        for(int i = 0; i < s.length(); i++){
            for(String word : wordDict){
                int pos = i - word.length() + 1;
                if(pos < 0){
                    continue;
                }

                if(pos == 0 || segmented[pos-1]){ //start position, or after segmented word
                    if(s.substring(pos, i + 1).equals(word)){
                        segmented[i] = true;
                        break;
                    }
                }
            }
        }
        return segmented[s.length() -1];
    }
}
*/

//question: 1) wordDict's words have random order?  
//            ex) "leetcode" -> "code", "leet"
//        2) in case of same word, wordDict has it one time? 
//           ex) "leetcodeleet" -> ["leet", "code", "leet"] vs ["leet", "code"]
//        3) case sensitive? or S and wordDict consist of only lowercase???
//         4) wordDict has more words than substring of input s -> "leet", ["cat", "le", "leet", "dog"]
//                                
//input: "bababaa"  ["aa", "ab", "bab"] -> Output: true
//input: "leetcode" ["leet", "neet", "cake", "code"] -> output: true
//input: "leetaecode" ["leet","leetae","code"] -> output: true
//input: "bababcaa" ["aa", "ab","ba","babab", "bababcaa"] -> output: true
//2022-12-03
//Time Complexity: O(N^3)  ( for loop * for loop * substring computation)
//Space Complexity; O(N) <--- boolean array
/*
class Solution{
    public boolean wordBreak(String s, List<String> wordDict){
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0){
            return false;
        }

        //to save search time
        HashSet<String> wordSet = new HashSet<>(wordDict);
        boolean[] wordBreak = new boolean[s.length() + 1];
        wordBreak[0] = true; //start position 

        for(int i = 1; i <= s.length(); i++){ //substring's end+1
            for(int j = 0; j <i; j++) {//substring's start
                if(wordBreak[j] == true && wordSet.contains(s.substring(j,i))){
                    wordBreak[i] = true;
                    break;
                }
            }
        }
        return wordBreak[s.length()];
    }
}
*/

//2022.10.09
//Time Complexity : O(n^3) O of N cubed ( for loop * for loop * substring computation)
//Space Complexity: O(n) <-- boolean array
/*
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size()== 0){
            return false;
        }
        
        HashSet<String> wordDictSet = new HashSet<>(wordDict);
        
        boolean[] wordBreak = new boolean[s.length()+1];
        wordBreak[0] = true;
        
        for(int i = 1; i <= s.length(); i++){
            for(int j = 0; j < i; j++){
                if(wordBreak[j] == true && wordDictSet.contains(s.substring(j, i))){
                    wordBreak[i] = true;
                    break;
                }
            }
        }
        
        return wordBreak[s.length()];
    }
}
*/