package pr3;

import jade.core.AID;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.DataStore;
import jade.lang.acl.ACLMessage;
import jade.core.Agent;



/**
 *
 * @author galvez
 */

//Este no deberia utilizarse ya, tiene que estar incluido en BehavMessage

public class BehavAskSantaCode extends Behaviour {      //Recibe el REQUEST de AgentP3 pidiendo el codigo, luego deberia seguir escuchando para darle su pos y responder con un HOHOHO!
    
    DataStore ds;
    
    @Override
    public void action() {
        
        ds = this.getDataStore();
        
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
    
    @Override
    public boolean done() {
        ds = this.getDataStore();
        return (boolean) ds.get("fin");
    }
    
}
