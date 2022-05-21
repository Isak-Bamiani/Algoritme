package heap;

public class Node {

    int value;
    Node left, right; // Venstre og høyre barn
    Node(int value) {
        this.value = value;
        right = null;
        left = null;
    }


}