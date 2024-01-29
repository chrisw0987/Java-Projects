package cse214hw3;

import javax.naming.OperationNotSupportedException;
import java.util.*;
public class BTree <E extends Comparable<E>> {
    private int minimumDegree;
    private final int max;
    private Node<E> root;
    public int count = 0;

    public BTree(int minimumDegree) {
        if (minimumDegree <= 0) {
            throw new java.lang.IllegalArgumentException("Input Number Bigger Than 0");
        }
        this.minimumDegree = minimumDegree;
        this.root = new Node(minimumDegree);
        this.max = 2 * minimumDegree - 1;
    }

    public void add(E element) throws OperationNotSupportedException {
        if (root.numSize == max) {
            splitRoot();
            System.out.println();
            insertNonFull(element, root);
        } else {
            insertNonFull(element, root);
        }
    }

    private void insertNonFull(E v, Node<E> node) {
        int i = node.numSize - 1;
        int index = 0;
        if (node.leaf) {
            while (index < node.values.size() && v.compareTo(node.values.get(index)) > 0) {
                index++;
            }
            if (index >= 0 && index < node.values.size()) {
                node.values.add(node.values.get(node.values.size() - 1));
                for (int j = node.values.size() - 2; j >= index; j--) {
                    node.values.set((j + 1), node.values.get(j));
                }
                node.values.set((index), v);
                node.numSize++;
            } else {
                node.values.add(v);
                node.numSize++;
            }
        } else {
            while (index < node.numSize && v.compareTo(node.values.get(index)) > 0) {
                index++;
            }

            if (node.children.get(index).numSize == 2 * minimumDegree - 1) {
                splitChild(node, node.children.get(index), index);
                if ((v.compareTo(node.values.get(index)) > 0)) {
                    index = index + 1;
                }
            }

            insertNonFull(v, node.children.get(index));
        }
    }

    public void splitChild(Node<E> parent, Node<E> child, int childIndex) {
        int middleIndex = (max / 2);
        E val = child.values.get(middleIndex);
        Node<E> leftNode = new Node<>(minimumDegree);
        Node<E> rightNode = new Node<>(minimumDegree);

        for (int i = 0; i < middleIndex; i++) {
            leftNode.values.add(child.values.get(i));
            leftNode.numSize++;

        }
        for (int j = middleIndex + 1; j < child.numSize; j++) {
            rightNode.values.add(child.values.get(j));
            rightNode.numSize++;

        }

        int newSize = parent.numSize + 1;
        if (childIndex == parent.children.size() - 1) {
            parent.children.add(parent.children.get(newSize - 1));
            for (int k = newSize - 2; k > childIndex; k--) {
                parent.children.set(k + 1, parent.children.get(k));
            }
            parent.children.set(childIndex, leftNode);
            parent.children.set(childIndex + 1, rightNode);
        } else {
            parent.children.set(childIndex, leftNode);
            parent.children.add(rightNode);
        }


        if (parent.numSize + 1 <= parent.values.size()) {
            parent.values.set(childIndex, val);
        } else {
            parent.values.add(val);
        }

        parent.numSize++;
    }


    public void splitRoot() {
        int middleIndex = (max / 2);
        Node<E> test = root;
        Node<E> leftNode = new Node<>(minimumDegree);
        Node<E> rightNode = new Node<>(minimumDegree);


        for (int i = 0; i < middleIndex; i++) {
            leftNode.values.add(i, root.values.get(i));
            leftNode.numSize++;
        }
        for (int j = middleIndex + 1; j < root.numSize; j++) {
            rightNode.values.add(j - middleIndex - 1, root.values.get(j));
            rightNode.numSize++;
        }

        if (!root.leaf) {
            int i = 0;

            for (i = 0; i <= leftNode.numSize; i++) {
                leftNode.children.add(root.children.get(i));
                leftNode.leaf = false;
            }

            for (int j = 0; j <= rightNode.numSize; j++) {
                rightNode.children.add(root.children.get(i + j));
                rightNode.leaf = false;
            }
            root.children.clear();
        }

        root.leaf = false;
        root.numSize = 1;
        root.values.set(0, root.values.get(middleIndex));
        root.children.add(leftNode);
        root.children.add(rightNode);
        count++;
    }

    private Node<E> search(Node<E> x, E value) {
        int i = 0;
        while (i < x.values.size() && value.compareTo(x.values.get(i)) > 0) {
            i++;
        }

        if (i < x.values.size() && value.compareTo(x.values.get(i)) == 0) {
            return x;
        }

        if (x.leaf) {
            return null;
        } else {
            if (i < x.children.size()) {
                return search(x.children.get(i), value);
            } else {
                return search(x.children.get(x.children.size() - 1), value);
            }
        }
    }

    public double find(E element) {
        Node<E> result = search(root, element);
        if (result != null) {
            System.out.print('[');
            for (E value : result.values) {
                System.out.print(value + " ");
            }
            System.out.print(']');
            System.out.print(' ');
            System.out.print(',');
            System.out.print(' ');

        } else {
            System.out.println("ERROR: Value Not Found");
        }
        return 0;
    }

    //EVERYTHING BELOW HERE ARE GIVENS
    public void addAll(Collection<E> elements) throws OperationNotSupportedException {
        for (E e : elements) this.add(e);
    }

    public void show() {
        String nodesep = " ";
        Queue<Node> queue1 = new LinkedList<>();
        Queue<Node> queue2 = new LinkedList<>();
        queue1.add(root); /* root of the tree being added */
        while (true) {
            while (!queue1.isEmpty()) {
                Node node = queue1.poll();
                System.out.printf("%s%s", node.toString(), nodesep);
                if (!node.children.isEmpty())
                    queue2.addAll(node.children);
            }
            System.out.printf("%n");
            if (queue2.isEmpty())
                break;
            else {
                queue1 = queue2;
                queue2 = new LinkedList<>();
            }
        }
    }
}
