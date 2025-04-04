import java.util.*;

public class Select {
    
    public static Store randSelect(List<Store> storeList, int leftIdx, int rightIdx, int k){
        if (leftIdx == rightIdx) {
			return storeList.get(leftIdx); // Only one element
        }

        int pivotIndx = randPartition(storeList, leftIdx, rightIdx);
        int rank = pivotIndx - leftIdx + 1; // Rank of the pivot in the current subarray

        if (k == rank) {
			return storeList.get(pivotIndx); // Found the k-th smallest
        } else if (k < rank) {
            return randSelect(storeList, leftIdx, pivotIndx - 1, k); // Search in the left subarray
        } else {
            return randSelect(storeList, pivotIndx + 1, rightIdx, k - rank); // Search in the right subarray
        }

    }

    public static int randPartition(List<Store> storeList, int leftIdx, int rightIdx) {
        int pivotIndex = leftIdx + (int)(Math.random() * (rightIdx - leftIdx + 1));
        double pivotDistance = storeList.get(pivotIndex).distance;
    
        // Move pivot to end
        Store temp = storeList.get(pivotIndex);
        storeList.set(pivotIndex, storeList.get(rightIdx));
        storeList.set(rightIdx, temp);
    
        int i = leftIdx - 1;
    
        for (int j = leftIdx; j < rightIdx; j++) {
            if (storeList.get(j).distance <= pivotDistance) {
                i++;
                // Swap storeList[i] and storeList[j]
                temp = storeList.get(i);
                storeList.set(i, storeList.get(j));
                storeList.set(j, temp);
            }
        }
    
        // Move pivot to its final place
        temp = storeList.get(i + 1);
        storeList.set(i + 1, storeList.get(rightIdx));
        storeList.set(rightIdx, temp);
    
        return i + 1;
    }
    
}
