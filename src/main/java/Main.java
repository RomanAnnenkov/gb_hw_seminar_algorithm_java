public class Main {
    public static void main(String[] args) {
        LeftHandedRedBlackTree<Integer> integerTree = new LeftHandedRedBlackTree<>();
        for (int i = 0; i < 10; i++) {
            integerTree.add(i);
            System.out.println(integerTree);
        }

        LeftHandedRedBlackTree<String> stringTree = new LeftHandedRedBlackTree<>();
        stringTree.add("str");
        System.out.println(stringTree);
    }
}
