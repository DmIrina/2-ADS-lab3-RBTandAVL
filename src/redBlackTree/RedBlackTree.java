package redBlackTree;

public class RedBlackTree<E extends Comparable<E>> {
    private Node<E> root;

    public RedBlackTree(E value) {
        this.root = new Node<>(value, true);
        this.root.changeColor();
    }

    public void add(E value) {
        Node<E> added = new Node<>(value, true);
        addToTree(added);
        case1(added);
    }

    private void case1(Node<E> node) {
        if (node.getParent() == null) {       //tree is empty
            node.setBlack();
            return;
        }
        case2(node);
    }

    private void case2(Node<E> node) {
        if (!node.getParent().isNodeRed()) {  // parent is black
            return;
        } else {
            case3(node);
        }
    }

    private void case3(Node<E> node) {
        Node<E> gp = node.getGrandparent();
        Node<E> p = node.getParent();
        Node<E> u = node.getUncle();
        if (u != null && u.isNodeRed()) {     //parent is red, uncle is red
            p.changeColor();
            u.changeColor();
            gp.changeColor();
            case1(gp);
        } else {
            case4(node);
        }
    }

    private void case4(Node<E> node) {        //parent is red, uncle is black
        Node<E> gp = node.getGrandparent();
        Node<E> p = node.getParent();
        Node<E> u = node.getUncle();

        //parent is left child, current is right child - "triangle"
        if (p == gp.getLeft() && node == p.getRight()) {
            rotateParentToLeft(node, p, u, gp);
            case5(node.getLeft());
            return;
        }

        //parent is right child, current is left child - "triangle"
        if (p == gp.getRight() && node == p.getLeft()) {
            rotateParentToRight(node, p, u, gp);
            case5(node.getRight());
            return;
        }

        case5(node);
    }

    // c - current, p - parent, u - uncle, gp - grandparent

    private void case5(Node<E> node) {
        Node<E> gp = node.getGrandparent();
        Node<E> p = node.getParent();
        Node<E> u = node.getUncle();

        p.setBlack();
        gp.changeColor();

        //parent is left child, current is left child - "line"
        if (p == gp.getLeft() && node == p.getLeft()) {
            rotateGrandparentToRight(node, p, u, gp);
        }

        //parent is right child, current is right child - "line"
        if (p == gp.getRight() && node == p.getRight()) {
            rotateGrandparentToLeft(node, p, u, gp);
        }
    }

    private void addToTree(Node<E> added) {
        E value = added.getValue();
        Node<E> current = this.root;
        while (true) {                                      // 1) add red node in binary tree
            Comparable<E> currentValue = current.getValue();
            if (currentValue.compareTo(value) > 0) {        // if added element is smaller than current
                if (current.getLeft().getValue() == null) { // there are no nodes
                    added.setParent(current);
                    current.setLeft(added);
                    return;
                }
                current = current.getLeft();
            } else if (currentValue.compareTo(value) < 0) {  // if added element is bigger than current
                if (current.getRight().getValue() == null) { // there are no nodes
                    added.setParent(current);
                    current.setRight(added);
                    return;
                }
                current = current.getRight();
            } else {
                current.setValue(value);
                return;
            }
        }
    }

    // c - current, p - parent, u - uncle, gp - grandparent

    private void rotateParentToLeft(Node<E> node, Node<E> p, Node<E> u, Node<E> gp) {
        if (p == null) {
            return;
        }
        Node<E> nodeLeft = node.getLeft();
        node.setParent(gp);
        gp.setLeft(node);
        node.setLeft(p);
        p.setParent(node);
        p.setRight(nodeLeft);
    }

    private void rotateParentToRight(Node<E> node, Node<E> p, Node<E> u, Node<E> gp) {
        if (p == null) {
            return;
        }
        Node<E> nodeRight = node.getRight();
        node.setParent(gp);
        gp.setRight(node);
        node.setRight(p);
        p.setParent(node);
        p.setLeft(nodeRight);
    }

    private void rotateGrandparentToRight(Node<E> node, Node<E> p, Node<E> u, Node<E> gp) {
        Node<E> pRight = p.getRight();
        Node<E> ggp = gp.getParent();

        p.setParent(ggp);
        if (ggp != null) {
            if (ggp.getLeft() == gp) {
                ggp.setLeft(p);
            } else {
                ggp.setRight(p);
            }
        } else {
            root = p;
        }
        gp.setLeft(pRight);
        pRight.setParent(gp);
        gp.setParent(p);
        p.setRight(gp);
    }

    private void rotateGrandparentToLeft(Node<E> node, Node<E> p, Node<E> u, Node<E> gp) {
        Node<E> pLeft = p.getLeft();
        Node<E> ggp = gp.getParent();

        p.setParent(ggp);
        if (ggp != null) {
            if (ggp.getRight() == gp) {
                ggp.setRight(p);
            } else {
                ggp.setLeft(p);
            }
        } else {
            root = p;
        }
        gp.setRight(pLeft);
        pLeft.setParent(gp);
        gp.setParent(p);
        p.setLeft(gp);
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
                if (start.getLeft().getValue() != null)
                return findFrom(start.getLeft(), findElement);
            case 1:
                if (start.getRight().getValue() != null)
                return findFrom(start.getRight(), findElement);
        }
        return null;
    }


    private Node<E> findNode(E findElement) {
        if (this.root == null) {
            return null;
        }
        return findNodeFrom(this.root, findElement);
    }

    private Node<E> findNodeFrom(Node<E> start, E findElement) {
        if (start == null) {
            return null;
        }
        Comparable<E> currentValue = start.getValue();
        if (findElement.compareTo((E) currentValue) < 0){
            if (start.getLeft().getValue() != null)
            return findNodeFrom(start.getLeft(), findElement);
        } else if(findElement.compareTo((E) currentValue) > 0) {
            if (start.getRight().getValue() != null)
            return findNodeFrom(start.getRight(), findElement);
        } else {
            return start;
        }
        return null;
    }

    private int countChildren(Node<E> delNode) {
        int count = 0;
        if (delNode.getLeft().getValue() != null) {
            count++;
        }
        if (delNode.getRight().getValue() != null) {
            count++;
        }
        return count;
    }

    public void remove(E value) {
        Node<E> delNode = findNode(value);
        int count = countChildren(delNode);

        switch (count) {
            case 0:                         // no children
            case 1:                         // 1 child
                removeA01(delNode);
                break;
            case 2:                         // 2 children

                if (delNode == root) {      // deleted node is root
                    removeR2(delNode);
                    break;
                }

                if (delNode.isNodeRed()) {  // deleted node is red
                    removeR2(delNode);
                } else {
                    removeB2(delNode);
                }
                break;
        }
    }

    private void deleteApplyA1(Node<E> delNode) {

        // deleted node is left child && has left child
        if (delNode.getParent().getLeft() == delNode && delNode.getLeft().getValue() != null) {
            delNode.getParent().setLeft(delNode.getLeft());
            delNode.getLeft().setParent(delNode.getParent());
            return;
        }

        // deleted node is left child && has right child
        if (delNode.getParent().getLeft() == delNode && delNode.getRight().getValue() != null) {
            delNode.getParent().setLeft(delNode.getRight());
            delNode.getRight().setParent(delNode.getParent());
            return;
        }

        // deleted node is right child && has left child
        if (delNode.getParent().getRight() == delNode && delNode.getLeft().getValue() != null) {
            delNode.getParent().setRight(delNode.getLeft());
            delNode.getLeft().setParent(delNode.getParent());
            return;
        }

        // deleted node is right child && has right child
        if (delNode.getParent().getRight() == delNode && delNode.getRight().getValue() != null) {
            delNode.getParent().setRight(delNode.getRight());
            delNode.getRight().setParent(delNode.getParent());
            return;
        }
    }

    private void removeR2(Node<E> delNode) {                 // Deleted node is red && has 2 children
        Node<E> leftBiggest = findBiggest(delNode.getLeft());
        delNode.setValue(leftBiggest.getValue());
        leftBiggest.getParent().setRight(leftBiggest.getLeft());
        leftBiggest.getLeft().setParent(leftBiggest.getParent());
    }

    private void removeB2(Node<E> delNode) {                 // Deleted node is black && has 2 children
        Node<E> leftBiggest = findBiggest(delNode.getLeft());
        delNode.setValue(leftBiggest.getValue());
        removeA01(leftBiggest);                              // leftBiggest has 1 or 0 children
    }


    private Node<E> findBiggest(Node<E> startNode) {
        if (startNode.getRight().getValue() == null) {
            return startNode;
        } else {
            return findBiggest(startNode.getRight());
        }
    }

    private boolean isLeftChild(Node<E> node) {
        return node == node.getParent().getLeft();
    }

    private boolean hasLeftChild(Node<E> node) {
        return (node.getLeft().getValue() != null);
    }

    private Node<E> getBrother(Node<E> node, boolean isLeft) {
        return isLeft ? node.getParent().getRight() : node.getParent().getLeft();
    }

    private Node<E> getChild(Node<E> node, boolean isLeft) {
        return isLeft ? node.getLeft() : node.getRight();
    }

    private Node<E> getFarNephew(Node<E> brother, boolean isLeft) {
        return isLeft ? brother.getRight() : brother.getLeft();
    }

    private Node<E> getNearNephew(Node<E> brother, boolean isLeft) {
        return isLeft ? brother.getLeft() : brother.getRight();
    }

    private Node<E> deleteApplyA0(Node<E> delNode, boolean isLeft) {
        if (isLeft) {
            delNode.getParent().setLeft(delNode.getRight());
        } else {
            delNode.getParent().setRight(delNode.getRight());
        }
        delNode.getRight().setParent(delNode.getParent());

        return delNode.getRight();
    }

    private void deleteApply(Node<E> node) {
        boolean isLeft = isLeftChild(node);      // deleted Node is left child
        boolean hasLeft = hasLeftChild(node);    // deleted Node has left child
        Node<E> child = getChild(node, hasLeft);
        if (node.isNodeRed()) {
            if (child.getValue() == null) {
                deleteApplyA0(node, isLeft);
            } else {
                deleteApplyA1(node);
            }
            return;
        }
    }

    private Node<E> promote(Node<E> node) {
        Node<E> parent = node.getParent();
        if (parent.getLeft() == node) {
            parent.setLeft(node.getRight());
            parent.getLeft().setParent(parent);
            node.setParent(parent.getParent());

            if (node.getParent().getLeft() == parent) {
                node.getParent().setLeft(node);
            } else {
                node.getParent().setRight(node);
            }

            node.setRight(parent);
            parent.setParent(node);

        } else {
            parent.setRight(node.getLeft());
            parent.getRight().setParent(parent);
            node.setParent(parent.getParent());

            if (node.getParent().getLeft() == parent) {
                node.getParent().setLeft(node);
            } else {
                node.getParent().setRight(node);
            }

            node.setLeft(parent);
            parent.setParent(node);
        }
        return node;
    }

    // node has 0 or 1 child
    private void removeA01(Node<E> node) {       // Deleted node has no children or 1 child
        boolean isLeft = isLeftChild(node);      // deleted Node is left child
        boolean hasLeft = hasLeftChild(node);    // deleted Node has left child
        boolean isBalanced;
        Node<E> child = getChild(node, hasLeft);

        // node is red
        if (node.isNodeRed()) {
            deleteApply(node);
            return;
        }
        // node is black
        // if child is red, recolor the child black
        // delete the node
        if (child.isNodeRed()) {
            child.setBlack();
            deleteApply(node);
            return;
        }

        Node<E> dad = node.getParent();
        Node<E> brother = getBrother(node, isLeft);
        Node<E> farNephew = getFarNephew(brother, isLeft);
        Node<E> nearNephew = getNearNephew(brother, isLeft);

        deleteApply(node);         // deleting node - we don't need it anymore
        node = child;

        // in a loop applying the red-black deletion
        // balancing algorithms until the tree is balanced
        do {
            isBalanced = true;

            if (node != root) {
                if (node.getValue() != null) {
                    dad = node.getParent();
                    isLeft = isLeftChild(node);
                    hasLeft = hasLeftChild(node);
                    child = getChild(node, isLeft);
                    brother = getBrother(node, isLeft);

                    // we need a black brother
                    // if the brother is red -> color the parent red, the brother black, and promote the brother
                    // then go round loop again

                    if (brother.isNodeRed()) {   // brother is red
                        dad.setRed();
                        brother.setBlack();
                        promote(brother);
                        isBalanced = false;
                    } else {                        // brother is black -> get far and near nephews
                        farNephew = getFarNephew(brother, isLeft);
                        nearNephew = getNearNephew(brother, isLeft);

                        // if the far nephew is red,
                        // color it black, color the brother the same as the parent,
                        // color the parent black, and then promote the brother

                        if (farNephew.isNodeRed()) {
                            farNephew.setBlack();
                            if (dad.isNodeRed()) {
                                brother.setRed();
                            } else {
                                brother.setBlack();
                            }
                            dad.setBlack();
                            promote(brother);
                        } else {                        //far nephew is black

                            // if the near nephew is red,
                            // color it the same color as the parent, color the parent black,
                            // promote the nephew twice

                            if (nearNephew.isNodeRed()) {
                                if (dad.isNodeRed()) {
                                    nearNephew.setRed();
                                } else {
                                    nearNephew.setBlack();
                                }
                                dad.setBlack();
                                promote(promote(brother));

                            } else {                    // near nephew is black
                                if (dad.isNodeRed()) {
                                    dad.setBlack();
                                    brother.setRed();
                                } else {
                                    brother.setRed();
                                    node = dad;
                                    isBalanced = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        while (isBalanced);
    }
}
