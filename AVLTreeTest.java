package avltree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Masha
 */
public class AVLTreeTest {

    public AVLTreeTest() {
    }

    @Test
    public void orderTest() {
        System.out.println("Order test #1");
        AVLTree<Integer> tree = new AVLTree();
        for (int i = 0; i < 10; i++) {
            tree.add((int) (Math.random() * 100));
        }
        Iterator it = tree.iterator();
        Object o1 = null, o2 = null;
        if (it.hasNext()) {
            o1 = it.next();
        }
        while (it.hasNext()) {
            o2 = it.next();
            if ((int) o1 >= (int) o2) {
                fail("Test is not passed");
            }
            o1 = o2;
        }
    }

    /**
     * Test of size method, of class AVLTree.
     */
    @Test
    public void testSize1() {
        System.out.println("Size test #1");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        tree.add(15);
        tree.add(20);
        if (tree.size() == 3) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of size method, of class AVLTree.
     */
    @Test
    public void testSize2() {
        System.out.println("Size test #2");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        tree.add(15);
        tree.add(20);
        tree.remove(20);
        tree.remove(15);
        tree.remove(10);
        if (tree.size() == 0) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of contains method, of class AVLTree.
     */
    @Test
    public void testContains1() {
        System.out.println("Contains test #1");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        tree.add(15);
        tree.add(20);
        if (tree.contains(10)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of contains method, of class AVLTree.
     */
    @Test
    public void testContains2() {
        System.out.println("Contains test #2");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        tree.add(15);
        tree.add(20);
        tree.remove(20);
        if (!tree.contains(20)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of contains method, of class AVLTree.
     */
    @Test
    public void testContains3() {
        System.out.println("Contains test #3");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        tree.add(15);
        tree.add(20);
        if (!tree.contains(50)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    @Test
    public void iterationTest() {
        System.out.println("Iteration test #1");
        AVLTree<Integer> tree = new AVLTree();
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < 10; i++) {
            int value = (int) (Math.random() * 20);
            tree.add(value);
            treeSet.add(value);
        }
        assertArrayEquals(tree.toArray(), treeSet.toArray());
    }

    /**
     * Test of add method, of class AVLTree.
     */
    @Test
    public void testAdd1() {
        System.out.println("Add test #1");
        AVLTree<Integer> tree = new AVLTree();
        boolean res = tree.add(10);
        if (tree.contains(10) && res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of add method, of class AVLTree.
     */
    @Test
    public void testAdd2() {
        System.out.println("Add test #2");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        boolean res = tree.add(10);
        System.out.println("Sdasddas " + tree.contains(10));
        if (tree.contains(10) && !res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of remove method, of class AVLTree.
     */
    @Test
    public void testRemove1() {
        System.out.println("Remove test #1");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        boolean res = tree.remove(10);
        if (!tree.contains(10) && res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of remove method, of class AVLTree.
     */
    @Test
    public void testRemove2() {
        System.out.println("Remove test #2");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(10);
        tree.add(20);
        tree.add(5);
        boolean res = tree.remove(50);
        if (tree.contains(10) && !res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of containsAll method, of class AVLTree.
     */
    @Test
    public void testContainsAll1() {
        System.out.println("ContainsAll test #1");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(10);
        tree1.add(20);
        tree1.add(5);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(5);
        tree2.add(20);
        tree2.add(10);
        if (tree1.containsAll(tree2)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of containsAll method, of class AVLTree.
     */
    @Test
    public void testContainsAll2() {
        System.out.println("ContainsAll test #2");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(10);
        tree1.add(20);
        tree1.add(5);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(3);
        tree2.add(4);
        if (!tree1.containsAll(tree2)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of containsAll method, of class AVLTree.
     */
    @Test
    public void testContainsAll3() {
        System.out.println("ContainsAll test #3");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(10);
        tree1.add(20);
        tree1.add(5);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(5);
        tree2.add(20);
        tree2.add(10);
        tree2.add(120);
        if (!tree1.containsAll(tree2)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of addAll method, of class AVLTree.
     */
    @Test
    public void testAddAll1() {
        System.out.println("AddAll test #1");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(10);
        tree1.add(20);
        tree1.add(5);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(5);
        tree2.add(20);
        tree2.add(10);
        tree2.add(120);
        boolean res = tree1.addAll(tree2);
        if (res && tree1.containsAll(tree2)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of addAll method, of class AVLTree.
     */
    @Test
    public void testAddAll2() {
        System.out.println("AddAll test #2");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(4);
        tree2.add(5);
        tree2.add(6);
        boolean res = tree1.addAll(tree2);
        AVLTree<Integer> tree3 = new AVLTree();
        tree3.add(1);
        tree3.add(2);
        tree3.add(3);
        tree3.add(4);
        tree3.add(5);
        tree3.add(6);
        if (res && tree1.containsAll(tree3)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of addAll method, of class AVLTree.
     */
    @Test
    public void testAddAll3() {
        System.out.println("AddAll test #3");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(1);
        tree2.add(2);
        tree2.add(3);
        boolean res = tree1.addAll(tree2);
        if (!res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of retainAll method, of class AVLTree.
     */
    @Test
    public void testRetainAll1() {
        System.out.println("RetainAll test #1");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(1);
        tree2.add(2);
        tree2.add(3);
        boolean res = tree1.retainAll(tree2);
        if (!res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of retainAll method, of class AVLTree.
     */
    @Test
    public void testRetainAll2() {
        System.out.println("RetainAll test #2");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(4);
        tree2.add(5);
        tree2.add(6);
        boolean res = tree1.retainAll(tree2);
        System.out.println(tree1.isEmpty());
        if (res && tree1.isEmpty()) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of removeAll method, of class AVLTree.
     */
    @Test
    public void testRemoveAll1() {
        System.out.println("RemoveAll test #1");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(4);
        tree2.add(5);
        tree2.add(6);
        boolean res = tree1.removeAll(tree2);
        if (!res) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of removeAll method, of class AVLTree.
     */
    @Test
    public void testRemoveAll2() {
        System.out.println("RemoveAll test #2");
        AVLTree<Integer> tree1 = new AVLTree();
        tree1.add(1);
        tree1.add(2);
        tree1.add(3);
        AVLTree<Integer> tree2 = new AVLTree();
        tree2.add(1);
        tree2.add(2);
        AVLTree<Integer> tree3 = new AVLTree();
        tree3.add(3);
        boolean res = tree1.removeAll(tree2);
        if (res && tree3.containsAll(tree1)) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    /**
     * Test of clear method, of class AVLTree.
     */
    @Test
    public void testClear() {
        System.out.println("Clear test #1");
        AVLTree<Integer> tree = new AVLTree();
        tree.add(1);
        tree.add(10);
        tree.add(100);
        tree.clear();
        if (tree.isEmpty()) {
            System.out.println("Successful");
        } else {
            fail("Test is not passed");
        }
    }

    @Test
    public void randomBalancedTest() {
        AVLTree tree = new AVLTree();
        for (int i = 0; i < 100; i++) {
            tree.clear();
            System.out.println("random balanced test " + (i + 1));
            int n = (int) (Math.random() * 50);
            for (int j = 0; j < n; j++) {
                tree.add((int) (Math.random() * 100));
            }
            for (int j = 0; j < (n + 1) / 3; j++) {
                tree.remove((int) (Math.random() * 100));
            }
            if (tree.isBalanced() == true) {
                System.out.println("tree is balanced");
            } else {
                fail("error! tree is not balanced");
            }
        }
    }

    @Test
    public void randomElementsTest() {
        ArrayList<Integer> list = new ArrayList<>();
        AVLTree tree = new AVLTree();
        for (int i = 0; i < 100; i++) {
            list.clear();
            tree.clear();
            System.out.println("random elements test " + (i + 1));
            int n = (int) (Math.random() * 50);
            for (int j = 0; j < n; j++) {
                int value = (int) (Math.random() * 100);
                if (tree.add(value)) {
                    list.add(value);
                }
            }
            for (int j = 0; j < (n + 1) / 3; j++) {
                int value = (int) (Math.random() * 100);
                if (tree.remove(value)) {
                    list.remove((Object) value);
                }
            }
            if (tree.containsAll(list) && list.containsAll(tree)) {
                System.out.println("tree is OK");
            } else {
                fail("error! tree is not OK");
            }
        }
    }

}
