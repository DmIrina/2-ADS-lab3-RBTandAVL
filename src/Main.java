import redBlackTree.RedBlackTree;

public class Main {

    public static void main(String[] args) {
        System.out.println("Лабораторна робота 3");

        RedBlackTree<Integer> rbt = new RedBlackTree<>(14);

        System.out.println("Дослідження бінарних дерев пошуку");

        rbt.add(10);
        rbt.add(15);
        rbt.add(5);
        rbt.add(11);
        rbt.add(3);
        rbt.add(4);
        rbt.add(100);
        rbt.add(54);
        rbt.add(7);
        rbt.add(65);
        rbt.add(9);
        rbt.add(12);
        rbt.add(34);
        rbt.add(36);
        //       rbt.printTree();

//        var n = rbt.findNode(54);
//        rbt.remove(15);
//        rbt.remove(12);
        rbt.remove(10);
        rbt.printTree();
        int k = 1;

    }
}
