import java.io.*;
import java.util.*;

public class Project2{

    public static List<Store> readFile(String filePath) throws Exception {
        ArrayList<Store> storeList = new ArrayList<Store>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

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

        }
        reader.close();
        return storeList;

    }

    public static List<Store> readQuery(String filePath) throws Exception {
        ArrayList<Store> queryList = new ArrayList<Store>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line; 

        while ((line = reader.readLine()) != null) {
            String[] queryData = line.split(",");
            double queryLat = Double.parseDouble(queryData[0]);
            double queryLong = Double.parseDouble(queryData[1]);
            int storesDesired = Integer.parseInt(queryData[2]);

        }
        reader.close();
        return queryList;

    }
    public static void main(String[] args) throws Exception {

        System.out.println("Reading in file...");
    
       List<Store> storeList = readFile("WhataburgerData.csv");

       System.out.println("Read in Queries file...");

       List <Store> queryList = readQuery("Queries.csv");
    }

}

    
   
        



