package avltree;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 *
 * @author Masha
 */
public class AVLTree<T extends Comparable<T>> implements Set<T> {

    private class Node<T> {

        private T value;
        private int height;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }

        public Node<T> getLeft() {
            return left;
        }

        public Node<T> getRight() {
            return right;
        }

        public int getHeight() {
            return height;
        }

        public T getValue() {
            return value;
        }

        private int bFactor() {
            int lHeight = left == null ? 0 : left.getHeight();
            int rHeight = right == null ? 0 : right.getHeight();
            return rHeight - lHeight;
        }
    }

    public class TreeIterator implements Iterator<T> {

        private final Object[] list;
        private int index = 0;

        public TreeIterator() {
            list = new Object[size];
            addElements(root);
            index = -1;
        }

        private void addElements(Node<T> node) {
            if (node == null) {
                return;
            }
            Node<T> leftNode = node.getLeft();
            if (leftNode != null) {
                addElements(leftNode);
            }
            list[index] = node.value;
            index++;
            Node<T> rightNode = node.getRight();
            if (rightNode != null) {
                addElements(rightNode);
            }
        }

        @Override
        public boolean hasNext() {
            return index < list.length - 1;
        }

        @Override
        public T next() {
            index++;
            return ((T) list[index]);
        }

    }

    private Node<T> root;
    private int size = 0;

    private void fixHeight(Node<T> node) {
        int rightHeight = node.right == null ? 0 : node.right.height;
        int leftHeight = node.left == null ? 0 : node.left.height;
        node.height = (leftHeight > rightHeight ? leftHeight : rightHeight) + 1;
    }

    private Node<T> rotateRight(Node<T> node) {
        Node<T> _node = node.left;
        node.left = _node.right;
        _node.right = node;
        fixHeight(node);
        fixHeight(_node);
        return _node;
    }

    private Node<T> rotateLeft(Node<T> node) {
        Node<T> _node = node.right;
        node.right = _node.left;
        _node.left = node;
        fixHeight(node);
        fixHeight(_node);
        return _node;
    }

    private Node<T> balance(Node<T> node) {
        fixHeight(node);
        if (node.bFactor() == 2) {
            if (node.right.bFactor() < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        if (node.bFactor() == -2) {
            if (node.left.bFactor() > 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<T> find(T value, Node<T> node) {
        if (node == null) {
            return null;
        }
        int comparison = value.compareTo(node.value);
        if (comparison == 0) {
            return node;
        } else if (comparison > 0) {
            return find(value, node.right);
        } else {
            return find(value, node.left);
        }
    }

    @Override
    public boolean contains(Object o) {
        if (root == null) {
            return false;
        }
        T t = (T) o;
        Node<T> closest = find(t, root);
        return closest != null && closest.value.compareTo(t) == 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        Iterator it = new TreeIterator();
        int i = 0;
        while (it.hasNext()) {
            array[i] = it.next();
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            T[] array = (Object) a.getClass() == (Object) Object[].class
                    ? (T[]) new Object[size]
                    : (T[]) Array.newInstance(a.getClass().getComponentType(), size);
            Iterator it = new TreeIterator();
            int i = 0;
            while (it.hasNext()) {
                array[i] = (T) it.next();
                i++;
            }
            return array;
        }
        Iterator it = new TreeIterator();
        int i = 0;
        while (it.hasNext()) {
            a[i] = (T) it.next();
            i++;
        }
        return a;
    }

    @Override
    public boolean add(T value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        if (contains(value)) {
            return false;
        }
        root = add(value, root);
        if (contains(value)) {
            size++;
            return true;
        }
        return false;
    }

    private Node<T> add(T value, Node<T> node) {
        if (node == null) {
            return new Node(value);
        }
        int comparison = value.compareTo(node.value);
        if (comparison == 0) {
            return balance(node);
        } else if (comparison < 0) {
            node.left = add(value, node.left);
        } else {
            node.right = add(value, node.right);
        }
        return node;
    }

    @Override
    public boolean remove(Object o) {
        if (root == null) {
            return false;
        }
        if (o.getClass() != root.value.getClass()) {
            return false;
        }
        if (!contains((T) o)) {
            return false;
        }
        root = remove((T) o, root);
        if (!contains((T) o)) {
            size--;
            return true;
        }
        return false;
    }

    private Node<T> findMin(Node<T> node) {
        return node.left != null ? findMin(node.left) : node;
    }

    private Node<T> removeMin(Node<T> node) {
        if (node.left == null) {
            return node.right;
        }
        node.left = removeMin(node.left);
        return balance(node);
    }

    private Node<T> remove(T value, Node<T> node) {
        if (node == null) {
            return null;
        }
        int comparison = value.compareTo(node.value);
        if (comparison < 0) {
            node.left = remove(value, node.left);
        } else if (comparison > 0) {
            node.right = remove(value, node.right);
        } else {
            Node<T> leftNode = node.left;
            Node<T> rightNode = node.right;
            if (rightNode == null) {
                return leftNode;
            }
            Node<T> minNode = findMin(rightNode);
            minNode.right = removeMin(rightNode);
            minNode.left = leftNode;
            return balance(minNode);
        }
        return node;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (!contains(it.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean changed = false;
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (add((T) it.next())) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        AVLTree<T> newTree = new AVLTree();
        boolean changed = false;
        Iterator it = c.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            if (contains(o)) {
                if (!newTree.add((T) o)) {
                    changed = true;
                }
            } else {
                changed = true;
            }
        }
        root = newTree.root;
        size = newTree.size;
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        Iterator it = c.iterator();
        while (it.hasNext()) {
            if (remove(it.next())) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    public boolean isBalanced() {
        return isBalanced(root) != -1;
    }

    private int isBalanced(Node<T> node) {
        if (node == null) {
            return 1;
        }
        int leftHeight = isBalanced(node.left);
        int rightHeight = isBalanced(node.right);
        return Math.abs(leftHeight - rightHeight) <= 1 ? Math.max(leftHeight, rightHeight) : -1;
    }
}
