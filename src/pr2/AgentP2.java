package pr2;

import pr1HelloWorld.*;
import java.util.Iterator;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

/*
    Comportamientos agente:
    1. Ver
    2. Calcular
    3. Moverse
*/

public class AgentP2 extends Agent {
    public DataStore sharedDataStore;
    
    @Override
    protected void setup() {
        sharedDataStore = new DataStore();
        Enviroment env = new Enviroment();
        env.setAgentPosition(6,6);
        env.setGoalPosition(3,3);
        sharedDataStore.put("enviroment", env);
        
        // Behaviour that moves the agent
        Behaviour behavMoveAgent = new BehavMoveAgent();
        behavMoveAgent.setDataStore(sharedDataStore);
        
        
        // Behaviour that calculate the next move
        Behaviour behavUtility = new BehavUtility();
        behavUtility.setDataStore(sharedDataStore);
        
        
        // Behaviour that update sensors
        Behaviour behavSee = new BehavSee();
        behavSee.setDataStore(sharedDataStore);
        
        addBehaviour(behavSee);
        addBehaviour(behavMoveAgent);

    }


}
