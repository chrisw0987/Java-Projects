import java.util.ArrayList;
import java.util.List;



public class SortingMethods<E extends Comparable<E>> implements Demonstrable {


    private static final int UPPER_LIMIT_FOR_SHOW = 15;


    StringBuilder steps = new StringBuilder();


    /**
     * DO NOT CHANGE THIS METHOD
     */

    public String show() {

        steps.setLength(steps.length() - 1);

        return steps.toString();

    }


    /**
     * This is a bottom-up implementation of the mergesort algorithm where multiple small sublists of the same size
     * <p>
     * are merged in a single pass. The demonstration of this algorithm is done by showing each stage of the merge on
     * <p>
     * sublists of the same size (except possibly the very last sublist, if the input list has odd number of elements).
     * <p>
     * For example, if the input is [5, 2, 4, 6, 7, 1, 3], a call to show() returns the string:
     *
     * <p>
     * <p>
     * [5, 2, 4, 6, 7, 1, 3]
     * <p>
     * [2, 5, 4, 6, 1, 7, 3]
     * <p>
     * [2, 4, 5, 6, 1, 3, 7]
     * <p>
     * [1, 2, 3, 4, 5, 6, 7]
     *
     * </p>
     * <p>
     * The first line shows the input, the second line demonstrates the pairwise merges creating sorted sublists of
     * <p>
     * size 2 (except the very last sublist, which is [3]), the third line demonstrates merges of size-2 lists together
     * <p>
     * to create [2, 4, 5, 6] and [1, 3, 7] (again, the very last sublist was not a pair because the input has an odd
     * <p>
     * number of elements), and the fourth line merges the size-4 sublists together, which is the sorted list.
     *
     * @param elements the input list to be sorted
     */

    public void mergeSort(List<E> elements) {
        steps.setLength(0);
        List<E> newList = new ArrayList<>(elements.size());

        if (elements.size() < UPPER_LIMIT_FOR_SHOW) {
            steps.append(elements.toString()).append('\n');
        }

        for (int size = 1; size < elements.size(); size = size * 2) {
            for (int leftList = 0; leftList < elements.size() - size; leftList += size * 2) {
                int midIndex = leftList + size - 1;
                int rightList = Math.min(leftList + size * 2 - 1, elements.size() - 1);
                merge(elements, newList, leftList, midIndex, rightList);
            }

            if (elements.size() < UPPER_LIMIT_FOR_SHOW) {
                steps.append(elements.toString()).append('\n');
            }
        }
    }

    private void merge(List<E> elements, List<E> newList, int leftList, int midIndex, int rightList) {
        int i = 0;
        int j = midIndex - leftList + 1;
        int k = leftList;
        List<E> combined = new ArrayList<>(elements.subList(leftList, rightList + 1));


        while (i < (midIndex - leftList + 1) && j < (rightList - leftList + 1)) {
            if (combined.get(i).compareTo(combined.get(j)) <= 0) {
                elements.set(k++, combined.get(i++));
            }
            else {
                elements.set(k++, combined.get(j++));
            }
        }

        while (i < (midIndex - leftList + 1)) {
            elements.set(k++, combined.get(i++));
        }

        while (j < (rightList - leftList + 1)) {
            elements.set(k++, combined.get(j++));
        }
    }


            /**

             * This is an implementation of the insertion sort algorithm. The steps are demonstrated by adding a line to the

             * string representation whenever the unsorted portion of the input list decreases in size by 1. For example, if

             * the input is [8, 3, 15, 0, 9], then show() returns the string:

             * <p>

             * [8, 3, 15, 0, 9]

             * [3, 8, 15, 0, 9]

             * [3, 8, 15, 0, 9]

             * [0, 3, 8, 15, 9]

             * [0, 3, 8, 9, 15]

             * </p>

             *

             * @param elements the input list to be sorted

             */

            public void insertionSort(List<E> elements) {
                steps.setLength(0);
                if (elements.size() <= UPPER_LIMIT_FOR_SHOW) {
                    steps.append(elements.toString()).append('\n');
                }

                for (int i = 1; i < elements.size(); i++) {
                    E current = elements.get(i);
                    int j = i - 1;

                    while (j >= 0 && elements.get(j).compareTo(current) > 0) {
                        elements.set(j + 1, elements.get(j));
                        j--;
                    }

                    elements.set(j + 1, current);

                    if (elements.size() <= UPPER_LIMIT_FOR_SHOW) {
                        steps.append(elements.toString()).append('\n');
                    }
                }
            }



            public void selectionSort(List<E> elements) {

                steps.setLength(0);

                if (elements.size() < UPPER_LIMIT_FOR_SHOW) {

                    steps.append(elements.toString()).append('\n');

                }

                int boundary = 0;

                while (boundary < elements.size() - 1) {

                    int minIndex = findMinIndex(elements, boundary);

                    swap(elements, boundary++, minIndex);

                    if (elements.size() < UPPER_LIMIT_FOR_SHOW)

                        steps.append(elements.toString()).append('\n');

                }

            }



            private int findMinIndex(List<E> elements, int boundary) {

                int minIndex = boundary;

                if (boundary == elements.size() - 1)

                    return minIndex;

                E min = elements.get(minIndex);

                for (int i = boundary + 1; i < elements.size(); i++) {

                    E e = elements.get(i);

                    if (e.compareTo(min) < 0) {min = e; minIndex = i;}

                }

                return minIndex;

            }



            private void swap(List<E> elements, int i, int j) {

                if (i < 0 || j < 0 || i >= elements.size() || j >= elements.size()) {

                    String err = String.format("Cannot swap elements between positions %d and %d in list of %d elements.",

                            i, j, elements.size());

                    throw new IndexOutOfBoundsException(err);

                }

                E tmp = elements.get(i);

                elements.set(i, elements.get(j));

                elements.set(j, tmp);

            }

        }
