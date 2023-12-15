package pr3;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.core.Agent;


/**
 *
 * @author galvez
 */
public class BehavSantaResponse extends OneShotBehaviour {
    
    @Override
    public void action() {
        ACLMessage msg = myAgent.blockingReceive();
        System.out.println(msg.getContent());
        
        int aleatorio = (int)Math.floor(Math.random()*10);
        System.out.println(aleatorio);
        
        ACLMessage respuestaSanta = new ACLMessage();
        
        if (1 <= aleatorio && aleatorio <= 8){
            respuestaSanta.addReceiver(new AID("AgentP3", AID.ISLOCALNAME));
            respuestaSanta.setContent("CodigoRudolph");
            myAgent.send(respuestaSanta);
        } else {
            respuestaSanta.addReceiver(new AID("AgentP3", AID.ISLOCALNAME));
            respuestaSanta.setContent("No eres digno");
            myAgent.send(respuestaSanta);
        }
    }
    
}
