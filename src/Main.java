import redBlackTree.RedBlackTree;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторна робота 3");
        System.out.println("Дослідження бінарних дерев пошуку");
        System.out.println("Червоно-чорне дерево");

        RedBlackTree<Integer> rbt = new RedBlackTree<>(5);
        ArrayList<Long> measures = new ArrayList<>();

        for (int i = 0; i < 1000000; i++) {
            long current = System.nanoTime();
            rbt.add(i);
            long result = System.nanoTime() - current;
            measures.add(result);
        }
        System.out.println("Час вставки:");
        stat(measures);

        measures = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            long current = System.nanoTime();
            try {
                rbt.find(i);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            long result = System.nanoTime() - current;
            measures.add(result);
        }
        System.out.println("Час пошуку:");
        stat(measures);

        measures = new ArrayList<>();
        System.out.println("Час видалення:");
        try {
            for (int i = 0; i < 1000000; i++) {
                long current = System.nanoTime();
                rbt.remove(i);
                long result = System.nanoTime() - current;
                measures.add(result);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        stat(measures);
    }

    public static void stat(ArrayList<Long> measures){
        long max = measures.get(0);
        long min = measures.get(0);
        for (long i : measures) {
            if (i < min) {
                min = i;
                continue;
            }
            if (i > max) {
                max = i;
            }
        }
        System.out.println("Максимальний час: " + max);
        System.out.println("Мінімальний час: " + min +  "\n");
    }
}
