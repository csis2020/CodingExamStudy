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
/*
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
*/

//idea2: Using previously established next pointers
//Time Complexity: O(N)
//Space Complexity: O(1)
class Solution {
    public Node connect(Node root) {
        
        if(root == null){
            return null;
        }
        
        Node leftMost = root;
        
        while(leftMost != null){
            
            Node head = leftMost;
            leftMost = null;
            Node prev = null;
            
            while(head != null){
                if(head.left != null){
                    if(prev == null){
                        leftMost = head.left; //first left node on this level.
                    }else{
                        prev.next = head.left;
                    }
                    
                    prev = head.left;
                }
                
                if(head.right!=null){
                    if(prev == null){
                        leftMost = head.right; //first left node on this level.
                    }else{
                        prev.next = head.right;
                    }
                    
                    prev = head.right;
                }
                
                head = head.next;
            }
        }
        
        return root;
    }
}

/*
class Solution {
    public Node connect(Node root) {
        
        if(root == null){
            return null;
        }
        
        Node leftMost = root;
        
        //[1,2,3,4,5,null,7]
        //leftMost: 1, 2,4
        //head:1,null ,2,3
        //prev:null, 2, 3,null,4,5
        //next: 2->3, 4->5->7
        while(leftMost != null){
            
            Node head = leftMost;
            leftMost = null; //Important! if there is no new leftMost, need to escape while
            Node prev = null;
            
            while(head != null){
                if(head.left != null){
                    if(prev == null){
                        leftMost = head.left; //its left most on this level   
                    }else{
                        prev.next = head.left;  
                    }   
                    prev = head.left;
                }
                
                if(head.right != null){
                    if(prev == null){
                        leftMost = head.right;//its left most on this level  
                    }else{
                        prev.next = head.right;
                    }
                    prev = head.right;
                }
                    
                head = head.next;
            }
        }
        
        return root;
    }
}
*/
