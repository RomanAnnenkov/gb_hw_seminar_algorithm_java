import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Left handed red-black tree implementation by task from GeekBrains.
 *
 * @author Roman Annenkov
 */
public class LeftHandedRedBlackTree<V extends Comparable<V>> {
    Node<V> root;
    int size;

    /**
     * This method is used to add element to tree
     *
     * @param value must be comparable
     */
    public void add(V value) {
        if (root == null) {
            root = new Node<V>(value, Colors.B, null, null);
            size++;
        } else {
            addNode(root, value);
            root = balance(root);
            root.color = Colors.B;
        }
    }

    /**
     * This private class use recursion for add new node to tree
     * and balance tree if it needs on the way back
     *
     * @param node  This option need for recursion.
     * @param value This is value, which must be added.
     * @return boolean This returns true if node added successfully or return false.
     */
    private boolean addNode(Node<V> node, V value) {
        if (value == node.value) {
            return false;
        } else {
            if (value.compareTo(node.value) < 0) {
                if (node.leftChild != null) {
                    boolean nodeAdded = addNode(node.leftChild, value);
                    node.leftChild = balance(node.leftChild);
                    return nodeAdded;
                } else {
                    node.leftChild = new Node<V>(value, Colors.R, null, null);
                    size++;
                    return true;
                }
            } else {
                if (node.rightChild != null) {
                    boolean nodeAdded = addNode(node.rightChild, value);
                    node.rightChild = balance(node.rightChild);
                    return nodeAdded;
                } else {
                    node.rightChild = new Node<V>(value, Colors.R, null, null);
                    size++;
                    return true;
                }

            }
        }
    }

    /**
     * This method makes left turn, this is local operation only for node and children.
     *
     * @param node node for rotate
     * @return return node after rotate
     */
    private Node<V> rotateLeft(Node<V> node) {
        Node<V> right = node.rightChild;
        node.rightChild = right.leftChild;
        right.leftChild = node;
        right.color = node.color;
        node.color = Colors.R;
        return right;
    }

    /**
     * This method makes right turn, this is local operation only for node and children.
     *
     * @param node node for rotate
     * @return return node after rotate
     */
    private Node<V> rotateRight(Node<V> node) {
        Node<V> left = node.leftChild;
        node.leftChild = left.rightChild;
        left.rightChild = node;
        left.color = node.color;
        node.color = Colors.R;
        return left;
    }

    /**
     * This method invert colors node and children.
     */
    private void swapColors(Node<V> node) {
        node.color = (node.color == Colors.B ? Colors.R : Colors.B);
        node.leftChild.color = (node.leftChild.color == Colors.B ? Colors.R : Colors.B);
        node.rightChild.color = (node.rightChild.color == Colors.B ? Colors.R : Colors.B);
    }

    /**
     * This method launches rotate left, rotate right and swap colors if necessary.
     *
     * @param node need for check
     * @return node after balance operation
     */
    private Node<V> balance(Node<V> node) {
        Node<V> result = node;
        boolean needBalance;
        do {
            needBalance = false;
            if (result.leftChild != null && result.leftChild.color == Colors.R &&
                    result.leftChild.leftChild != null && result.leftChild.leftChild.color == Colors.R) {
                needBalance = true;
                result = rotateRight(result);
            }
            if (result.rightChild != null && result.rightChild.color == Colors.R &&
                    (result.leftChild == null || result.leftChild.color == Colors.B)) {
                needBalance = true;
                result = rotateLeft(result);
            }
            if (result.leftChild != null && result.leftChild.color == Colors.R &&
                    result.rightChild != null && result.rightChild.color == Colors.R) {
                needBalance = true;
                swapColors(result);
            }
        } while (needBalance);

        return result;
    }

    /**
     * This method formed string from tree, it works correct with 1 character value with color indicator,
     * or 3 character without color indicator
     *
     * @return tree in one string
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        List<List<Node<V>>> levelNodes = new ArrayList<>();
        levelNodes.add(new ArrayList<>(List.of(root)));
        int levels = size / 2;
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
        for (int i = levelNodes.size() - 1; i >= levelNodes.size() / 2; i--) {
            if (levelNodes.get(i).stream().allMatch(Objects::isNull)) {
                levelNodes.remove(i);
            }
        }
        levels = levelNodes.size();
        String indent = "\t";
        List<Integer> countIndent = new ArrayList<>();
        int nextInt = 0;
        for (int i = 0; i < levels; i++) {
            countIndent.add(nextInt);
            nextInt += (int) Math.pow(2, i);
        }
        Collections.reverse(countIndent);
        int savedIndentCount = 0;
        for (int i = 0; i < levelNodes.size(); i++) {
            result.append(indent.repeat(countIndent.get(i)));
            for (int k = 0; k < levelNodes.get(i).size(); k++) {
                Node<V> currentElement = levelNodes.get(i).get(k);
                result.append(currentElement == null ? "*" : currentElement.value + "-" + currentElement.color)
                        .append(indent.repeat(savedIndentCount + 1));
            }
            savedIndentCount = countIndent.get(i);
            result.append("\n");
        }

        return result.toString();
    }

    /**
     * This method returns count of elements in tree
     *
     * @return count elements of tree
     */
    public int size() {
        return size;
    }

    /**
     * Base class, for this tree.
     * It has value, color, left and right child
     */
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

        /**
         * This method returns list of children.
         *
         * @return list children
         */
        private List<Node<V>> getChildren() {
            List<Node<V>> result = new ArrayList<>();
            result.add(this.leftChild);
            result.add(this.rightChild);
            return result;
        }

    }
}
