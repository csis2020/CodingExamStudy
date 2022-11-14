//2022-11-13
//Time Complexity : O(n)
//Space Complexity: O(k)
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() == 0 || k <= 0){
            return 0;
        }

        int longest = 0; 
        int left = 0; 
        int currSize = 0;

        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for(int right = 0; right < s.length(); right++){
            char ch = s.charAt(right);

            charCountMap.put(ch, charCountMap.getOrDefault(ch,0)+1);
            while(charCountMap.size() > k){
                int count = charCountMap.get(s.charAt(left));
                if(count <= 1){
                    charCountMap.remove(s.charAt(left));
                }else{
                    charCountMap.put(s.charAt(left), count -1);
                }
                left++;
            }
            currSize = right - left + 1;
            longest = Math.max(longest, currSize);
        }

        return longest;
    }
}