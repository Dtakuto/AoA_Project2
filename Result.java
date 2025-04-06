import java.util.ArrayList;

public class Result {
    private Query q;
    private ArrayList<Store> selectedStores;

    /**
     *
     * @param theQ
     * @param theSlectedStores
     */
    public Result(Query theQ, ArrayList<Store> theSlectedStores) {
        q = theQ;
        selectedStores = theSlectedStores;
    }

    /**
     *
     * @return
     */
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
