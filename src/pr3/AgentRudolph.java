package pr3;

import jade.core.Agent;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.Behaviour;
import java.util.HashMap;


public class AgentRudolph extends Agent {

    public DataStore sharedDataStore;
    private int n_renos = 8;
    //private int step = 0;
    HashMap<String, Coords> renos = new HashMap<String, Coords>();

    @Override
    protected void setup() {
        sharedDataStore = new DataStore();
        
        // setup_rudolph();
        setup_pr3();
    }

    private void setup_pr3() {
        Behaviour behavRecep = new BehavReceptor();
        addBehaviour(behavRecep);
    }

    private void setup_rudolph() {
        for (int i = 0; i < n_renos; i++) {
            renos.put("reno " + i, new Coords(i, i));
        }

        ACLMessage msg = blockingReceive();
        System.out.print("recibiendo mensaje: " + msg.getContent());

        ACLMessage replay = msg.createReply();
        replay.setConversationId("Hola");
        replay.setContent("Hola Agente");
        send(replay);
    }
}
