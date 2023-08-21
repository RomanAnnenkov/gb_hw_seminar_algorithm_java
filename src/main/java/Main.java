public class Main {
    public static void main(String[] args) {
        LeftHandedRedBlackTree<Integer> integerTree = new LeftHandedRedBlackTree<>();
        for (int i = 0; i < 10; i++) {
            integerTree.add(i);
            System.out.println("------------------------");
            System.out.println("add - " + i);
            System.out.println(integerTree);
            System.out.println("------------------------");
        }


        LeftHandedRedBlackTree<String> stringTree = new LeftHandedRedBlackTree<>();
        stringTree.add("d");
        stringTree.add("b");
        stringTree.add("c");
        stringTree.add("a");

        System.out.println(stringTree);
    }
}
