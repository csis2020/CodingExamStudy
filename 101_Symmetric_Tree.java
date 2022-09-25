/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
  */

//2022.09.25
//limitation if root is null, return what???
// Input: root = [1,2,2,3,4,4,3]  -> Output: true
// Input: root = [1,2,2,null,3,null,3] -> Output: false

//Time Complexity: O(n), Space Complexity: O(2^(logn) <--- leetcode 랑 다른결과
//Space complexity : (2^logn) 라고 생각 ->  queue 안에 한꺼번에 들어가는 최대 크기가 Tree 의 맨 아랫단 노드개수 이므로.... 
//leetcode 솔루션은  O(n) 이라고함. ->  There is additional space required for the search queue. In the worst case, we have to insert O(n) nodes in the queue. Therefore, space complexity is O(n)

class Solution{
    public boolean isSymmetric(TreeNode root){
        
        if(root == null){
            return false;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        
        //아래 할필요가 없네... while 내에서 알아서 걸러짐. 
        //It has only root node
        //if(root.left == null && root.right == null){
        //    return true;
        //}
        
        queue.add(root.left);
        queue.add(root.right);
        
        while(!queue.isEmpty()){
            TreeNode leftNode = queue.poll();
            TreeNode rightNode = queue.poll();
            
            if(leftNode == null && rightNode ==null){
                continue;
            }else if(leftNode == null || rightNode == null){
                //Tree is not symmetric
                return false;
            }
            
            if(leftNode.val != rightNode.val){
                return false; // it is not symmetric.
            }
        
            //If the value is same, still symmetric. keep checking for its child node
            queue.add(leftNode.left);
            queue.add(rightNode.right);
            queue.add(leftNode.right);
            queue.add(rightNode.left);
        }
        
        return true;
    }
 }
 
 
 
 
//2022.07.05
//Time complexity: O(N),  Space complexity: O(N)
/*
class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        if(root == null) {
            return false;
        }
        
        if(root.left == null && root.right == null){
            return true;
        }else if(root.left == null || root.right == null){
            return false;
        }
        
        
        return recursiveCheck(root.left, root.right);
        
    }
    
    private boolean recursiveCheck(TreeNode n1, TreeNode n2){
        
        if(n1 == null && n2 == null){
            return true;
        }else if(n1 == null || n2 == null){
            return false;
        }
        
        if(n1.val != n2.val){
            return false;
        }
        
        return recursiveCheck(n1.left, n2.right) && recursiveCheck(n1.right, n2.left);
        
    }
}
*/
// limitation: root is  null, return false???
//Time complexity: O(N), Space complexity: O(N)
/*
class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        if(root == null) {
            return false;
        }
        
        //Deque<TreeNode> queue = new ArrayDeque<TreeNode>(); // null element is prohibited.
        Queue<TreeNode> queue = new LinkedList<>();
        
        queue.add(root);
        queue.add(root);
        
        while(!queue.isEmpty()){ 
            TreeNode leftNode = queue.remove();
            TreeNode rightNode = queue.remove();
            if(leftNode == null && rightNode == null){ //both side have same null.
                continue;
            }
            
            if(leftNode == null || rightNode == null){ // one of children is null
                return false;
            }
            
            if(leftNode.val != rightNode.val){ // different value
                return false;
            }

            queue.add(leftNode.left);
            queue.add(rightNode.right);
            queue.add(leftNode.right);
            queue.add(rightNode.left);            
        }
        
        return true;
    }
}
*/
//Leetcode solution - while loop   (queue 1개 가지고도 할수 있네!!!)
/*
class Solution {
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList();
                
        queue.offer(root.left);
        queue.offer(root.right);
        
        while(!queue.isEmpty()){
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();
            
            if(node1 == null && node2 == null) {
                continue;
            }
            if(node1 == null || node2 == null){  //One of them is null
                return false;
            }
            if(node1.val != node2.val){//node1 and node2 are different
                return false;
            }
            
            queue.offer(node1.left);
            queue.offer(node2.right);
            queue.offer(node1.right);
            queue.offer(node2.left);

        }
        
        return true;

    }
}
*/
//leetcode solution - recursive
/*
class Solution {
    public boolean isSymmetric(TreeNode root) {
    
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode node1, TreeNode node2){
        
        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }
        if(node1.val != node2.val){
            return false;
        }
        
        boolean isLeftMirror = isMirror(node1.left, node2.right);
        boolean isRightMirror = isMirror(node1.right, node2.left);
        
        if(isLeftMirror && isRightMirror){
            return true;
        }
        
        return false;
    }
}
*/
/*
//my solution 04,07 --- Time complexity : O(n), 
//Space complexity : (2^logn) 라고 생각 왜냐하면 queue 안에 한꺼번에 들어가는 최대 크기가 Tree 의 맨 아랫단 노드개수 이므로.... (. ===> Solution 을 보니까  틀렸음. O(n) 임. 
//설명:  There is additional space required for the search queue. 
// In the worst case, we have to insert O(n) nodes in the queue. 
// Therefore, space complexity is O(n)

class Solution {
    public boolean isSymmetric(TreeNode root) {
        
        Queue<TreeNode> leftTreeQueue = new LinkedList();
        Queue<TreeNode> rightTreeQueue = new LinkedList();
    
        leftTreeQueue.offer(root.left);
        rightTreeQueue.offer(root.right); 
                
        while(!leftTreeQueue.isEmpty() || !rightTreeQueue.isEmpty()){
            if(!leftTreeQueue.isEmpty() && !rightTreeQueue.isEmpty()){
                
                TreeNode lqNode = leftTreeQueue.poll();
                TreeNode rqNode = rightTreeQueue.poll();
                
                if((lqNode != null) && rqNode != null){

                    if(lqNode.val != rqNode.val){
                        return false;
                    }

                    leftTreeQueue.offer(lqNode.left);
                    leftTreeQueue.offer(lqNode.right);
                    rightTreeQueue.offer(rqNode.right);   
                    rightTreeQueue.offer(rqNode.left);                    
                }else if(lqNode == null  && rqNode == null ){
                    continue;
                }else{ //one of them is null
                    return false;
                }
                
            }else{
                System.out.println("one of them is empty.");
                return false;
            }
            
        }
        
        return true;
    }
}

*/