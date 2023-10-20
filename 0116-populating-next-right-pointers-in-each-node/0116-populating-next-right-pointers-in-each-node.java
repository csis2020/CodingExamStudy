/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

//Time Complexity: O(N)
//Space Complexity: O(N)
/*
class Solution {
    public Node connect(Node root) {
        if(root == null){
            return null;
        }
         
        Queue<Node> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);
        bfsQueue.add(null);
        
        Node prev = null;
        
        //queue: (1, null)->(null, 2, 3, null)->(2,3,null)->(3,null,4,5)->(null,4,5,6,7,null)
        //      ->(4,5,6,7,null)->(5,6,7,null)->(6,7,null)->(7,null)->(null,null)->(null)->()
        //prev: null, 1,null, 2,3,null,4,5,6,7,null
        //leave: 1, null, 2,3,null,4,5,6,7,null,null
        //endR: true
        //next: 1->null,2->3->null,4->5->6->7->null
        while(!bfsQueue.isEmpty()){
            Node leave = bfsQueue.remove();
            if(prev != null)
            {
                prev.next = leave;
            }           
            prev = leave;
            
            if(leave == null){
                continue;
            }
            
            boolean endRight = false;
            
            if(bfsQueue.peek() == null){ // it means that current leave is end of right in this level
                endRight = true;
            }
                
            if(leave.left != null){ //bottom level node 때문에 필요 
                bfsQueue.add(leave.left);
            }
            if(leave.right != null){ //bottom level node 때문에 필요  
                bfsQueue.add(leave.right);
            }
            if(endRight){
                bfsQueue.add(null);
            }

        }
            
        return root;
    }
}
*/


class Solution {
    public Node connect(Node root) {
                if(root == null){
            return null;
        }
         
        Queue<Node> bfsQueue = new LinkedList<>();
        bfsQueue.add(root);        
       
        while(!bfsQueue.isEmpty()){
           
            int size = bfsQueue.size();
            Node prev = null;
            for(int i = 0; i < size; i++){
                Node leave = bfsQueue.remove();
                if(prev != null){
                    prev.next = leave;                    
                }
                
                if(leave.left != null){
                    bfsQueue.add(leave.left);
                }
                if(leave.right != null){
                    bfsQueue.add(leave.right);
                }   
                prev = leave;
            }
        }
            
        return root;
    }
}