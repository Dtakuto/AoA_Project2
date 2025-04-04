import java.io.*;
import java.util.*;


public class Project2{

    public static List<Store> readFile(String filePath) throws Exception {
        ArrayList<Store> storeList = new ArrayList<Store>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.readLine(); // Skip the header line if there is one

        String line; 

        while ((line = reader.readLine()) != null) {
            String[] storeData = line.split(",");
            String storeID = storeData[0]; // storeID with "," in it
            String address = storeData[1];
            String city = storeData[2];
            String state = storeData[3];
            String zip = storeData[4];
            double latitude = Double.parseDouble(storeData[5]);
            double longitude = Double.parseDouble(storeData[6]);
            storeList.add(new Store(storeID, address, city, state, zip, latitude, longitude));

        }
        reader.close();
        return storeList;

    }

    public static List<Query> readQuery(String filePath) throws Exception {
        ArrayList<Query> queryList = new ArrayList<Query>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        reader.readLine(); // Skip the header line if there is one

        String line; 

        while ((line = reader.readLine()) != null) {
            String[] queryData = line.split(",");
            double queryLat = Double.parseDouble(queryData[0]);
            double queryLong = Double.parseDouble(queryData[1]);
            int storesDesired = Integer.parseInt(queryData[2]);
            queryList.add(new Query(queryLat, queryLong, storesDesired));

        }
        reader.close();
        return queryList;

    }

    
    // Comparator for sorting stores by distance
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

    public static void main(String[] args) throws Exception {

        System.out.println("Reading in file...");
    
       List<Store> storeList = readFile("WhataburgerData.csv");

       System.out.println("Read in Queries file...");

       List <Query> queryList = readQuery("Queries.csv");

       for (Query q : queryList){
            
            for (Store s : storeList) {
                s.computeDistance(q.latitude, q.longitude); // store the computed distance             
            }

            Store kthStore = Store.randSelect(storeList, 0, storeList.size() - 1, q.storesDesired);
            System.out.println("The " + q.storesDesired + " closest Stores to (" + q.latitude + ", " + q.longitude + "):");
           
            double kthDistance = kthStore.distance;

            ArrayList<Store> selectedStores = new ArrayList<Store>();
            for (Store s : storeList) {
                if (s.distance <= kthDistance) {
                    selectedStores.add(s);
                }
            }

            // Sort the selected stores by distance with linear sort
            selectedStores = insertionSort(selectedStores);
            for (int i = 0; i < q.storesDesired && i < selectedStores.size(); i++) {
                System.out.println(selectedStores.get(i).toString() + " - " + Math.round(selectedStores.get(i).distance * 100.0) / 100.0 + " miles");
            }
            System.out.println(); // Print a blank line for better readability
       }
    }

}

    
