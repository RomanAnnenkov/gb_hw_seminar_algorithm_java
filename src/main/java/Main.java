public class Main {
    public static void main(String[] args) {
        LeftHandedRedBlackTree<Integer> integerTree = new LeftHandedRedBlackTree<>();
        integerTree.add(4);
        System.out.println(integerTree);

        LeftHandedRedBlackTree<String> stringTree = new LeftHandedRedBlackTree<>();
        stringTree.add("str");
        System.out.println(stringTree);
    }
}
