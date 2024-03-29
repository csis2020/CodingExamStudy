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

//2023-10-20
//DFS - recursive 
//Time Complexity: O(N) <-- 모든 노드 방문
//Space Complexity: O(트리높이) <-- node 가 left 로만 또는 right 로만 있으면 트리높이가 O(N), balanced tree  이면 이진트리이니까 트리높이는 O(logN)
/*
class Solution{
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        
        return depth(root, 0);
    }
    
    // root: 3, 9, null, 
    // level: 0, 1, 2
    // (3,0) : [2,3] : 3
    // (9,1) :[(null,2), (null,2)]:2
    // (20,1): [3, 3] : 3
    // (15,2): [(null,3), (null,3)]:3
    // (7, 2): [(null,3), (null,3)]:3
    
    int depth(TreeNode root, int level){
        if(root == null){
            return level;
        }
        
        int leftDepth = depth(root.right, level+1);
        int rightDepth = depth(root.left, level+1);
        
        return (leftDepth > rightDepth) ? leftDepth : rightDepth;
    }
}
*/


//BFS - using Queue
//Time Complexity:O(N) <-- 모든노트 탐색
//Space Complexity: O(the maximum number of nodes at the same level ) 
class Solution{
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        
        int count = 0;
        Queue<TreeNode> bfs = new LinkedList<>();
        bfs.add(root);
        
        while(!bfs.isEmpty()){
            int size = bfs.size();

            for(int i = 0; i < size; i++){
                TreeNode leave = bfs.remove();
                if(leave.left != null){
                    bfs.add(leave.left);
                }
                if(leave.right != null){
                    bfs.add(leave.right);
                }
            }
            
            count++;
        }
        return count;
    }
}

//2023-09-29
//DFS - recursive function 
//Time Complexity: O(N) <-- 모든 노드 방문
//Space Complexity: O(트리높이) <-- node 가 left 로만 또는 right 로만 있으면 트리높이가 O(N), balanced tree  이면 이진트리이니까 트리높이는 O(logN)
/*
class Solution{
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        
        return dfs(root);
    }
    
    //root = [3,9,20,null,null,15,7]
    //
    int dfs(TreeNode root){
        
        if(root == null){
            return 0;
        }
        
        int leftNodeDepth = dfs(root.left);
        int rightNodeDepth = dfs(root.right);
        
        int currentDepth = Math.max(leftNodeDepth, rightNodeDepth);
        
        return currentDepth + 1;
    }
}
*/

//BFS - queue 이용
/*
class Solution{
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }
        
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        int depth = 0;
        int numberOfNodes = 1;
        while(!nodeQueue.isEmpty()){
            int count = 0;
            int numberOfChildren = 0;
            while(count < numberOfNodes){
                TreeNode node = nodeQueue.remove();
                if(node.left != null){
                    numberOfChildren++;
                    nodeQueue.add(node.left);
                }
                if(node.right != null){
                     numberOfChildren++;
                    nodeQueue.add(node.right);
                }
                count++;
            }
            numberOfNodes = numberOfChildren;
            depth++;
        }
        return depth;
    }
}
*/

 //2022-11-16
 //limitation:??
 //DFS
//Time Complexity: O(N) <-- 모든 노드 방문
//Space Complexity: O(트리높이) <-- node 가 left 로만 또는 right 로만 있으면 트리높이가 O(N), balanced tree  이면 이진트리이니까 트리높이는 O(logN)
/*
class Solution{
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        int leftMaxDepth = maxDepth(root.left);
        int rightMaxDepth = maxDepth(root.right);

        int depth = Math.max(leftMaxDepth, rightMaxDepth);

        return depth+1;
    }
}
*/
//BFS
//Time Complexity:O(N) <-- 모든노트 탐색
//Space Complexity: O(the maximum number of nodes at the same level ) 
/*
class Solution{
    public int maxDepth(TreeNode root){
        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<TreeNode>(); 
        queue.add(root);
        int depth = 0;
        int nodeNum = 1; //the number of nodes at the same level
        while(!queue.isEmpty()){

            int count = 0;
            int currNum = 0;
            while(count < nodeNum){
                TreeNode node = queue.remove();
                if(node.left != null){
                    currNum++;
                    queue.add(node.left);
                }
                if(node.right != null){
                    currNum++;
                    queue.add(node.right);
                }
                count++;
            }
            nodeNum = currNum; //update the number of nodes
            depth++;
            
        }
        return depth;
    }
}
*/

/*
class Solution {
    
    public int maxDepth(TreeNode root) {
        
        if(root == null){
            return 0;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList();
        int maxDepth = 0;
        queue.add(new Pair(root, 1));
        
        while(!queue.isEmpty()){
            Pair<TreeNode, Integer> data = queue.poll();
            TreeNode node = data.getKey();
            int depth = data.getValue();
            
            maxDepth = Math.max(maxDepth, depth);
            if(node.left != null){
                queue.offer(new Pair(node.left, depth+1));
            }
            if(node.right != null){
                queue.offer(new Pair(node.right, depth+1));
            }
        }
        
        return maxDepth;
    }  
}
*/
    //2022.04.09 ----- my solution
    /*
    public int maxDepth(TreeNode root) {
        
        if(root == null){
            return 0;
        }

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        
        int depth = Math.max(leftDepth, rightDepth);
        
        return depth+1;
    }
    */

