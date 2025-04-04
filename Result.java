import java.util.ArrayList;

public class Result {
    private Query q; // Assuming Query is a class that contains storesDesired
    private ArrayList<Store> selectedStores; // Assuming selectedStores is a list of Store objects

    public Result(Query theQ, ArrayList<Store> theSlectedStores) {
        q = theQ;
        selectedStores = theSlectedStores;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < q.storesDesired && i < selectedStores.size(); i++) {
            result.append(selectedStores.get(i).toString())
                  .append(" - ")
                  .append(String.format("%.2f", selectedStores.get(i).distance))
                  .append(" miles\n");
        }
        return result.toString();
    }
}
