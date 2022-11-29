//2022-11-29
//https://youtu.be/q6IEA26hvXc   <---refer this Youtube
//Time Complexity O(log(min(m,n))) m is nums1.length, n is nums2.length
//Space Complexity O(1)
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if(nums1 == null || nums1.length == 0){
            return getMedian(nums2);
        }else if(nums2 == null || nums2.length == 0){
            return getMedian(nums1);
        }

        int[] numsA;
        int[] numsB;
        //shorter length of array -> numsA 
        if(nums1.length < nums2.length){
            numsA = nums1;
            numsB = nums2;
        }else{
            numsA = nums2;
            numsB = nums1;
        }

        int total = numsA.length + numsB.length;
        int half = total/2;
        int start = 0;
        int end = numsA.length -1;
        int aLeft = 1;
        int aRight = 0;
        int bLeft = 1;
        int bRight = 0;

        while( (aLeft > bRight ) || (bLeft > aRight)){
            int aPos, bPos;
            
            if(end < 0){ // it happens this case -> ex) numsB:[1,2,3,4,5,6,7], numsA:[8,9]
                aPos = -1;
            }else{
                aPos = (start + end) /2;
            }
             
            bPos = half - aPos -2;

            if( aPos >= 0){
                aLeft = numsA[aPos];
            }else{
                aLeft = Integer.MIN_VALUE;
            }

            if( (aPos+1) < numsA.length){
                aRight = numsA[aPos+1];
            }else{
                aRight = Integer.MAX_VALUE;
            }

            if( bPos  >= 0){
                bLeft = numsB[bPos];
            }else{
                bLeft = Integer.MIN_VALUE;
            }

            if( (bPos+1) < numsB.length){
                bRight = numsB[bPos+1];
            }else{
                bRight = Integer.MAX_VALUE;
            }

            if(aLeft > bRight){
                end = aPos -1;
            }else if(bLeft > aRight){
                start = aPos +1;
            }

        }

        if(total %2 == 1){ //odd
            return Math.min(aRight, bRight);
        }else{ //even
             double median = (Math.max(aLeft, bLeft) + Math.min(aRight, bRight))/2.0;
             return median;
        }

    }

    private double getMedian(int[] nums){
        int size = nums.length;
        int mid = size /2;
        if(size %2 == 1){ //odd
            return nums[mid];
        }else{ //even
            return (nums[mid-1] + nums[mid])/2.0;
        }
    }
}