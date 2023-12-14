/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pr3;

import jade.core.Agent;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
//import jade.core.behaviours.DataStore;

/**
 *
 * @author joy111
 */
public class MessageBehav extends CyclicBehaviour {
    
    private int step = 0;
    
        
    @Override
    public void action() {
        
            switch (step) {
                    
            case 0 -> {
                //System.out.println("Valor de step: " + step);
                ACLMessage msg = new ACLMessage();
                
                msg.addReceiver(new AID("Rudolph",AID.ISLOCALNAME));
                
                msg.setContent("Hola Rudolph");
                
                msg.setConversationId("ID");        //Es el ID de Santa que te da para hablar con Rudolph
               // msg.setConversationId("ID");
                myAgent.send(msg);
                
                step = 1;
                
                System.out.println("Valor de step: " + step);
            }
                    
            case 1 -> {
                //System.out.println("Entra en el case 1");
                ACLMessage msg = myAgent.blockingReceive();
                System.out.println("Mensaje de Rudolph: " + msg.getContent());
              /*if(msg.getConversationId().equals("ID")) {
                    System.out.println("El mensaje es:" + msg.getContent());
                }
                else
                    System.out.println("Error en la conversacion");
                        
                System.out.println("El mensaje es: " + msg.getContent());
                */
            }
        }
    }
    
   
    
}
