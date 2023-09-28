
//2023-09-28
//Idea:  HashMap + Sliding Window => 여기서 HashMap<Value, Value 나타난횟수>
class Solution {
    public int totalFruit(int[] fruits) {
        
        if(fruits == null || fruits.length <= 0){
            return 0;
        }
        
        Map<Integer, Integer> typeMap = new HashMap<>(); //<key, value> =< type, count>
        
        int start = 0;
        int longest = 0;
        for(int end = 0; end < fruits.length; end++){
            
            typeMap.put(fruits[end], typeMap.getOrDefault(fruits[end],0) + 1);
            
            while(typeMap.size() > 2){
                int key = fruits[start];
                typeMap.put(key, typeMap.get(key) - 1);
                if(typeMap.get(key) == 0){
                    typeMap.remove(new Integer(key));
                }
                start++;
            }
            longest = Math.max(longest, end - start + 1);
        }
        return longest;
    }
}