import java.util.List;

public class Random {
    public static double randSelect(List<Store> storeList, int leftIdx, int rightIdx, int k){
        if (leftIdx == rightIdx) {
            return storeList.get(leftIdx).distance; // Only one element
        }

        int pivotInd = randPartition(storeList, leftIdx, rightIdx);
        int rank = pivotInd - leftIdx + 1; // Rank of the pivot in the current subarray

        if (k == rank) {
            return storeList.get(pivotInd).distance; // Found the k-th smallest
        } else if (k < rank) {
            return randSelect(storeList, leftIdx, pivotInd - 1, k); // Search in the left subarray
        } else {
            return randSelect(storeList, pivotInd + 1, rightIdx, k - rank); // Search in the right subarray
        }

    }

    public static int randPartition(List<Store> storeList, int leftIdx, int rightIdx) {
        double pivot = storeList.get(rightIdx).distance; // Choose the rightmost element as pivot
        int i = leftIdx - 1;

        for (int j = leftIdx; j < rightIdx; j++) {
            if (storeList.get(j).distance <= pivot) {
                i++;
                // Swap storeList[i] and storeList[j]
                Store temp = storeList.get(i);
                storeList.set(i, storeList.get(j));
                storeList.set(j, temp);
            }
        }

        i++;

        Store temp = storeList.get(i);
        storeList.set(i, storeList.get(rightIdx));
        storeList.set(rightIdx, temp); // Swap the pivot to its correct position
        return i;
    }
}
