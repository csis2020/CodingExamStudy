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

//idea1 : bfs using queue
//Time Complexity: O(N)
//Space Complexity: O(N)
class Solution {
    public Node connect(Node root) {
        
        if(root == null){
            return null;
        }
        
        Queue<Node> bfs = new LinkedList<>();
        bfs.add(root);

        while(!bfs.isEmpty()){            
            int size = bfs.size();
            Node pre = null;
            for(int i = 0; i < size; i++){
                Node leave = bfs.remove();
                if(pre != null){
                    pre.next = leave;
                }
                if(leave.left != null){
                    bfs.add(leave.left);
                }
                if(leave.right != null){
                    bfs.add(leave.right);
                }
                
                pre = leave;
            }          
            
        }
        return root;
    }
}


//idea2: Using previously established next pointers
/*
class Solution {
    public Node connect(Node root) {
        
        if(root == null){
            return null;
        }
        
        ListNode leftMost = root;
        while(leftMost != null)
    }
}
*/