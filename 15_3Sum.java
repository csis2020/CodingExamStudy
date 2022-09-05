//2022.09.04
// limitation: if nums is null or size is 0, return null
// Input: nums = [-1,0,1,2,-1,-4] , Output: [[-1,-1,2],[-1,0,1]]
// Input: nums = [0,1,1] , Output: []

//Time Complexity: O(n^2) <--sorting time
// Space Complexity: O(n)

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums == null || nums.length < 3){
            return null;
        }
        
        Arrays.sort(nums);
        
        List<List<Integer>> tripletList = new ArrayList<List<Integer>>();
        
        int length = nums.length; 
        for(int i = 0; i < (nums.length-2); i++ ){
            
            if(i != 0 && nums[i] == nums[i-1]){ // duplicate case skip
                continue;
            }
            
            int sum = nums[i] * -1;
            
            int low = i+1;
            int high = nums.length-1;
            while(low < high){
                int tempSum = nums[low] + nums[high];
                if(tempSum == sum){
                    List<Integer> triplet = Arrays.asList(nums[i], nums[low], nums[high]);
                    tripletList.add(triplet);
                    
                    low++;
                    while( (low <= (length -1)) && (nums[low-1] == nums[low])){
                        low++;
                    }
                    high--;
                    while((high > (i+1)) && (nums[high] == nums[high+1])){//이건 필요없나?
                        high--;
                    }
                    
                }else if(tempSum > sum){
                    high--;
                }else{//tempSum < sum
                    low++;
                }
                
            }
        } 
        
        return tripletList;
        
    }
}

//아래 솔루션은 triplet 세트는 구했으나 그 세트내에서 duplicate 이 존재함. 
//ex. Input: nums = [-1,0,1,2,-1,-4],  output: [[-1,1,0],[-1,-1,2],[0,1,-1],[1,0,-1],[2,-1,-1]]
/*
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        if(nums == null || nums.length == 0){
            return null;
        }
        
        int length = nums.length;
        List<List<Integer>> tripletList = new ArrayList<List<Integer>>();
        
        
        HashSet<Integer> threeSumMap = new HashSet<Integer>();
        HashMap<Integer, Integer> twoSumMap;
        
        //make first map.  HashMap(Key: needed Sum, value: index)
        for(int i = 0; i < length; i++){
            int neededSum = nums[i] * -1;
            
            
            //if it is not duplicated
            if(!threeSumMap.contains(neededSum)){ // hashset 으로 바꾸자.... 
                threeSumMap.add(neededSum);
                //System.out.println("["+i+"]:"+nums[i]+"_neededSum:" + neededSum);
                //create new list
                twoSumMap = new HashMap<Integer, Integer>();
                for(int j = 0; j < length; j++){
                    if(i != j){
                        int secondNum = nums[j];
                        
                        //System.out.println("["+i+"]:"+nums[i]+"_neededSum:" + neededSum + ", j["+j+"]:" + nums[j]+" neededNum=" + neededNum);
                        
                        if(twoSumMap.containsKey(secondNum)){
                            int thirdIndex = twoSumMap.get(secondNum);
                            if(thirdIndex != i){
                                List<Integer> triplet = Arrays.asList(nums[i], nums[j], nums[thirdIndex]);
                                tripletList.add(triplet); 
                            }
                        }else{
                            int neededNum = neededSum - secondNum;
                            twoSumMap.put(neededNum, j);
                        }
                            
                    }
                }
            }
        }
        
        return tripletList;
        
    }
}
*/