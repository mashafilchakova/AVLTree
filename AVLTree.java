import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Masha
 */
public class AVLTree<T extends Comparable<T>> implements Set<T> {

    public class Node<T> {

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
    }

    public class TreeIterator implements Iterator<T> {

        private ArrayList<T> list = new ArrayList<>();
        private int index = -1;

        public TreeIterator() {
            addElements(root);
        }

        private void addElements(Node<T> node) {
            if (node == null) {
                return;
            }
            Node<T> leftNode = node.getLeft();
            Node<T> rightNode = node.getRight();
            list.add(node.value);
            if (leftNode != null) {
                addElements(leftNode);
            }
            if (rightNode != null) {
                addElements(rightNode);
            }
        }

        @Override
        public boolean hasNext() {
            return index < list.size() - 1;
        }

        @Override
        public T next() {
            index++;
            return (list.get(index));
        }

    }

    private Node<T> root;
    private int size = 0;
    private boolean added;
    private boolean removed;

    private int getHeight(Node<T> node) {
        return node == null ? 0 : node.height;
    }

    private int bFactor(Node<T> node) {
        return getHeight(node.right) - getHeight(node.left);
    }

    private void fixHeight(Node<T> node) {
        int rightHeight = getHeight(node.right);
        int leftHeight = getHeight(node.left);
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
        if (bFactor(node) == 2) {
            if (bFactor(node.right) < 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        if (bFactor(node) == -2) {
            if (bFactor(node.left) > 0) {
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
        if (o.getClass() != root.value.getClass()) {
            return false;
        }
        return find((T) o, root) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        addElements(root, array, 0);
        return array;
    }

    private int addElements(Node<T> node, Object[] array, int index) {
        if (node == null) {
            return 0;
        }
        Node<T> leftNode = node.getLeft();
        Node<T> rightNode = node.getRight();
        array[index] = node.value;
        if (leftNode != null) {
            index = addElements(leftNode, array, index + 1);
        }
        if (rightNode != null) {
            index = addElements(rightNode, array, index + 1);
        }
        return index;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            T[] array = (Object)a.getClass() == (Object)Object[].class ?
                    (T[]) new Object[size] :
                    (T[]) Array.newInstance(a.getClass().getComponentType(), size);
            addElements(root, array, 0);
            return array;
        }
        addElements(root, a, 0);
        return a;
    }

    @Override
    public boolean add(T value) {
        if (root == null) {
            root = new Node(value);
            added = true;
            size++;
            return added;
        }
        root = add(value, root);
        if (added == true) {
            size++;
        }
        return added;
    }

    private Node<T> add(T value, Node<T> node) {
        if (node == null) {
            added = true;
            return new Node(value);
        }
        int comparison = value.compareTo(node.value);
        if (comparison == 0) {
            added = false;
            return balance(node);
        } else if (comparison < 0) {
            node.left = add(value, node.left);
        } else {
            node.right = add(value, node.right);
        }
        return balance(node);
    }

    @Override
    public boolean remove(Object o) {
        if (root == null) {
            return false;
        }
        if (o.getClass() != root.value.getClass()) {
            return false;
        }
        removed = false;
        root = remove((T) o, root);
        if (removed == true) {
            size--;
        }
        return removed;
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
            removed = true;
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
        return balance(node);
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
            Object o = it.next();
            if (o.getClass() != root.value.getClass()) {
                return false;
            }
            if (add((T) o)) {
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
            if (o.getClass() != root.value.getClass()) {
                return false;
            }
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
