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

//2022-09-18
// limitation: if root is null, return empty list
// Input: root = [3,9,20,null,null,15,7]  -> Output: [[3],[20,9],[15,7]]
// Input: root = [1] -> Output: [[1]]

//BFS (Breadth-First Search)
// leetcode 를 보고 update 해서 만듬. -- reverse 함수 안써도 됨. 
// Time Complexity: O(N), Space Complexity: O(N)
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        List<List<Integer>> totalOrder = new ArrayList<List<Integer>>();
        
        if(root == null){
            return totalOrder;
        }
        
        boolean leftToRight = true;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            //ArrayList 대신 LinkedList사용 (to use addLast and addFirst)
            LinkedList<Integer> levelOrder = new LinkedList<Integer>(); 
            totalOrder.add(levelOrder);
            
            int size = queue.size();
            
            // add children nodes to queue from left to right
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                if(leftToRight){
                    levelOrder.addLast(node.val);
                }else{//From Right To Left
                    levelOrder.addFirst(node.val);
                }

                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }

            //ZigZag order change
            leftToRight = !leftToRight;
        }
        
        return totalOrder;
    }
}

/*
//BFS (Breadth-First Search) -내가 처음 풀이한 방식으로 reverse 함수를 사용함. 
// Time Complexity: O(N) ???, Space Complexity: O(N) ????
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        List<List<Integer>> totalOrder = new ArrayList<List<Integer>>();
        
        if(root == null){
            return totalOrder;
        }
        
        int level = 0;
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            //List<Integer> levelOrder = new ArrayList<Integer>();
            totalOrder.add(new ArrayList<Integer>());
            
            int size = queue.size();
            
            // add children nodes to queue from left to right
            for(int i = 0; i < size; i++){
                TreeNode node = queue.poll();
                totalOrder.get(level).add(node.val);
                
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            
            // level: 0,1,2,3, ... , even level: left -> right , odd level: right -> left
            if(level % 2 != 0){ // if level is odd, reverse order
                Collections.reverse(totalOrder.get(level));
            }
            level++;
        }
        
        return totalOrder;
    }
}
*/