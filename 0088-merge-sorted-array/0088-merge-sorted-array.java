//2024-02-23
// compare one by one from the end of arrays.
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        if(nums1 == null ||  nums2 == null || nums1.length < nums2.length){
            return;
        }

        if(n == 0){
            return;
        }

        int pos = nums1.length -1;
        int count1 = m -1;
        int count2 = n -1;

        //nums1 = [1,2,3,0,0,0], m:3, nums2 = [2,5,6] n:3
        //pos: 5 , 4 , 3, 2, 1
        //count1 : 2, 2, 2, 1, 1
        //count2: 2, 1, 0, 0, -1
        //nums1: 3 < 6 :[1,2,3,0,0,6]-> 3 <5 : [1,2,3,0,5,6]->
        //      3 >2:[1,2,3,3,5,6] -> 2=2:[1,2,2,3,5,6]

        //nums1 = [2,0], m:1,  nums2 = [1], n:1
        //pos: 1, 0
        //count1 = 0, -1
        //count2 = 0, 0
        //nums1: 2 > 1:[2,2] , [1,2]

        //nums1 = [0], m:0, nums2 = [1], n:1
        //pos:0
        //count1:-1
        //count2:0
        //nums1 : [1]
        while(count2 >= 0 && pos >= 0){

            if(count1 >= 0 && nums1[count1] > nums2[count2]){ 
                nums1[pos] = nums1[count1];
                count1--;
            }else{
                nums1[pos] = nums2[count2];
                count2--;
            }
            pos--;
        }
    }
}

//Limitation : nums2 is null -> 
//Time Complexity: O(M+N) , Space Complexity: O(1)
/*
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        if(nums2 == null || n == 0){
            return;            
        }
        
        int c1 = m -1;
        int c2 = n -1;
        int index = m + n -1;
        
        //nums1 = [1,2,0,0,0], m = 3, nums2 = [3,5,6], n = 3
        // [1,2,2,3,5,6]
        
        while( c1 >= 0  && c2 >= 0){
            if(nums1[c1] > nums2[c2]){
                nums1[index] = nums1[c1];
                c1--;
            }else{
                nums1[index] = nums2[c2];
                c2--;
            }
            index--;
        }
        
        if(c2 >= 0){
            while(c2 >= 0){
               nums1[index--] = nums2[c2--];             
            }
        }
    }
}
*/

//Discussion 에 있던 솔루션중 좋다고 생각된 아이디어를 참조하여 만듬. 
/*
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
       
        if(n <= 0){
            return;
        }        
                
        int i = m-1;
        int j = n-1;
        int total = m+n-1;
                
        // Input: nums1 = [0], m = 0, nums2 = [1], n = 1 와 같은 경우가 있어. 아래처럼 ( i >= 0 ) 을 체크해야 함. 
        while(j >= 0){
            nums1[total--] = ( i >= 0  && nums1[i] > nums2[j]) ? nums1[i--] : nums2[j--];
            
        }
    }
}
*/
//My solution - 04-12
/*
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        //풀기.
        
        if( (m + n) <= 0 ){
            return;
        }
        
        int[] mergedNums = new int[m+n];
        
        int i, j;
        i = j = 0;
        
        while( i < m && j < n){
            if(nums1[i] < nums2[j]){
                mergedNums[i+j] = nums1[i];
                i++;
            }else{
                mergedNums[i+j] = nums2[j];
                j++;
            }
        }
        
        if( j < n){
            while( j < n){
                mergedNums[i+j] = nums2[j];
                j++;
            }
        }else if ( i < m) {
            while( i < m){
                mergedNums[i+j] = nums1[i];
                i++;
            }
        }
       
        for(i = 0; i < nums1.length; i++){
            nums1[i]= mergedNums[i];
        }
        
    }
}
*/