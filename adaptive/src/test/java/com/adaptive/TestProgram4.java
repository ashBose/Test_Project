package com.adaptive;

import org.junit.Test;
import static org.junit.Assert.*;

public class TestProgram4 {

    @Test
    public void testPathSum() {

        int sum = 21;

        //Check for positive case
        Program4 tree = new Program4();
        tree.root = new Program4.TreeNode(10);
        tree.root.left = new Program4.TreeNode(8);
        tree.root.right = new Program4.TreeNode(2);
        tree.root.left.left = new Program4.TreeNode(3);
        tree.root.left.right = new Program4.TreeNode(5);
        tree.root.right.left = new Program4.TreeNode(2);

        assertEquals(tree.hasPathSum(tree.root, 21), true);


        Program4 tree1 = new Program4();
        tree1.root = new Program4.TreeNode(10);
        tree1.root.left = new Program4.TreeNode(12);
        tree1.root.right = new Program4.TreeNode(2);
        assertEquals(tree.hasPathSum(tree1.root, 21), false);


        //Test when tree is null.
        Program4 tree2 = new Program4();
        assertEquals(tree2.hasPathSum(tree2.root, 1), false);

        //Test when sum is Zero
        Program4 tree5 = new Program4();
        tree5.root = new Program4.TreeNode(3);
        tree5.root.left = new Program4.TreeNode(-2);
        tree5.root.right = new Program4.TreeNode(-1);
        assertEquals(tree5.hasPathSum(tree5.root, 0), false);


        //Test when sum is Zero
        Program4 tree6 = new Program4();
        tree6.root = new Program4.TreeNode(3);
        tree6.root.left = new Program4.TreeNode(-2);
        tree6.root.left.left = new Program4.TreeNode(-1);
        assertEquals(tree5.hasPathSum(tree6.root, 0), true);

    }
}
