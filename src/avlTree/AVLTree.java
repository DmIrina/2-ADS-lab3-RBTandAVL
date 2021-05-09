package avlTree;

public class AVLTree <E extends Comparable<E>> {
    private Node<E> root;

    public AVLTree(E value) {
        this.root = new Node<>(value);
    }

    private int getHeight(Node<E> node){
        return node != null ? node.height() : 0;
    }

    private void fixHeight(Node<E> node){
        int hl = getHeight(node.getLeft());
        int hr = getHeight(node.getRight());
        node.setHeight(Math.max(hl, hr) + 1);
    }

    private int getBFactor(Node<E> node){
        return getHeight(node.getRight()) - getHeight(node.getLeft());
    }

    private Node<E> rotateToRight(Node<E> node){
        Node<E> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);
        fixHeight(node);
        fixHeight(left);
        return left;
    }

    private Node<E> rotateToLeft(Node<E> node){
        Node<E> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);
        fixHeight(node);
        fixHeight(right);
        return right;
    }

    private Node<E> balance(Node<E> node){
        fixHeight(node);
        if (getBFactor(node) == 2){
            if (getBFactor(node.getRight()) < 0){
                node.setRight(rotateToRight(node.getRight()));
            }
            return rotateToLeft(node);
        }
        if (getBFactor(node) == -2){
            if (getBFactor(node.getLeft()) > 0){
                node.setLeft(rotateToLeft(node.getLeft()));
            }
            return rotateToRight(node);
        }
        return node;
    }

    public void add(E value){
        insert(root, value);
    }

    private Node<E> insert(Node<E> current, E value){
        if (current == null){
            return new Node<>(value);
        }
        Comparable<E> currentValue = current.getValue();
        if (value.compareTo((E) currentValue) < 0){
            current.setLeft(insert(current.getLeft(), value));
        } else {
            current.setRight(insert(current.getRight(), value));
        }
        return balance(current);
    }

    private Node<E> findMin(Node<E> startNode){
        if (startNode.getLeft() != null) {
            return findMin(startNode.getLeft());
        }
        return startNode;
    }

    private Node<E> removeMin(Node<E> node){
        if (node.getLeft() == null){
            return node.getRight();
        }
        node.setLeft(removeMin(node.getLeft()));
        return balance(node);
    }

    public void delete(E value){
        remove(root, value);
    }

    private Node<E> remove(Node<E> current, E value){
        if (current == null){
            return null;
        }
        Comparable<E> currentValue = current.getValue();
        if (value.compareTo((E) currentValue) < 0){
            current.setLeft(remove(current.getLeft(), value));
        } else if(value.compareTo((E) currentValue) > 0) {
            current.setRight(remove(current.getRight(), value));
        } else {
            // node is found
            Node<E> left = current.getLeft();
            Node<E> right = current.getRight();
            if (right == null){
                return left;
            }
            Node<E> min = findMin(right);
            min.setRight(removeMin(right));
            min.setLeft(left);
            return balance(min);
        }
        return balance(current);
    }

    public E find(E findElement) {
        if (this.root == null) {
            return null;
        }
        return findFrom(this.root, findElement);
    }

    private E findFrom(Node<E> start, E findElement) {
        if (start == null) {
            return null;
        }
        switch (findElement.compareTo((E) start.getValue())) {
            case 0:
                return (E) start.getValue();
            case -1:
                if (start.getLeft() != null)
                    return findFrom(start.getLeft(), findElement);
            case 1:
                if (start.getRight() != null)
                    return findFrom(start.getRight(), findElement);
        }
        return null;
    }
}
