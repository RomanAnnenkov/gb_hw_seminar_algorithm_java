public class Main {
    public static void main(String[] args) {
        LeftHandedRedBlackTree<Integer> integerTree = new LeftHandedRedBlackTree<>();
        integerTree.add(5);
        integerTree.add(3);
        integerTree.add(7);
        integerTree.add(1);
        integerTree.add(4);
        integerTree.add(6);
//        integerTree.add(8);
//        integerTree.add(9);
//        integerTree.add(-20);
//        integerTree.add(-10);
        System.out.println(integerTree);


        LeftHandedRedBlackTree<String> stringTree = new LeftHandedRedBlackTree<>();
        stringTree.add("d");
        stringTree.add("b");
        stringTree.add("c");

        System.out.println(stringTree);
    }
}
