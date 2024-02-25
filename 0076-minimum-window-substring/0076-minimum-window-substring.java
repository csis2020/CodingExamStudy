
//2024-02-23
//2024-02-23
//Idea: HashMap 2개 (HashMap<Character, set에 주어진 회수>, HashMap<Character, s에 나타난 횟수)+ Sliding Window
//Time Complexity : O(S의 길이 + t의 길이) <--- 왜 t 의 길이가 더해지지???
//Space Complexity: O(S의 길이 + t의 길이)

class Solution {
    public String minWindow(String s, String t) {
     
        if(s == null || s.length() <= 0 || t == null || t.length() <= 0 || s.length() < t.length() ){
            return "";
        }
        
        HashMap<Character, Integer> dictMap = new HashMap<>(); //<character, frequency>
        HashMap<Character, Integer> currMap = new HashMap<>(); //<Character, current frrequency>
        
        //set dictionary
        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            dictMap.put(ch, dictMap.getOrDefault(ch, 0) + 1);
        }
        
        int totalCount = 0;
        int start = 0;
        String subStr = s;
        
        for(int end = 0; end < s.length(); end++){
            char ch = s.charAt(end);
            
            if(dictMap.containsKey(ch)){                
                currMap.put(ch, currMap.getOrDefault(ch, 0) +1);
                
                if(currMap.get(ch) <= dictMap.get(ch)){
                    totalCount++;
                }
                
                                    
                if(totalCount == t.length()){
                    while(start <= end){
                        char tempCh = s.charAt(start);
                        if(dictMap.containsKey(tempCh)){
                            if(dictMap.get(tempCh) >= currMap.get(tempCh)){
                                break; 
                            }
                            currMap.put(tempCh, currMap.get(tempCh) -1);
                        }
                        start++;                        
                    }
                    
                    if( (end - start + 1) < subStr.length()){
                        subStr = s.substring(start, end+1);
                    }
                }
                
                
            }
        }
        
    
        return (totalCount < t.length() ? "" : subStr);
     }
}

//Idea: HashMap 1개 (HashMap<Character, int[])+ Sliding Window
//Time Complexity : O(S의 길이 + t의 길이) <--- 왜 t 의 길이가 더해지지???
//Space Complexity: O(S의 길이 + t의 길이)
/*
class Solution {
    public String minWindow(String s, String t) {
     
        if(s == null || s.length() <= 0 || t == null || t.length() <= 0 || s.length() < t.length()){
            return "";
        }
        
        //Making HashMap<key = character, value = frequency>
        HashMap<Character, int[]> map = new HashMap<>();
        for(int i = 0; i < t.length(); i++){
            char ch = t.charAt(i);
            if(!map.containsKey(ch)){
                int[] freq = new int[]{0,0};
                map.put(ch, freq);
            }
            int[] freq = map.get(ch);
            freq[0]++;
            //map.put(ch, freq); //안 넣어도 되나?
        }
        
        int start = 0;
        String subStr = s;
        int count = 0;
        
        for(int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if(map.containsKey(ch)){
                int[] freq = map.get(ch);
                freq[1]++;
                if(freq[1] <= freq[0]){
                    count++;
                }
                
                //move sliding window
                if(count >= t.length()){
                    while(start  <= i){
                        char tempCh = s.charAt(start);
                        if(map.containsKey(tempCh)){
                            int[] tempFreq = map.get(tempCh);
                            if(tempFreq[1] <= tempFreq[0]){
                                break;
                            }
                            tempFreq[1]--;      
                        }
                        start++;
                    }
                    
                    if(( i - start +1) < subStr.length()){
                        subStr = s.substring(start, i+1);
                    }
                }
                
            }
        }
        
        if(count < t.length()){
            return "";
        }
        
        return subStr;
                
    }
}
*/