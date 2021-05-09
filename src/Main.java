import redBlackTree.RedBlackTree;
import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        System.out.println("Лабораторна робота 3");
        System.out.println("Дослідження бінарних дерев пошуку");
        System.out.println("Червоно-чорне дерево");

        RedBlackTree<Integer> rbt = new RedBlackTree<>(14);
        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            rbt.add(random.nextInt(100000));
        }
        ArrayList<Long> measures = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            long current = System.currentTimeMillis();
            rbt.add(random.nextInt());
            long result = System.currentTimeMillis() - current;
            measures.add(result);
        }
        System.out.println("Час вставки:");
        stat(measures);

        measures = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            long current = System.currentTimeMillis();
            try {
                rbt.find(i);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
            long result = System.currentTimeMillis() - current;
            measures.add(result);
        }
        System.out.println("Час пошуку:");
        stat(measures);

        measures = new ArrayList<>();
        System.out.println("Час видалення:");
        try {
            for (int i = 0; i < 100000; i++) {
                long current = System.currentTimeMillis();
                rbt.remove(i);
                long result = System.currentTimeMillis() - current;
                measures.add(result);
            }
            stat(measures);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void stat(ArrayList<Long> measures){
        long max = measures.get(0);
        long min = measures.get(0);
        double average = 0;
        long sum = 0;
        for (long i : measures) {
            sum += i;
            if (i < min) {
                min = i;
                continue;
            }
            if (i > max) {
                max = i;
            }
        }
        average = sum / measures.size();
        System.out.println("Максимальний час: " + max);
        System.out.println("Мінімальний час: " + min);
        System.out.println("Середній час: " + average + "\n");
    }
}
