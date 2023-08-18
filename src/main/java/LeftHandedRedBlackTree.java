public class LeftHandedRedBlackTree<V extends Comparable<V>> {
    Node<V> root;
    int size;

    public void add(V v) {
        Node<V> newNode = new Node<>(v, Colors.RED, null, null);
        if (root == null) {
            newNode.color = Colors.BLACK;
            root = newNode;
        } else if ((root.value.compareTo(newNode.value) < 0) && (root.leftChild == null)) {
            root.leftChild = newNode;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node<V> currentElement = root;
        builder.append(currentElement.value).append(" - ").append(currentElement.color);
        return builder.toString();
    }

    private static class Node<V> {
        V value;
        Colors color;
        Node<V> leftChild;
        Node<V> rightChild;

        public Node(V value, Colors color, Node<V> leftChild, Node<V> rightChild) {
            this.value = value;
            this.color = color;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

    }
}
