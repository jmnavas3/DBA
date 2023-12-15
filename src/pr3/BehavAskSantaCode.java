package pr3;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.core.Agent;



/**
 *
 * @author galvez
 */
public class BehavAskSantaCode extends OneShotBehaviour {
    
    @Override
    public void action() {
        ACLMessage preguntaSanta = new ACLMessage();
        preguntaSanta.addReceiver(new AID("AgentSanta", AID.ISLOCALNAME));
        preguntaSanta.setContent("Hola santa. ¿He sido un chico bueno?");
        myAgent.send(preguntaSanta);
        
        ACLMessage respuestaSanta = myAgent.blockingReceive();
        //System.out.println(respuestaSanta.getContent());
        
        if(respuestaSanta.getContent()=="No has sido bueno"){
            // Si no has sido bueno deja se borra el agente
            myAgent.doDelete();
        } 
    }
    
}
