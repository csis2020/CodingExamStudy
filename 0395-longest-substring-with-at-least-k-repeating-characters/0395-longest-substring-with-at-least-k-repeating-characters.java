
//2023-09-26
//Solution: sliding window - 그런데 unique한 alpahbet 의 개수를 fix 한 상태에서 풀수 있는 솔루션이라서 
// 허용가능한 unique한 alpahbet 이 1개일때부터 max 개까지 for 문 돌면서 매번 sliding window 방법적용
//
//Time Complexity: O(N) , 정확하게는 O( N * maxUnique)인데 maxUnique 는 최대경우가 26으로 fix 된 값으로 생략가능
//Space Complexity: O(1) 알파벳 체크하는 사이즈 26으로 fix 된 배열만 잡고 쓰므로.
/*
class Solution {
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() <= 0) {
            return 0;
        }
        
        int maxUnique = numberOfUniqueChar(s);
        int[] repeating = new int[26];
        int longest = 0;      
        //"aaabb", k:3 
        //numUnique:1
        //start:0->1
        //end:0->1->2->3
        //countUnique:0->1->2
        //countKOrMore:0->1->0
        //repeating:a[3]b[1]
        //longest: 0->1
        for(int numUnique = 1; numUnique <= maxUnique; numUnique++){
            Arrays.fill(repeating, 0);
            
            int start = 0;
            int end = 0;
            int countUnique = 0;
            int countKOrMore = 0;
            int idx = 0;
            while(end < s.length()){
                if(countUnique <= numUnique){
                    idx = s.charAt(end) - 'a';
                    if(repeating[idx] == 0){
                        countUnique++;
                    }
                    repeating[idx]++;
                    if(repeating[idx] == k){
                        countKOrMore++;
                    } 
                    end++;
                }else{
                    idx = s.charAt(start) - 'a';
                    if(repeating[idx] == k){
                        countKOrMore--;
                    }
                    repeating[idx]--;
                    if(repeating[idx] == 0){
                        countUnique--;
                    }
                    start++;
                }
                if(countUnique == numUnique && countKOrMore == numUnique){
                    longest = Math.max(longest, end - start);
                }
            }
        }
        return longest;
    }
    
    int numberOfUniqueChar(String s){
        Set<Character> charSet = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(!charSet.contains(ch)){
                charSet.add(ch);
            }
        }
        
        return charSet.size();
    }
}
*/

//Solution : Divide & Conquer - recursive 
/*
class Solution {
    public int longestSubstring(String s, int k) {
        if(s == null || s.length() <= 0 || k <= 0){
            return 0;
        }
        
        return recursiveLST(s, k);
    }
    
    //Input: s = "aaabb", k = 3
    //countMap: [a:3][b:2]-> [a:3]
    //(start,end) : (0,4)-> aaa(0,2) (4,4)
    //
    //
    int recursiveLST(String s, int k){
        
        if(s == null || s.length() < k){
            return 0;
        }
        
        char [] c = s.toCharArray();
        
        int[] countMap = new int[26];
        for(int i = 0; i < c.length; i++){
            //char ch = s.charAt(i);
            countMap[c[i] - 'a']++;
        }
        
        for(int i = 0; i < c.length; i++){
            //char ch = s.charAt(i);
            if(countMap[c[i] - 'a'] < k && countMap[c[i] -'a'] > 0){

                int leftLongest = recursiveLST(s.substring(0, i), k);
                int rightLongest = recursiveLST(s.substring(i+1,c.length), k);
                
                return Math.max(leftLongest, rightLongest);
            }
        }
        return s.length();
    }
}
*/
/*
 class Solution {
    public int longestSubstring(String s, int k) {
      int [] alpha = new int[26];
      char [] c = s.toCharArray();
      
      for(int i=0; i<c.length; i++){
        int index = c[i]-'a';
        alpha[index]++;
      }
      
      boolean vaild = true;
      int max = 0;
      int start = 0;
      for(int end =0; end<c.length; end++){
        int index = c[end]-'a';

        if(alpha[index]<k && alpha[index]>0){
          String str = s.substring(start,end);
          max = Math.max(max,longestSubstring(str,k));
          start = end+1;
          vaild = false;
        }
        
      }
      if(vaild){
          return s.length();
        }
        else{
          return Math.max(max, longestSubstring(s.substring(start),k));
        }
      
    }
  }
  */
class Solution {
    public int longestSubstring(String s, int k) {
        int[] freq = new int[26];
        int res = 0, start = 0;
        
        for(int i = 0; i < s.length(); ++i) ++freq[s.charAt(i) - 'a'];
        
        boolean valid = true;
            
        for(int end = 0; end < s.length(); ++end){
            if(freq[s.charAt(end) - 'a'] < k){
                res = Math.max(res, longestSubstring(s.substring(start, end), k));
                valid = false;
                start = end + 1;
            }
        }
        
        return valid? s.length() : Math.max(res, longestSubstring(s.substring(start), k));
    }
}

//brute force -이 방법도 submit 하면 Accepted 됨. 
//idea : for 문 2번 돌면서 [i]부터[j]까지 범위에 있는 character 들이 같은alpahbet 이 k개 이상인지 check
//Time Complexity: O(N^2) , 같은 알파벳이 k개 이상인지 체크하는것은 늘 26개 체크해서 계산에서 제외
//Space Complexity: O(1) , 알파벳 체크하는 사이즈 26으로 fix 된 배열만 잡고 쓰므로. 
/*
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
*/