package binary_tree;

public class Remove {
    // Inner class for binary tree nodes with single-character data
    private class treeNode {
        char data;
        treeNode left, right;

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
    private treeNode root;

    // Constructor, creates empty tree
    public void binarySearchTree() {
        root = null;
    }

    // Check for empty tree
    public boolean isEmpty(){
        return (root == null);
    }


    // Remove node with given value from binary search tree.
    //
    // Accepts attempts to remove from an empty tree or removal of
    // non-existing values.
    //
    // Uses the method "replacement" to do the removal and
    // restructuring of the tree, after the node to be removed has
    // been located.
    //
    public void remove(char value)
    {
        if (!isEmpty())
        {
            // Removal of root?
            if (value == root.data)
                root = replacement(root);
            else
            {
                // Find the node to be removed and replace it

                treeNode parent = root, current;
                boolean finished = false;

                // Starting from a child of the root node
                if (value < root.data)
                    current = root.left;
                else
                    current = root.right;

                // Loop until node is found or tree exhausted
                while (current != null && !finished)
                {
                    if (current.data == value)
                    {
                        // Node to be removed found, remove and replace
                        if (current == parent.left)
                            parent.left = replacement(current);
                        else
                            parent.right = replacement(current);
                        finished = true;
                    }
                    else
                    {
                        // Move on down the tree
                        parent = current;
                        if (value < current.data)
                            current = current.left;
                        else
                            current = current.right;
                    }
                }
            }
        }
    }


    //
    // Handles the three different cases of removal of a treenode
    // t from a binary tree:
    //
    // 1. t is a leaf
    // 2. t has only one non-empty subtree
    // 3. t has two non-empty subtreest
    //
    // Returns the node (or null) that replaces the deleted node
    // in the tree.
    //
    private treeNode replacement(treeNode t)
    {
        // Case 1: Removing leaf node
        if (t.left == null && t.right == null)
            return null;

            // Case 2: One non-empty subtree
        else if (t.left != null && t.right == null)
            return t.left;
        else if (t.left == null && t.right != null)
            return t.right;

            // Case 3: Two non-empty subtrees.
            // Replacing with rightmost node in left subtre
        else
        {
            // Special case, t.left has no right subtree
            if (t.left.right == null)
            {
                t.left.right = t.right;
                return t.left;
            }

            // Finding rightmost node in left subtree,
            // also holding track of parent node
            treeNode current = t.left, parent = t;
            while (current.right != null)
            {
                parent = current;
                current = current.right;
            }

            // Replacing t with rightmost node
            parent.right = current.left;
            current.left = t.left;
            current.right = t.right;
            return current;
        }
    }


    //
    // Printout of inorder tree traversal, for demo purposes
    //
    public void inorder()
    {
        System.out.print("Inorder: ");
        inorder(root);
        System.out.println("\n");
    }

    private void inorder(treeNode t)
    {
        if (t != null)
        {
            inorder(t.left);
            t.write();
            inorder(t.right);
        }
    }
}