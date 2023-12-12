package pr3;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;


public class AgentP3 extends Agent {

    public DataStore sharedDataStore;

    @Override
    protected void setup() {
        sharedDataStore = new DataStore();

        // setup_pr2();
        setup_pr3();
    }
    
    private void setup_pr3() {
        /*
        1. presentarse (a Santa) -> espera respuesta
        2. dar_codigo (a Rudolph) -> esperar respuesta
        
        behaviours en bucle: la última es con santa
            3. pedir_coord (a Rudolph|Santa) -> esperar respuesta
            4. behavSee
            5. behavUtility
            6. behavMoveAgent
        7. avisar_llegada (a Santa) -> esperar respuesta final
        8. eliminar agente
        */
        
        Behaviour behavMess = new BehavMessage();
        addBehaviour(behavMess);
    }

    private void setup_pr2() {
        Enviroment env = new Enviroment();
        env.setAgentPosition(0, 0);
        env.setGoalPosition(9, 9);
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
        addBehaviour(behavUtility);
        addBehaviour(behavMoveAgent);
    }

}
