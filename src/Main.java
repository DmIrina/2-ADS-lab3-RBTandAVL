import avlTree.AVLTree;
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

        rbt.remove(10);
        rbt.printTree();

        AVLTree<Integer> avl = new AVLTree<>(17);
        avl.add(12);
        avl.add(38);
        avl.add(3);
        avl.add(49);
        avl.add(19);
        avl.add(100);
        avl.add(1);
        avl.add(29);
        avl.add(46);
        avl.delete(17);
        avl.delete(38);


        int k =1;
    }
}
