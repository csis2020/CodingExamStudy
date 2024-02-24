
//2024-02-23
//Idea: HashMap + Sliding Window

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