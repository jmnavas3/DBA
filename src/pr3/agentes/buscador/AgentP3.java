package pr3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

public class AgentP3 extends Agent {

    public DataStore ds;

    @Override
    protected void setup() {
        ds = new DataStore();
        Enviroment env = new Enviroment();
        env.setAgentPosition(aleatorio(), aleatorio());
        ds.put("enviroment", env);
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
        Behaviour behavSee = new BehavSee();
        Behaviour behavUtility = new BehavUtility();
        Behaviour behavMoveAgent = new BehavMoveAgent();
        behavSee.setDataStore(ds);
        behavUtility.setDataStore(ds);
        behavMoveAgent.setDataStore(ds);
        addBehaviour(behavSee);
        addBehaviour(behavUtility);
        addBehaviour(behavMoveAgent);
    }

    private int aleatorio() {
        return (int) Math.floor(Math.random() * 40);
    }
}
