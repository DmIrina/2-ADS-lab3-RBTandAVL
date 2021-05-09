package avlTree;

public class Node <E extends Comparable<E>> {
    private E value;
    private Node<E> left;
    private Node<E> right;
    private int height;

    public Node(E value) {
        this.value = value;
        this.height = 0;
    }

    public E getValue() {
        return value;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    public int height() {
        return this.height;
    }

    public void setHeight(int height){
        this.height = height;
    }
}
