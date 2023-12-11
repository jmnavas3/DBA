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
    
    @Override
    protected void setup() {
        sharedDataStore = new DataStore();
        Enviroment env = new Enviroment();
        env.setAgentPosition(0,0);
        env.setGoalPosition(10,10);
        sharedDataStore.put("enviroment", env);
        
        //Paso de mensajes, este es el que inicia la conversacion.
        
      
        /*
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
      
        msg.addReceiver(new AID("jorge-receiver", AID.ISLOCALNAME));
        
        msg.setContent("Hola agente");
        
        msg.setConversationId("greeting-conversation");
        
        this.send(msg);
        */
        
      /*  int step = 0;
      //  switch (step){
        //    case 0 -> {
                ACLMessage msg = new ACLMessage(ACLMessage);
                msg.addReceiver(new AID("mjcobo-receiver", AID.ISLOCALNAME));
                msg.setConversationId(CONVERSATION_ID);
                myAgent.send(msg);
               // this.step = 1;
                }
            
           
        }
        /*
        
        */
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
