package pr3;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.core.behaviours.Behaviour;
import java.util.ArrayList;


public class AgentRudolph extends Agent {

    public DataStore ds;
    private final int n_renos = 8;
    private final String codigo = "code";
    ArrayList<Coords> renos = new ArrayList<>();

    @Override
    protected void setup() {
        ds = new DataStore();
        
        for (int i = 0; i < n_renos; i++)
            renos.add(new Coords(i, i));

        ds.put("renos", renos);
        ds.put("codigo", codigo);
        ds.put("terminar", false);
        setup_pr3();
    }

    private void setup_pr3() {
        Behaviour behavRecep = new BehavReceptor();
        behavRecep.setDataStore(ds);
        addBehaviour(behavRecep);
    }
}
