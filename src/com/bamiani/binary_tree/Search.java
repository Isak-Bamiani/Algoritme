package binary_tree;

public class Search {
    // Inner class for binary tree nodes with single-character data
    private class treeNode
    {
        char data;
        Insertion.treeNode left, right;

        public treeNode(char value)
        {
            data = value;
            left = right = null;
        }

        void write()
        {
            System.out.print(data + " ");
        }
    }

    // Root of entire search tree
    private Insertion.treeNode root;

    // Constructor, creates empty tree
    public void binarySearchTree()
    {
        root = null;
    }

    // Check for empty tree
    public boolean isEmpty()
    {
        return (root == null);
    }


    //
    // Iterative search in binary search tree
    //
    public boolean search(char value)
    {
        Insertion.treeNode current = root;

        while (current != null)
        {
            if (current.data == value)
                return true;
            if (value < current.data)
                current = current.left;
            else
                current = current.right;
        }
        return false;
    }


}
