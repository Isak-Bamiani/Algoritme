package heap;


import java.util.LinkedList;
import java.util.Queue;

public class DeleteLast {

    public int DeleteLast(Node root) {
        int val = 0;
        // feil håndtering to tilfeller
        if (root == null) {
            // Feilhåndtering av ett eller annet slag
            return val;
        }
        if (root.left == null) {
            // Dette vil si at vi har bare en node i treet
            // Dette er et spesialtilfelle som må håndteres
            // før denne metoden kalles
            // Feilhåndtering av ett eller annet slag
            return root.value;
        }

        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        Node prev = null;
        while (!q.isEmpty()) {
            Node curr = q.remove();

            // stopp hvis en de betingelser er riktige
            if (curr.right == null && curr.left != null) {
                val = curr.left.value;
                curr.left = null;
                break;
            }
            if (curr.left == null) {
                val = prev.right.value;
                prev.right = null;
                break;
            }
            // hvis ingen av barnnoder av current node er null
            if (curr.left != null) {
                q.add(curr.left);
            }
            if (curr.right != null) {
                q.add(curr.right);
            }
            prev = curr;
        }
        return val;
    }
}