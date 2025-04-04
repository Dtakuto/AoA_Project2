import java.io.*;
import java.util.*;


public class Project2{

    public static List<Store> readFile(File filePath) throws Exception {
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

    public static List<Query> readQuery(File filePath) throws Exception {
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
    

    public static void main(String[] args) throws Exception {

       File file = new File("WhataburgerData.csv");

       File queryFile = new File("Queries.csv");

       System.out.println("Reading in file " + file);
    
       List<Store> storeList = readFile(file);

       System.out.println("Read in Queries file " + queryFile);

       List <Query> queryList = readQuery(queryFile);

       for (Query q : queryList){
            
            for (Store s : storeList) {
                s.computeDistance(q.latitude, q.longitude); // store the computed distance             
            }

            Store kthStore = Select.randSelect(storeList, 0, storeList.size() - 1, q.storesDesired);
            System.out.println("The " + q.storesDesired + " closest Stores to (" + q.latitude + ", " + q.longitude + "):");
           

            ArrayList<Store> selectedStores = new ArrayList<Store>();

            for (Store s : storeList) {
                if (s.distance <= kthStore.distance) {
                    selectedStores.add(s);
                }
            }

            // Sort the selected stores by distance with linear sort
            selectedStores = Sort.insertionSort(selectedStores);

            // Create a Sort object to format the output
            Result result = new Result(q, selectedStores);
            System.out.println(result);
            System.out.println(); // Print a blank line for better readability
       }
    }

}

    
