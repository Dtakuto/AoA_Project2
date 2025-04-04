import java.util.ArrayList;

public class Sort {
    
    public static ArrayList<Store> insertionSort(ArrayList<Store> storeList) {
        for (int i = 1; i < storeList.size(); i++){
            Store key = storeList.get(i);
            int j = i - 1;

            while (j >= 0 && storeList.get(j).distance > key.distance) {
                storeList.set(j + 1, storeList.get(j)); // Shift the element to the right
                j--;
            }
            storeList.set(j + 1, key); // Place the key in its correct position
        }
        return storeList;
    }

}
