import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LeftHandedRedBlackTree<V extends Comparable<V>> {
    Node<V> root;
    int size;

    public void add(V value) {
        if (root == null) {
            root = new Node<V>(value, Colors.BLACK, null, null);
        } else {
            addNode(root, value);
        }
    }

    private boolean addNode(Node<V> node, V value) {
        if (value == node.value) {
            return false;
        } else {
            if (value.compareTo(node.value) < 0) {
                if (node.leftChild != null) {
                    return addNode(node.leftChild, value);
                } else {
                    node.leftChild = new Node<V>(value, Colors.RED, null, null);
                    size++;
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    return addNode(node.rightChild, value);
                } else {
                    node.rightChild = new Node<V>(value, Colors.RED, null, null);
                    size++;
                    return true;
                }

            }
        }
    }


    private void rotateLeft() {

    }

    private void rotateRight() {

    }

    private void swapColors() {

    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<List<Node<V>>> levelNodes = new ArrayList<>();
        levelNodes.add(new ArrayList<>(List.of(root)));
        int levels = size / 2 + 1;

        for (int i = 0; i < levels; i++) {
            List<Node<V>> nextLevelNodes = new ArrayList<>();
            for (Node<V> node : levelNodes.get(i)) {
                if (node != null) {
                    nextLevelNodes.addAll(node.getChildren());
                } else {
                    nextLevelNodes.add(null);
                    nextLevelNodes.add(null);
                }
            }
            levelNodes.add(nextLevelNodes);
        }
        levels = levelNodes.size();
        String indent = "\t\t";
        List<Integer> countIndent = new ArrayList<>();
        int nextInt = 0;
        for (int i = 0; i < levels; i++) {
            countIndent.add(nextInt);
            nextInt += (int)Math.pow(2,i);
        }
        Collections.reverse(countIndent);
        int tmp = 0;
        for (int i = 0; i < levelNodes.size(); i++) {
            result.append(indent.repeat(countIndent.get(i)));
            for (int k = 0; k < levelNodes.get(i).size(); k++) {
                Node<V> currentElement = levelNodes.get(i).get(k);
                result.append(currentElement == null ? "*" : currentElement.value)
                        .append(indent.repeat(tmp + 1));
            }
            tmp = countIndent.get(i);
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
