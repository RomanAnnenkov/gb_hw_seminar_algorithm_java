import java.util.ArrayList;
import java.util.List;

public class LeftHandedRedBlackTree<V extends Comparable<V>> {
    Node<V> root;
    int size;

    public void add(V v) {
        Node<V> newNode = new Node<>(v, Colors.RED, null, null);
        if (root == null) {
            newNode.color = Colors.BLACK;
            root = newNode;
            size++;
        }

        if ((root.value.compareTo(newNode.value) > 0) && (root.leftChild == null)) {
            root.leftChild = newNode;
            size++;
        }

        if ((root.value.compareTo(newNode.value) < 0) && (root.rightChild == null)) {
            root.rightChild = newNode;
            size++;
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<List<Node<V>>> levelNodes = new ArrayList<>();
        levelNodes.add(new ArrayList<>(List.of(root)));
        int levels = size / 2;

        for (int i = 0; i < levels; i++) {
            List<Node<V>> nextLevelNodes = new ArrayList<>();
            for (Node<V> node : levelNodes.get(i)) {
                nextLevelNodes.addAll(node.getChildren());
            }
            levelNodes.add(nextLevelNodes);
        }

        for (int i = 0; i < levelNodes.size(); i++) {
            for (int j = 0; j < levelNodes.get(i).size(); j++) {
                for (int k = 0; k < levels - i; k++) {
                    result.append("\t");
                }
                Node<V> currentNode = levelNodes.get(i).get(j);
                if (currentNode == null) {
                    result.append("\t\t");
                } else {
                    result.append(currentNode.value)
//                        .append("-")
//                        .append(currentNode.color)
                            .append("\t\t");
                }
            }
            result.append("\n");
        }

        return result.toString();
    }

    public int size() {
        return size;
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

        private List<Node<V>> getChildren() {
            List<Node<V>> result = new ArrayList<>();
            result.add(this.leftChild);
            result.add(this.rightChild);
            return result;
        }

    }
}
