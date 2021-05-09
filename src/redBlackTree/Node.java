package redBlackTree;

class Node<E extends Comparable<E>> {
    private E value;
    private boolean isRed;
    private Node<E> left;
    private Node<E> right;
    private Node<E> parent;

    public Node(E value, boolean isRed) {
        this.value = value;
        this.isRed = isRed;
        if (isRed) {
            left = new Node<>(null, false);
            right = new Node<>(null, false);
            left.setParent(this);
            right.setParent(this);
        }
    }

    public E getValue() { return value; }

    public void setValue(E value) { this.value = value; }

    public void changeColor() { isRed = !isRed; }

    public void setRed(){ isRed = true; }

    public void setBlack(){ isRed = false; }

    public boolean isNodeRed() { return isRed; }

    public Node<E> getLeft() { return left; }

    public void setLeft(Node<E> left) { this.left = left; }

    public Node<E> getRight() { return right; }

    public void setRight(Node<E> right) { this.right = right; }

    public Node<E> getParent() { return parent; }

    public void setParent(Node<E> parent) { this.parent = parent; }

    public Node<E> getUncle() {
        Node<E> gp = getGrandparent();
        if (gp == null) { return null; }
        if (parent == gp.left) { return gp.right; }
        else { return gp.left; }
    }

    public Node<E> getGrandparent() { return parent.getParent(); }
}
