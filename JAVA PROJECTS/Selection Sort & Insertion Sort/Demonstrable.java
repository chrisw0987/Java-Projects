import java.util.ArrayList;
import java.util.List;

public interface Demonstrable {
    String show();
}
//public void mergeSort(List<E> elements) {
//        int split = elements.size()/2;
//
//        }
//
//
//private List<E> merge(List<E> elements) {
//        for (int i = 0; i <= elements.size(); i+= 2) {
//        for (int j = 1; j <= elements.size(); j +=2){
//        if (elements.get(i).compareTo(elements.get(j)) < 0) {
//        swap(elements, i, j);
//        }
//        }
//        }
//        return null;
//        }
////        List<E> combined = new ArrayList<E>();
////
////        int index = 0;
////        int i = 0;
////        int j = 0;
////        while (i < array1.size() && j < array2.size()) {
////            //System.out.println(i);
////            //System.out.println(array1.get(i));
////            if (array1.get(i).compareTo(array2.get(j)) <= 0) {
////                combined.add(array1.get(i));
////                index++;
////                i++;
////            }
////            else {
//////                combined.add(index, array2.get(j));
////                combined.add(array2.get(j));
////                index++;
////                j++;
////            }
////        }
////        while (i < array1.size()) {
//////            combined.add(index, array1.get(i));
////            combined.add(array1.get(i));
////            index++;
////            i++;
////        }
////        while (j < array2.size()) {
//////            combined.add(index, array2.get(j));
////            combined.add(array2.get(j));
////            j++;
////        }
////        return combined;
////    }
//
//public void insertionSort(List<E> elements) {
//        for (int i = 1; i < elements.size(); i++) {
//        E temp = elements.get(i);
//        int j = i - 1;
//        while (j >= 0 && elements.get(j).compareTo(temp) > 0) {
//        elements.set(j + 1, elements.get(j));
//        j--;
//        }
//        elements.set(j + 1, temp);
//        }
//        }


