package pr2;

import pr1HelloWorld.*;
import java.util.Iterator;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;

/**
 * 1. Agente abstracto que implementa métodos comunes que uso en los agentes de
 * la práctica 1
 *
 * @author jmnavas
 */
public abstract class AgentP2 extends Agent {
    public DataStore sharedDataStore;
    
    @Override
    protected void setup() {
        sharedDataStore = new DataStore();
        Enviroment env = new Enviroment();
        env.setAgentPosition(9,9);
        env.setGoalPosition(0,0);
        


    }

    public void calculateUtility(Enviroment env){
        
    }
}
