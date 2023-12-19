package pr3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;


public class AgentP3 extends Agent {
    public DataStore ds;
    
    @Override
    protected void setup() {
        ds = new DataStore();
        // pr2
        Enviroment env = new Enviroment();
        env.setAgentPosition(aleatorio(), aleatorio());
        ds.put("enviroment", env);
        // pr3
        ds.put("buscareno", false);
        ds.put("buscasanta", false);
        ds.put("fin", false);
        
        setup_pr3();
        setup_pr2();
    }
    
    private void setup_pr3() {
        Behaviour behavMess = new BehavMessage();
        behavMess.setDataStore(ds);
        addBehaviour(behavMess);
    }
    
    private void setup_pr2() {

        // Behaviour that moves the agent
        Behaviour behavMoveAgent = new BehavMoveAgent();
        behavMoveAgent.setDataStore(ds);

        // Behaviour that calculate the next move
        Behaviour behavUtility = new BehavUtility();
        behavUtility.setDataStore(ds);

        // Behaviour that update sensors
        Behaviour behavSee = new BehavSee();
        behavSee.setDataStore(ds);

        addBehaviour(behavSee);
        addBehaviour(behavUtility);
        addBehaviour(behavMoveAgent);
    }

    private int aleatorio() {
        return (int)Math.floor(Math.random()*40);
    }
}
