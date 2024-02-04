package cse214hw2;

import java.util.ArrayList;
import java.util.List;

public class DynamicIntegerSet implements DynamicSet {
    Node root;

    public DynamicIntegerSet() {
    }

    public static class Node implements PrintableNode {
        Integer data;
        Node left, right;

        Node(int x) {
            this(x, null, null);
        }

        Node(int x, Node left, Node right) {
            this.data = x;
            this.left = left;
            this.right = right;
        }

        @Override
        public String getValueAsString() {
            return data.toString();
        }

        @Override
        public PrintableNode getLeft() {
            return left;
        }

        @Override
        public PrintableNode getRight() {
            return right;
        }
    }

    // this method must be there exactly in this form
    public Node root() {
        return this.root;
    }

    // rest of your code for this class, including the size, contains, add, and remove methods
    @Override
    public int size() {
        return 1 + sizeHelper(root);
    }

    public int sizeHelper(Node node) {
        int size = 0;

        if (node == null) {
            return 0;
        } else {
            if (node.left != null) {
                size += sizeHelper(node.left) + 1;
            }

            if (node.right != null) {
                size += sizeHelper(node.right) + 1;
            }
        }

        return size;
    }

    @Override
    public boolean contains(Integer x) {
        Node temp = root;
        boolean check = false;
        if (root == null) {
            return false;
        } else if (x.equals(root.data)) {
            return true;
        } else {
            while (!check) {
                if (x < temp.data) {
                    temp = temp.left;
                    if (temp != null && x.equals(temp.data)) {
                        return true;
                    }
                    if (temp == null) {
                        return false;
                    }
                } else if (x > temp.data) {
                    temp = temp.right;
                    if (temp != null && x.equals(temp.data)) {
                        return true;
                    }
                    if (temp == null) {
                        return false;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(Integer x) {
        Node data = new Node(x);
        Node temp;
        boolean check = false;
        if (root == null) {
            root = data;
        } else if (contains(x)) {
            return false;
        } else {
            temp = root;
            while (!check) {
                if (x > temp.data) {
                    if (temp.right == null) {
                        check = true;
                        temp.right = data;
                    }
                    temp = temp.right;
                } else if (x < temp.data) {
                    if (temp.left == null) {
                        check = true;
                        temp.left = data;
                    }
                    temp = temp.left;
                }
            }
            return true;
        }
        return true;
    }

    @Override
    public boolean remove(Integer x) {
        Node temp = root;
        Node parent = null;
        if (root == null) {
            return false;
        } else {
            if (contains(x)) {
                while (!(temp.data.equals(x))) {
                    if (x < temp.data) {
                        parent = temp;
                        temp = temp.left;
                    } else {
                        parent = temp;
                        temp = temp.right;
                    }
                }

                if (temp == root && temp.left == null) {
                    root = root.right;
                } else if (temp != root && temp.left == null) {
                    assert parent != null;
                    if (parent.right == temp) {
                        parent.right = temp.right;
                    } else {
                        parent.left = temp.right;
                    }
                } else {
                    temp.data = getRightmostData(temp.left);
                    temp.left = removeRightMost(temp.left);
                }
                return true;
            }
        }
        return false;
    }

    public int getRightmostData(Node node) {
        if (node.right == null) {
            return node.data;
        } else {
            return getRightmostData(node.right);
        }
    }

    public Node removeRightMost(Node node) {
        if (node.right == null) return node.left;
        else {
            removeRightMost(node.right);
            return node;
        }
    }

    public static void printTree(PrintableNode node) {
        List<List<String>> lines = new ArrayList<>();
        List<PrintableNode> level = new ArrayList<>();
        List<PrintableNode> next = new ArrayList<>();

        level.add(node);
        int nn = 1;
        int widest = 0;
        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getValueAsString();
                    line.add(aa);
                    if (aa.length() > widest)
                        widest = aa.length();
                    next.add(n.getLeft());
                    next.add(n.getRight());
                    if (n.getLeft() != null)
                        nn++;
                    if (n.getRight() != null)
                        nn++;
                }
            }
            if (widest % 2 == 1)
                widest++;
            lines.add(line);
            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (String f : line) {
                if (f == null) f = "";
                final float a = perpiece / 2f - f.length() / 2f;
                int gap1 = (int) Math.ceil(a);
                int gap2 = (int) Math.floor(a);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            perpiece /= 2;
        }
    }
}