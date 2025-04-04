//Dr. Gibson-Lopez' Store.java implementation.
//Providing mainly for the Haversine formula code.
//If coding in Java, you can use this implementation if you'd like, or you can use your own.

import java.util.Collections;
import java.util.List;

public class Store {

	public String id;
	public String address;
	public String city;
	public String state;
	public String zipCode;
	public double latitude;
	public double longitude;
	public double distance;
	
	public Store(String theID, String theAddress, String theCity, String theState, String theZip, double theLat, double theLong) {
		
		id = theID;
		address = theAddress;
		city = theCity;
		state = theState;
		zipCode = theZip;
		latitude = theLat;
		longitude = theLong;
		distance = -1;
	}
	
	public void computeDistance(double otherLat, double otherLong) {
		
		//Haversine Formula
		double radiusOfEarthInMiles = 3958.8;
		
		//First we convert the latitudes and longitudes to radians.
		double lat1 = Math.toRadians(latitude);
		double lat2 = Math.toRadians(otherLat);
		double long1 = Math.toRadians(longitude);
		double long2 = Math.toRadians(otherLong);
		
		//Then we can apply the Haversine Formula to get the distance in miles.
		double a = Math.pow(Math.sin((lat2-lat1)/2), 2) + Math.cos(lat1)*Math.cos(lat2)*Math.pow(Math.sin((long2-long1)/2), 2);
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		distance = radiusOfEarthInMiles*c;
		
	}
	
	public String toString() {
		
		return "Store #" + id + ". " + address + ", " + city + ", " + state + ", " + zipCode + ".";
	}

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
		int randIndex = leftIdx + (int)(Math.random() * (rightIdx - leftIdx + 1));
		Collections.swap(storeList, randIndex, rightIdx);

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
