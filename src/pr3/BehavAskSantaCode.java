package pr3;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;


/**
 *
 * @author galvez
 */
public class BehavAskSantaCode extends OneShotBehaviour {
    
    @Override
    public void action() {
        ACLMessage msg = new ACLMessage();
        msg.addReceiver(new AID("AgentSanta", AID.ISLOCALNAME));
        msg.setContent("Hola agente");
        //send(msg);
    }
    
}
