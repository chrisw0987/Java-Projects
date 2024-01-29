package cse214hw3;

import java.util.ArrayList;

public class Node<E extends Comparable<E>> {
        public int numSize;
        public boolean leaf;
        public ArrayList<E> values;
        public ArrayList<Node<E>> children ;
        public Node(int minimumDegree) {
            this.leaf = true;
            this.numSize = 0;
            this.values = new ArrayList<>();
            this.children = new ArrayList<>();
        }
        @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i = 0; i < numSize; i++) {
            sb.append(values.get(i) + ", ");
        }
        if (numSize > 0) {
            sb.deleteCharAt(sb.length() - 2);
        }
        sb.append(" ]");
        return sb.toString();
    }
}
