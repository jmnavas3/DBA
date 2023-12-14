package pr3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

import jade.lang.acl.ACLMessage;
import jade.core.AID;
//import jade.domain.introspection.ACLMessage;
//import jade.content.OntoACLMessage;

public class AgentP3 extends Agent {
    public DataStore sharedDataStore;
    private boolean buscareno = false;
    
    @Override
    protected void setup() {
        sharedDataStore = new DataStore();
        Enviroment env = new Enviroment();
        env.setAgentPosition(0,0);
        env.setGoalPosition(9,9);
        sharedDataStore.put("enviroment", env);
        
        sharedDataStore.put("buscareno", buscareno);
        
        /*
        // Behaviour that moves the agent
        Behaviour behavMoveAgent = new BehavMoveAgent();
        behavMoveAgent.setDataStore(sharedDataStore);
        
        
        // Behaviour that calculate the next move
        Behaviour behavUtility = new BehavUtility();
        behavUtility.setDataStore(sharedDataStore);
        
        
        // Behaviour that update sensors
        Behaviour behavSee = new BehavSee();
        behavSee.setDataStore(sharedDataStore);
        
        Behaviour behavMess = new BehavMessage();
        
        Behaviour behavMess = new MessageBehav();
        addBehaviour(behavMess);
        
        
            Behaviour behavMovimiento = new BehavMovimiento();
            addBehaviour(behavMovimiento);
        
        /*
        addBehaviour(behavSee);
        addBehaviour(behavUtility);
        addBehaviour(behavMoveAgent);
        */


    }


}
