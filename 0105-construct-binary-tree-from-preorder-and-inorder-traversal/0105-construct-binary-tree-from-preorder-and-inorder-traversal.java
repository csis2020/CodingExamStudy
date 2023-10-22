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
//Time Complexity: O(N^2) <-- root 의 search 타임이 있음.  N^2 이 됨.
//      그래서 leetcode solution에서는 inorder배열을 hashSet 에 넣어서 search time 을 없에고 O(N)으로 만들었다. 대신 Space complexity 는 O(N), 그러나 어차피 원래도 Space complexity 의 worst case 는 O(N)이긴 했으니 손해가 아니네.
//Space Complexity: O(H) <- recursive 함수의 stack size 는 tree 높이 

/*
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
*/

//idea: 기본 개념은 위에 내가 푼것과 동일하고, time complexity 를 줄이기 위해 inorder 를 hashMap 에 넣음. 이를 통해서 시간도 줄임.
//Time Complexity: O(N) 
//Space Complexity: O(N) <- HashMap size
 class Solution{
     public TreeNode buildTree(int[] preorder, int[] inorder){
         if(preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0){
             return null;
         }
         
         HashMap<Integer, Integer> inorderMap = new HashMap<>();
         for(int i = 0; i < inorder.length; i++){
             inorderMap.put(inorder[i], i);
         }
         
         
         return makeSubTree(preorder, inorderMap, 0, preorder.length -1, 0, inorder.length -1);
     }
    
     TreeNode makeSubTree(int[] preorder, HashMap<Integer, Integer> inorderMap, int preStart, int preEnd, int inStart, int inEnd ){
         if(preStart > preEnd || inStart > inEnd ){
             return null;
         }
         
        int rootValue = preorder[preStart];
         TreeNode node = new TreeNode(rootValue);
        
         if(preStart == preEnd){
             return node;
         }
         
         int mid = inorderMap.get(rootValue);
         
         int leftSize = mid - inStart;
         int rightSize = inEnd - mid;
         node.left = makeSubTree(preorder, inorderMap, preStart+1, preStart + leftSize, inStart, mid -1);
         node.right = makeSubTree(preorder, inorderMap, preStart + leftSize +1, preEnd,  mid+1, inEnd);
        
        return node;
     }
    
 }


//아래는 leetcode solution 인데, preorderIndex 를 따로 두고 1씩 count 하는게 잘 이해가 되지 않았다. 그냥 내가 하던 방식대로 하는게 나을듯. 
/*
class Solution {
    int preorderIndex;
    Map<Integer, Integer> inorderIndexMap;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preorderIndex = 0;
        // build a hashmap to store value -> its index relations
        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length - 1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        // if there are no elements to construct the tree
        if (left > right) return null;

        // select the preorder_index element as the root and increment it
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        // build left and right subtree
        // excluding inorderIndexMap[rootValue] element because it's the root
        root.left = arrayToTree(preorder, left, inorderIndexMap.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndexMap.get(rootValue) + 1, right);
        return root;
    }
}
*/









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