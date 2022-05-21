package binary_tree;

public class Insertion {

        // Inner class for binary tree nodes with single-character data
        public class treeNode {
            char data;
            treeNode left, right;

            public treeNode(char value){
                data = value;
                left = right = null;
            }

            void write(){
                System.out.print(data + " ");
            }
        }

        // Root of entire search tree
        private treeNode root;

        // Constructor, creates empty tree
        public void binarySearchTree()
        {
            root = null;
        }

        // Check for empty tree
        public boolean isEmpty() {
            return (root == null);
        }

                                                          /*****    Insertion    ****/

        public void insert(char value)
        {
            treeNode newNode = new treeNode(value);

            // Create new root if tree is empty
            if (isEmpty())
            {
                root = newNode;
                return;
            }

            treeNode current = root;
            boolean finished = false;

            // Insert new node as a leaf in the tree
            while (!finished)
            {
                if (value < current.data)
                {
                    if (current.left == null)
                    {
                        current.left = newNode;
                        finished = true;
                    }
                    else
                        current = current.left;
                }
                else
                {
                    if (current.right == null)
                    {
                        current.right = newNode;
                        finished = true;
                    }
                    else
                        current = current.right;
                }
            }
        }
}


