package com.adaptive;



public class Program4 {


    static class TreeNode
    {
        int data;
        TreeNode left, right;

        TreeNode(int item)
        {
            data = item;
            left = right = null;
        }
    }

    /*
    creating a TreeNode.
    **/

    /*
    Assumption: The following Treenode accepts value as integer. But tree should be generic like
    TreeNode<T> . It was not implemented due to time.
     */


    TreeNode root;

    public boolean hasPathSum(TreeNode root, int sum) {

        if(root==null)
            return false;

        boolean ans=false;

        int subsum=sum-root.data;

        if(subsum==0 && root.left==null && root.right==null)
            return true;

        if(root.left!=null)
            ans=ans || hasPathSum(root.left,subsum);

        if(root.right!=null)
            ans=ans || hasPathSum(root.right,subsum);

        return ans;
    }

    public TreeNode getRoot() {
        return root;
    }

}
