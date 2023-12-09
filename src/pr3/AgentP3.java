package pr3;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;


public class AgentP3 extends Agent {
    public DataStore sharedDataStore;
    private String santaCode;
    private int step;
    
    @Override
    protected void setup() {
        sharedDataStore = new DataStore();
        Enviroment env = new Enviroment();
        env.setAgentPosition(5,6);
        env.setGoalPosition(9,4);
        sharedDataStore.put("enviroment", env);
        this.step = 0;
        
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
        
        
        ACLMessage preguntaSanta = new ACLMessage();
        preguntaSanta.addReceiver(new AID("AgentSanta", AID.ISLOCALNAME));
        preguntaSanta.setContent("Hola santa. ¿He sido un chico bueno?");
        this.send(preguntaSanta);

        ACLMessage respuestaSanta = blockingReceive();

        
        if(respuestaSanta.getContent()=="No has sido bueno"){
            
        } else {
            // Si no has sido bueno deja se borra el agente
            this.doDelete();
        }
    }
}
