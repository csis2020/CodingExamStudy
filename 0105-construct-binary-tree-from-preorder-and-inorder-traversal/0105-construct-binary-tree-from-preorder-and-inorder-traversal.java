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

//2023-10-21
//idea : preorder 의 첫 시작이 root,  inorder 에서 root 를 찾으면, root 전까지 left tree, root 앞은 right tree => recursive 함수 이용 
 class Solution{
     public TreeNode buildTree(int[] preorder, int[] inorder){
         if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
             return null;
         }
         
         return makeTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1); 
     }
     
     TreeNode makeTree(int[] preorder, int[] inorder, int preStart, int preEnd, int inStart, int inEnd){
         
         if(preStart > preEnd || inStart > inEnd){
             return null;
         }
         if(preStart == preEnd){
             return new TreeNode(preorder[preStart]);
         }
         
         int rootVal = preorder[preStart];
         TreeNode node = new TreeNode(rootVal);
         
         int rootPosition = 0;
         for(int i = inStart; i <= inEnd; i++){
             if(inorder[i] == rootVal){
                 rootPosition = i;
                 break;
             }
         }
         
     //preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
     //[0,4][0,4], l:[1,1][0,0], r:[2,4][2,4], l:[3,3][2,2], r:[4,4][4,4]
     //sizeLeft:1, 1
     //sizeRight:3,1
     //root:1, 3
     //TREE: 3-(9,20-(15,7))
         int sizeOfLeftTree = rootPosition - inStart;
         int sizeOfRightTree = inEnd - rootPosition;
         node.left = makeTree(preorder, inorder, preStart+1, preStart + sizeOfLeftTree, inStart, rootPosition -1 );
         node.right = makeTree(preorder, inorder, preStart + sizeOfLeftTree + 1, preEnd, rootPosition +1, inEnd );
             
        return node;
     }
 }














 //2022-11-25
//Time Complexity : O(N)
 //Space Complexity : O(1)
/*
 class Solution{
     public TreeNode buildTree(int[] preorder, int[] inorder){
         if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
             return null;
         }

         return buildSubTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length -1);
     }

     private TreeNode buildSubTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd){

         if((preEnd - preStart) < 0 || (inEnd - inStart) < 0){
             return null;
         }

         TreeNode root = new TreeNode(preorder[preStart]);

         int mid = 0;
         for(int i = inStart; i <= inEnd; i++){
             if(inorder[i] == root.val){
                 mid = i;
                 break;
             }
         }
         int leftSize = mid - inStart;
         int rightSize = inEnd - mid;

         root.left = buildSubTree(preorder, preStart+1, preStart + leftSize, inorder, inStart, mid-1);
         root.right = buildSubTree(preorder, preStart+leftSize+1, preEnd, inorder, mid+1, inEnd);

         return root;
     }
 }
 */

 //Time Complexity : O(N)
 //Space Complexity : O(1)
 /*
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
            return null;
        }

        TreeNode root = new TreeNode(preorder[0]);
        addChildNode(root, preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);

        return root;
    }

    private void addChildNode(TreeNode root, int[] preorder, int ps, int pe, int[] inorder, int is, int ie ){
        int root_pos = 0;
        for(int i = is; i <= ie; i++){
            if(inorder[i] == root.val){
                root_pos = i;
                break;
            }
        }

        int leftSize = root_pos - is;
        int rightSize = ie - root_pos;
        
        if(leftSize > 0){
            TreeNode left = new TreeNode(preorder[ps+1]);
            root.left = left;
            if(leftSize > 1){
                addChildNode(root.left, preorder, ps+1, ps+leftSize, inorder, is, root_pos -1 );
            }
        }
        if(rightSize > 0){
            TreeNode right = new TreeNode(preorder[ps+leftSize+1]);
            root.right = right;
            if(rightSize > 1){
                addChildNode(root.right, preorder, ps+leftSize+1, pe, inorder, root_pos+1, ie );
            }
        }

    }
}
*/