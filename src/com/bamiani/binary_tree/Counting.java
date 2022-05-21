package binary_tree;
import java.io.*;
import java.util.*;
public class Counting {



    // Unordered binary tree of characters
    public static class BinCharTree
    {
        // Root of binary tree
        treeNode root;

        // Contructor
        public BinCharTree()
        {
            root = null;
        }

        // Public search method
        public boolean contains(char x)
        {
            return contains(root, x);
        }

        // Recursive method for searching for a value "x" in the tree
        // rooted at "root"
        private boolean contains(treeNode root, char x)
        {
            if (root == null)
                return false;
            if (root.data == x)
                return true;
            return (contains(root.left, x) || contains(root.right, x));
        }


        // Public method returning number of nodes in tree
        public int numNodes()
        {
            return numNodes(root);
        }

        // Recursive method for counting number of nodes in binary tree
        // rooted at "root"
        private int numNodes(treeNode root)
        {
            if (root == null)
                return 0;
            return (1 + numNodes(root.left) + numNodes(root.right));
        }


        // Public method returning number of levels in tree
        public int numLevels()
        {
            return numLevels(root);
        }

        // Recursive method for counting number of levels in binary tree
        // rooted at "root"
        private int numLevels(treeNode root)
        {
            if (root == null)
                return 0;
            int nl = numLevels(root.left);
            int nh = numLevels(root.right);
            if (nl > nh)
                return (1 + nl);
            return (1 + nh);
        }


        // Public method returning number of leaves in tree
        public int numLeaves()
        {
            return numLeaves(root);
        }

        // Recursive method for counting number of leaf nodes in binary
        // tree rooted at "root"
        private int numLeaves(treeNode root)
        {
            if (root == null)
                return 0;
            if (root.left == null && root.right == null)
                return 1;
            else
                return (numLeaves(root.left) + numLeaves(root.right));
        }


        // Public method returning number of nodes with two childres
        public int numTwoChildren()
        {
            return numTwoChildren(root);
        }

        // Recursive method for counting number of nodes with two
        // children, in binary tree rooted at "root"
        private int numTwoChildren(treeNode root)
        {
            if (root == null)
                return 0;
            int add = 0;
            if (root.left != null && root.right != null)
                add = 1;
            return (add + numTwoChildren(root.left) +
                    numTwoChildren(root.right));
        }


                                                                                    /**     TEST PROGRAM         **/
        public static void main(String args[])
        {
	/* Using this binary tree:

	              A
                     / \
                    B   C
                   / \   \
                  /   \   \
                 /     \   \
                D       E   F
               / \     / \   \
              G   H   I   J   K
                     / \
                    L   M
	 */

            BinCharTree BCT = new BinCharTree();

            // Setting up the tree in the figure above explicitly
            BCT.root = new treeNode('A',
                    new treeNode('B',
                            new treeNode('D',
                                    new treeNode('G', null, null),
                                    new treeNode('H', null, null)),
                            new treeNode('E',
                                    new treeNode('I',
                                            new treeNode('L', null, null),
                                            new treeNode('M', null, null)),
                                    new treeNode('J', null, null))),
                    new treeNode('C', null,
                            new treeNode('F', null,
                                    new treeNode('K', null, null))));

            // Printing out the tree used in the test program
            System.out.println("\n           A");
            System.out.println("          / \\");
            System.out.println("         B   C");
            System.out.println("        / \\   \\");
            System.out.println("       /   \\   \\");
            System.out.println("      /     \\   \\");
            System.out.println("     D       E   F");
            System.out.println("    / \\     / \\   \\");
            System.out.println("   G   H   I   J   K");
            System.out.println("          / \\");
            System.out.println("         L   M\n");

            // Testing the tree's methods
            for (char c ='A'; c <= 'Z'; c+=3)
                if (BCT.contains(c))
                    System.out.println("Tree contains " + c);
                else
                    System.out.println("Tree does not contain " + c);

            System.out.print("\nNo. of nodes     : " + BCT.numNodes());
            System.out.print("\nNo. of leaves    :  " + BCT.numLeaves());
            System.out.print("\nNo. w/2 children :  " + BCT.numTwoChildren());
            System.out.print("\nNo. of levels    :  " + BCT.numLevels());
            System.out.println();
        }
    }
}
