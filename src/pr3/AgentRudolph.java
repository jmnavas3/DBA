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
        setup_rudolph();
        // setup_pr3();
    }

    private void setup_pr3() {
        /*
        1. esperar código Agente -> responder
        2. esperar solicitud coord hasta que no tenga más -> responder
        */
        
        Behaviour behavRecep = new BehavReceptor();
        addBehaviour(behavRecep);
    }

    private void setup_rudolph() {
        // array con 8 coordenadas y codigo de comunicacion
        for (int i = 0; i < n_renos; i++)
            renos.add(new Coords(i, i));
        
        ds.put("renos", renos);
        ds.put("codigo", codigo);
        
        // behaviour única que va por pasos:
        // 1. espera a que el agente le dé el código y responde
        // 2. espera a que el agente le pida coordenadas y responde
        // hasta que no tenga
        Behaviour behavRudolph = new BehavRudolph();
        behavRudolph.setDataStore(ds);
        addBehaviour(behavRudolph);
    }
}
