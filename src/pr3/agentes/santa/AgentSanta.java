package pr3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

public class AgentSanta extends Agent {

    public DataStore ds;

    @Override
    protected void setup() {
        ds = new DataStore();
        ds.put("termino", false);
        Behaviour behavSantaResponse = new BehavSantaResponse();
        behavSantaResponse.setDataStore(ds);
        addBehaviour(behavSantaResponse);
    }
}
