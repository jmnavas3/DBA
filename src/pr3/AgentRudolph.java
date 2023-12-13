package pr3;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
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
         setup_rudolph();
        // setup_pr3();
    }

    private void setup_pr3() {
        /*
        valores rudolph
        array con 8 posiciones (renos)
        codigo de comunicacion con agente
        
        1. esperar código Agente -> responder
        2. esperar solicitud coord hasta que no tenga más -> responder
        */
        
        Behaviour behavRecep = new BehavReceptor();
        addBehaviour(behavRecep);
    }

    private void setup_rudolph() {
        for (int i = 0; i < n_renos; i++)
            renos.add(new Coords(i, i));
        
        ds.put("renos", renos);
        ds.put("codigo", codigo);
        
        Behaviour behavRudolph = new BehavRudolph();
        behavRudolph.setDataStore(ds);
        addBehaviour(behavRudolph);
    }
}
