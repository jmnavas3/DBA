package pr3;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import java.util.Random;


public class AgentSanta extends Agent {
    private int step; 
    private int x_pos_rudolph, y_pos_rudolph;
    
    @Override
    protected void setup() {
        this.step = 0;
        
        ACLMessage msg = blockingReceive();
        System.out.print(msg.getContent());
        
        int aleatorio = (int)Math.floor(Math.random()*10);
        
        if (1 <= aleatorio && aleatorio <= 8){
            ACLMessage preguntaSanta = new ACLMessage();
            preguntaSanta.addReceiver(new AID("AgentP3", AID.ISLOCALNAME));
            preguntaSanta.setContent("Si has sido bueno");
            this.send(preguntaSanta);
        } else {
            ACLMessage preguntaSanta = new ACLMessage();
            preguntaSanta.addReceiver(new AID("AgentP3", AID.ISLOCALNAME));
            preguntaSanta.setContent("No has sido bueno");
            this.send(preguntaSanta);
        }

    }


}
